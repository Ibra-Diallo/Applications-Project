package com.university.controller;

import com.university.model.Grade;
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

        gradeService.addGrade(grade);

        Grade grade1 = new Grade();
        grade1.setTeacherId(Session.currentUser.getId());
        model.addAttribute("gradeGiven", grade1);
        model.addAttribute("studentsList", userService.findAllStudent());
        model.addAttribute("coursesList", courseService.getAllCourse());
        model.addAttribute("errorMessage", "Grade Added Successfully");
        model.addAttribute("showErrorMessage", true);
        return "teacherHome";
    }
}
