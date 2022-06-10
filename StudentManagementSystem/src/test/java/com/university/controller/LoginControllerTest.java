package com.university.controller;

import com.university.model.User;
import com.university.service.CourseService;
import com.university.service.GradeService;
import com.university.service.UserService;
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

    @Test
    public void testGetLogin() {
        Model model = Mockito.mock(Model.class);
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        String result = loginController.login(user, model);

        Assertions.assertEquals("adminHome", result);
    }
}
