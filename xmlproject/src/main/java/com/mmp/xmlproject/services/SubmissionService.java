package com.mmp.xmlproject.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmp.xmlproject.model.SalesforceStudentRecord;
import com.mmp.xmlproject.model.SalesforceSubmitResult;
import com.mmp.xmlproject.model.ValidationResult;
import com.mmp.xmlproject.xmlmodel.Students;

@Service
public class SubmissionService {

    @Autowired
    SalesforceClient salesforceClient;

    @Autowired
    SalesforceTransformer transformer;

    @Autowired
    StudentValidator validator;

    @Autowired
    XmlStudentParser parser;

    @Async
    public String processXmlValidation(String id) {

        InputStream inputStream = null;

        try {
            inputStream = SubmissionService.class
                    .getClassLoader()
                    .getResourceAsStream("SampleData/students.xml");

            if (inputStream == null) {
                throw new IllegalStateException("XML file not found");
            }

            Students students = parser.parse(inputStream);
            System.out.println("XML parsed successfully");

            ValidationResult validationResult = validator.validate(students);

            if (!validationResult.isValid()) {
                System.out.println("Validation errors:");
                validationResult.getErrors().forEach(System.out::println);
                return "VALIDATION_FAILED";
            }

            System.out.println("Business validation passed");

            List<SalesforceStudentRecord> records = transformer.transform(students);

            ObjectMapper mapper = new ObjectMapper();

            String jsonPayload = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(records);

            System.out.println("\n=== JSON Payload ===");
            System.out.println(jsonPayload);

            SalesforceSubmitResult result = salesforceClient.submitStudents(records);

            System.out.println("\n=== Salesforce Response ===");
            String responseJson = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(result);

            System.out.println(responseJson);

            return responseJson;

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    System.err.println("Failed to close InputStream");
                }
            }
        }
    }
}