package com.mmp.xmlproject.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mmp.xmlproject.model.ValidationError;
import com.mmp.xmlproject.model.ValidationResult;
import com.mmp.xmlproject.xmlmodel.Course;
import com.mmp.xmlproject.xmlmodel.Student;
import com.mmp.xmlproject.xmlmodel.Students;

@Service
public class StudentValidator {

    private static final Set<String> VALID_GENDERS =
            new HashSet<>(Arrays.asList("M", "F", "Other"));

    // Mock reference data
    private static final Set<String> VALID_COURSE_CODES =
            new HashSet<>(Arrays.asList("CS101", "MATH201", "ENG101"));

    public ValidationResult validate(Students students) {
        ValidationResult result = new ValidationResult();

        for (Student student : students.getStudents()) {
            validateStudent(student, result);
        }

        return result;
    }

    private void validateStudent(Student student, ValidationResult result) {

        int studentId = student.getStudentId();

        // Email format (basic)
        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            result.addError(new ValidationError(studentId, "Email", "Invalid email format"));
        }

        // Gender check
        if (!VALID_GENDERS.contains(student.getGender())) {
            result.addError(new ValidationError(studentId, "Gender", "Invalid gender value"));
        }

        // Date of Birth validation
        if (!isValidDate(student.getDateOfBirth())) {
            result.addError(new ValidationError(studentId, "DateOfBirth", "Invalid date format"));
        }

        // Address validation
        if (student.getAddress() != null) {
            if (student.getAddress().getState().length() != 2) {
                result.addError(new ValidationError(studentId, "State", "State must be 2 characters"));
            }
        }

        // Course validation
        if (student.getCourses() == null || student.getCourses().getCourses().isEmpty()) {
            result.addError(new ValidationError(studentId, "Courses", "At least one course is required"));
        } else {
            for (Course course : student.getCourses().getCourses()) {
                validateCourse(studentId, course, result);
            }
        }
    }

    private void validateCourse(int studentId, Course course, ValidationResult result) {

        // Course code exists in reference data
        if (!VALID_COURSE_CODES.contains(course.getCourseCode())) {
            result.addError(new ValidationError(studentId, "CourseCode",
                    "Invalid course code: " + course.getCourseCode()));
        }

        // Enrollment date
        if (!isValidDate(course.getEnrollmentDate())) {
            result.addError(new ValidationError(studentId, "EnrollmentDate",
                    "Invalid enrollment date"));
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}