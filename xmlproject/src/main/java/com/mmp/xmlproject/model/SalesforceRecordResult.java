package com.mmp.xmlproject.model;

import java.util.ArrayList;
import java.util.List;

public class SalesforceRecordResult {

    private String externalId;
    private boolean success;
    private String salesforceId;
    private List<String> errors = new ArrayList<>();

    public SalesforceRecordResult() {
    }

    public SalesforceRecordResult(String externalId, boolean success, String salesforceId, List<String> errors) {
        this.externalId = externalId;
        this.success = success;
        this.salesforceId = salesforceId;
        this.errors = errors;
    }

    public String getExternalId() {
        return externalId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSalesforceId() {
        return salesforceId;
    }

    public List<String> getErrors() {
        return errors;
    }
}
