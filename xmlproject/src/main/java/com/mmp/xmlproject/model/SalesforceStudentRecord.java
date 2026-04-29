package com.mmp.xmlproject.model;

import java.util.List;

public class SalesforceStudentRecord {

    private String externalId;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String state;

    private List<SalesforceCourseRecord> courses;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SalesforceCourseRecord> getCourses() {
        return courses;
    }

    public void setCourses(List<SalesforceCourseRecord> courses) {
        this.courses = courses;
    }
}
