package com.mmp.xmlproject.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    
    private List<ValidationError> errors = new ArrayList<>();

    public void addError(ValidationError error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
