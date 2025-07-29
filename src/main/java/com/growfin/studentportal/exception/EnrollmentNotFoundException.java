package com.growfin.studentportal.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(Long student_id, Long course_id) {
        super("The student with id " + student_id + " is not enrolled in course with id " + course_id);
    }
}
