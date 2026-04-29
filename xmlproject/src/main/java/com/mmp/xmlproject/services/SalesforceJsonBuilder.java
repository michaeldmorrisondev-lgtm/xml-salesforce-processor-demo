package com.mmp.xmlproject.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmp.xmlproject.model.SalesforceStudentRecord;

@Service
public class SalesforceJsonBuilder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String build(List<SalesforceStudentRecord> records) throws Exception {

        Map<String, Object> payload = new HashMap<>();
        payload.put("records", records);

        return objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(payload);
    }
}