package com.university.controller;

import com.university.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class RegisterControllerTest {

    @Mock
    private UserService userService;

    private RegisterController registerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        registerController = new RegisterController(userService);
    }

    @Test
    public void testGetRegisterPage() {
        Model model = Mockito.mock(Model.class);
        String result = registerController.getRegisterPage(model);

        Assertions.assertEquals("register", result);
    }
}
