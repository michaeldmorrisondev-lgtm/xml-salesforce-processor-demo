package com.mmp.xmlproject.model;

public class ValidationError {

    private int studentId;
    private String field;
    private String message;

    public ValidationError(int studentId, String field, String message) {
        this.studentId = studentId;
        this.field = field;
        this.message = message;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "studentId=" + studentId +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}