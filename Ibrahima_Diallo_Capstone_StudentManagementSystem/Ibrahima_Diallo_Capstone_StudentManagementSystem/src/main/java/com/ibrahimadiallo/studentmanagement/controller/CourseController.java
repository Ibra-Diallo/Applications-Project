package com.ibrahimadiallo.studentmanagement.controller;

import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.model.UserCourse;
import com.ibrahimadiallo.studentmanagement.service.CourseService;
import com.ibrahimadiallo.studentmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    private CourseService courseService;
    private UserService userService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/add-course", method = RequestMethod.POST)
    public String addCourse(@ModelAttribute("course") Course course, Model model) {
        courseService.addCourse(course);
        model.addAttribute("course", new Course());
        model.addAttribute("userCourse", new UserCourse());
        model.addAttribute("teachersList", userService.findAllTeacher());
        model.addAttribute("coursesList", courseService.getAllCourse());

        model.addAttribute("errorMessage", "Course added successfully");
        model.addAttribute("showErrorMessage", true);
        return "adminHome";
    }

    @RequestMapping(value = "/assign-course", method = RequestMethod.POST)
    public String assignCourse(@ModelAttribute("userCourse") UserCourse course, Model model) {
        try {
            courseService.assignCourse(course);
            model.addAttribute("errorMessage", "Course assigned successfully");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        model.addAttribute("course", new Course());
        model.addAttribute("userCourse", new UserCourse());
        model.addAttribute("teachersList", userService.findAllTeacher());
        model.addAttribute("coursesList", courseService.getAllCourse());
        model.addAttribute("showErrorMessage", true);
        return "adminHome";
    }
}
