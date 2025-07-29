package com.growfin.studentportal.service;
import java.util.*;
import java.util.stream.Collectors;
import com.growfin.studentportal.dto.CourseRequestDTO;
import com.growfin.studentportal.dto.CourseResponseDTO;
import com.growfin.studentportal.entity.Course;
import com.growfin.studentportal.entity.Student;
import com.growfin.studentportal.enums.CourseDifficulty;
import com.growfin.studentportal.exception.CourseNotFoundException;
import com.growfin.studentportal.repository.CourseRepository;
import com.growfin.studentportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // 2. ADDING A COURSE

    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        String courseName = dto.getCourseName();
        String courseDifficulty = dto.getCourseDifficulty().toString();

        Course course = Course.builder()
                .courseName(courseName)
                .courseDifficulty(CourseDifficulty.valueOf(courseDifficulty))
                .build();
        Course saved = courseRepository.save(course);
        return CourseResponseDTO.builder()
                .courseId(saved.getCourseId())
                .courseName(saved.getCourseName())
                .courseDifficulty(saved.getCourseDifficulty().toString())
                .build();
    }

    // 3. LIST ALL THE COURSE NAMES
    public List<String> getAllCourseNames() {
        return courseRepository.findAll().stream()
                .map(Course::getCourseName)
                .toList();
    }

    //4. DELETE THE GIVEN COURSE
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));
        for (Student student : course.getStudents()) {
            student.getCourses().remove(course);
        }
        course.getStudents().clear();
        courseRepository.deleteById(courseId);
    }

    // 5. LIST ALL THE COURSES WITH THEIR DIFFICULTY
    public List<String> getCourseWithDifficulty(CourseDifficulty courseDifficulty) {
        return courseRepository.findByCourseDifficulty(courseDifficulty).stream()
                .map(Course::getCourseName)
                .toList();
    }
}
