package com.university.controller;

import com.university.model.Grade;
import com.university.model.User;
import com.university.service.CourseService;
import com.university.service.GradeService;
import com.university.service.UserService;
import com.university.session.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class GradeControllerTest {

    @Mock
    private GradeService gradeService;
    @Mock
    private UserService userService;
    @Mock
    private CourseService courseService;

    private GradeController gradeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gradeController = new GradeController(gradeService, userService, courseService);
    }

    @Test
    public void testAddGrade() {
        Model model = Mockito.mock(Model.class);
        Grade grade = new Grade();
        Session.currentUser = new User();

        String result = gradeController.addGrade(grade, model);

        Assertions.assertEquals("teacherHome", result);
    }
}
