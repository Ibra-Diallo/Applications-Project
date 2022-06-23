package com.ibrahimadiallo.studentmanagement.controller;

import com.ibrahimadiallo.studentmanagement.model.Grade;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.service.CourseService;
import com.ibrahimadiallo.studentmanagement.service.GradeService;
import com.ibrahimadiallo.studentmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GradeController {

    private GradeService gradeService;
    private UserService userService;
    private CourseService courseService;

    @Autowired
    public GradeController(GradeService gradeService, UserService userService, CourseService courseService) {
        this.gradeService = gradeService;
        this.userService = userService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/add-grade", method = RequestMethod.POST)
    public String addGrade(@ModelAttribute("gradeGiven") Grade grade, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ((UserDetails) auth.getPrincipal());

        gradeService.addGrade(grade);

        Grade grade1 = new Grade();

        User user = userService.getUserByUsername(userDetails.getUsername());
        grade1.setTeacherId(user.getId());
        model.addAttribute("gradeGiven", grade1);
        model.addAttribute("studentsList", userService.findAllStudent());
        model.addAttribute("coursesList", courseService.getAllCourseBasedOnUserId(user.getId()));
        model.addAttribute("errorMessage", "Grade Added Successfully");
        model.addAttribute("showErrorMessage", true);
        return "teacherHome";
    }
}
