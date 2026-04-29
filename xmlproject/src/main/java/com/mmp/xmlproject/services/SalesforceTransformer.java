package com.mmp.xmlproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mmp.xmlproject.model.SalesforceCourseRecord;
import com.mmp.xmlproject.model.SalesforceStudentRecord;
import com.mmp.xmlproject.xmlmodel.Course;
import com.mmp.xmlproject.xmlmodel.Student;
import com.mmp.xmlproject.xmlmodel.Students;

@Service
public class SalesforceTransformer {

    public List<SalesforceStudentRecord> transform(Students students) {
        List<SalesforceStudentRecord> results = new ArrayList<>();

        for (Student student : students.getStudents()) {
            results.add(transformStudent(student));
        }

        return results;
    }

    private SalesforceStudentRecord transformStudent(Student student) {

        SalesforceStudentRecord record = new SalesforceStudentRecord();

        record.setExternalId(String.valueOf(student.getStudentId()));
        record.setFirstName(student.getFirstName());
        record.setLastName(student.getLastName());
        record.setEmail(student.getEmail());
        record.setDateOfBirth(student.getDateOfBirth());
        record.setGender(student.getGender());

        if (student.getAddress() != null) {
            record.setState(student.getAddress().getState());
        }

        record.setCourses(transformCourses(student));

        return record;
    }

    private List<SalesforceCourseRecord> transformCourses(Student student) {

        List<SalesforceCourseRecord> courses = new ArrayList<>();

        if (student.getCourses() != null) {
            for (Course c : student.getCourses().getCourses()) {
                SalesforceCourseRecord course = new SalesforceCourseRecord();
                course.setCourseCode(c.getCourseCode());
                course.setEnrollmentDate(c.getEnrollmentDate());
                courses.add(course);
            }
        }

        return courses;
    }
}