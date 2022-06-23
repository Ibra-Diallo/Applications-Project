package com.ibrahimadiallo.studentmanagement.controller;

import com.ibrahimadiallo.studentmanagement.controller.LoginController;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.service.CourseService;
import com.ibrahimadiallo.studentmanagement.service.GradeService;
import com.ibrahimadiallo.studentmanagement.service.UserService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class LoginControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private CourseService courseService;
    @Mock
    private GradeService gradeService;

    private LoginController loginController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        loginController = new LoginController(userService, courseService, gradeService);
    }

    @Test
    public void testGetLoginPage() {
        Model model = Mockito.mock(Model.class);
        String result = loginController.getLoginPage(model);

        Assertions.assertEquals("login", result);
    }
}
