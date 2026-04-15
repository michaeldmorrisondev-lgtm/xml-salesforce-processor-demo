package com.mmp.xmlproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

import com.mmp.xmlproject.services.XmlMarshaller;
import com.mmp.xmlproject.xmlmodel.Students;

@EnableAsync
@Controller
public class SubmissionController {
    
    @Autowired
    XmlMarshaller xmlMarshaller;
    
    public void processXmlValidation (String id) {

        //System.out.println(id);
        try {
            Students students = xmlMarshaller.marshallStudentsXml("SampleData/students.xml");
            //System.out.println("Parsed student count: " + students.getStudents().size());
        } catch(Exception e) {
            e.printStackTrace();
        } finally {

        }

    }
}
