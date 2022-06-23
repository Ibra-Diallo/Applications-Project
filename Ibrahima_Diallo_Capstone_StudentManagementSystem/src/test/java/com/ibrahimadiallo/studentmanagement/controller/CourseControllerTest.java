package com.ibrahimadiallo.studentmanagement.controller;

import com.ibrahimadiallo.studentmanagement.controller.CourseController;
import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.service.CourseService;
import com.ibrahimadiallo.studentmanagement.service.UserService;

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
