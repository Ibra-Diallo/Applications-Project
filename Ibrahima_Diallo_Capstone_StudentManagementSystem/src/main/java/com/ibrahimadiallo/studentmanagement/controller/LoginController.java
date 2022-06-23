package com.ibrahimadiallo.studentmanagement.controller;

import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.model.Grade;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.model.UserCourse;
import com.ibrahimadiallo.studentmanagement.service.CourseService;
import com.ibrahimadiallo.studentmanagement.service.GradeService;
import com.ibrahimadiallo.studentmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "login";
    }

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ((UserDetails) auth.getPrincipal());

        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
            model.addAttribute("course", new Course());
            model.addAttribute("userCourse", new UserCourse());
            model.addAttribute("teachersList", userService.findAllTeacher());
            model.addAttribute("coursesList", courseService.getAllCourse());

            model.addAttribute("showErrorMessage", false);
            return "adminHome";
        }

        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Student"))) {
            User user = userService.getUserByUsername(userDetails.getUsername());
            model.addAttribute("grades", gradeService.findStudentGrades(user));
            return "studentHome";
        } else {
            Grade grade = new Grade();
            User user = userService.getUserByUsername(userDetails.getUsername());
            grade.setTeacherId(user.getId());
            model.addAttribute("gradeGiven", grade);
            model.addAttribute("studentsList", userService.findAllStudent());
            model.addAttribute("coursesList", courseService.getAllCourseBasedOnUserId(user.getId()));
            model.addAttribute("showErrorMessage", false);
            return "teacherHome";
        }
    }
}
