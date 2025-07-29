package com.growfin.studentportal.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long course_id) {
        super("The course with id " + course_id + " does not exist");
    }
}
