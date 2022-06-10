package com.university.controller;

import com.university.model.Course;
import com.university.service.CourseService;
import com.university.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class CourseControllerTest {

    @Mock
    private CourseService courseService;
    @Mock
    private UserService userService;

    private CourseController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new CourseController(courseService, userService);
    }

    @Test
    public void testAddCourse() {
        Model model = Mockito.mock(Model.class);
        Course course = new Course();

        String result = controller.addCourse(course, model);

        Assertions.assertEquals("adminHome", result);
    }
}
