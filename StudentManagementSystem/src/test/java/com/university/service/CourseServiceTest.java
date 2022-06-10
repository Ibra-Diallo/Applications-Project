package com.university.service;

import com.university.model.Course;
import com.university.repository.CourseRepository;
import com.university.repository.UserCourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserCourseRepository userCourseRepository;

    private CourseService courseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseService(courseRepository, userCourseRepository);
    }

    @Test
    public void testGetAllCourse() {
        List<Course> courseList = new ArrayList<>();
        Mockito.when(courseRepository.findAll()).thenReturn(courseList);
        List<Course> result = courseService.getAllCourse();

        Assertions.assertEquals(0, result.size());
    }
}
