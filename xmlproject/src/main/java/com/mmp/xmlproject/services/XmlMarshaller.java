package com.mmp.xmlproject.services;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmp.xmlproject.xmlmodel.Students;

@Service
public class XmlMarshaller {
    
    @Autowired
    XmlStudentParser parser;

    public Students marshallStudentsXml(String filePath) throws Exception {
        try (InputStream inputStream = XmlMarshaller.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {

            if (inputStream == null) {
                throw new IllegalStateException("Could not find sample-data/students.xml");
            }

            Students students = parser.parse(inputStream);

            return students;
        }
    }
}
