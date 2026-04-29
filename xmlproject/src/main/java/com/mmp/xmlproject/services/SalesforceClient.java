package com.mmp.xmlproject.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmp.xmlproject.model.SalesforceStudentRecord;
import com.mmp.xmlproject.model.SalesforceSubmitResult;

@Service
public interface SalesforceClient {
    SalesforceSubmitResult submitStudents(List<SalesforceStudentRecord> records);
}