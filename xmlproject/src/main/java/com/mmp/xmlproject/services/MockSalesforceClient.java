package com.mmp.xmlproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mmp.xmlproject.model.SalesforceRecordResult;
import com.mmp.xmlproject.model.SalesforceStudentRecord;
import com.mmp.xmlproject.model.SalesforceSubmitResult;

@Service
public class MockSalesforceClient implements SalesforceClient {

    @Override
    public SalesforceSubmitResult submitStudents(List<SalesforceStudentRecord> records) {
        SalesforceSubmitResult submitResult = new SalesforceSubmitResult();

        List<SalesforceRecordResult> recordResults = new ArrayList<>();

        int successCount = 0;
        int failureCount = 0;

        for (SalesforceStudentRecord record : records) {
            List<String> errors = validateMockSalesforceRules(record);

            if (errors.isEmpty()) {
                successCount++;

                recordResults.add(new SalesforceRecordResult(
                        record.getExternalId(),
                        true,
                        generateFakeSalesforceId(),
                        errors
                ));
            } else {
                failureCount++;

                recordResults.add(new SalesforceRecordResult(
                        record.getExternalId(),
                        false,
                        null,
                        errors
                ));
            }
        }

        submitResult.setTotalRecords(records.size());
        submitResult.setSuccessCount(successCount);
        submitResult.setFailureCount(failureCount);
        submitResult.setResults(recordResults);

        return submitResult;
    }

    private List<String> validateMockSalesforceRules(SalesforceStudentRecord record) {
        List<String> errors = new ArrayList<>();

        if (record.getExternalId() == null || record.getExternalId().isBlank()) {
            errors.add("Missing external ID.");
        }

        if (record.getEmail() == null || record.getEmail().isBlank()) {
            errors.add("Missing email.");
        }

        if (record.getLastName() == null || record.getLastName().isBlank()) {
            errors.add("Missing last name.");
        }

        return errors;
    }

    private String generateFakeSalesforceId() {
        return "a0B" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 15);
    }
}