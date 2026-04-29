package com.mmp.xmlproject.model;

import java.util.ArrayList;
import java.util.List;

public class SalesforceSubmitResult {

    private int totalRecords;
    private int successCount;
    private int failureCount;
    private List<SalesforceRecordResult> results = new ArrayList<>();

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public List<SalesforceRecordResult> getResults() {
        return results;
    }

    public void setResults(List<SalesforceRecordResult> results) {
        this.results = results;
    }
}
