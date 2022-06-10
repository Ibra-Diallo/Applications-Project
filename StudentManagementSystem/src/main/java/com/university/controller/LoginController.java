package com.university.controller;

import com.university.model.Course;
import com.university.model.Grade;
import com.university.model.User;
import com.university.model.UserCourse;
import com.university.service.CourseService;
import com.university.service.GradeService;
import com.university.service.UserService;
import com.university.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private UserService userService;
    private CourseService courseService;
    private GradeService gradeService;

    @Autowired
    public LoginController(UserService userService, CourseService courseService, GradeService gradeService) {
        this.userService = userService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(Model model) {
        return "index";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("showErrorMessage", false);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, Model model) {
        if (user.getUsername().equalsIgnoreCase("admin") || user.getPassword().equalsIgnoreCase("admin")) {
            model.addAttribute("course", new Course());
            model.addAttribute("userCourse", new UserCourse());
            model.addAttribute("teachersList", userService.findAllTeacher());
            model.addAttribute("coursesList", courseService.getAllCourse());

            model.addAttribute("showErrorMessage", false);
            return "adminHome";
        }
        User login = userService.login(user.getUsername(), user.getPassword());
        if (login == null) {
            model.addAttribute("user", user);
            model.addAttribute("showErrorMessage", true);
            return "login";
        }

        Session.currentUser = login;

        if (login.getRole().equalsIgnoreCase("student")) {
            model.addAttribute("grades", gradeService.findStudentGrades(login));
            return "studentHome";
        } else {
            Grade grade = new Grade();
            grade.setTeacherId(login.getId());
            model.addAttribute("gradeGiven", grade);
            model.addAttribute("studentsList", userService.findAllStudent());
            model.addAttribute("coursesList", courseService.getAllCourse());
            model.addAttribute("showErrorMessage", false);
            return "teacherHome";
        }
    }
}
