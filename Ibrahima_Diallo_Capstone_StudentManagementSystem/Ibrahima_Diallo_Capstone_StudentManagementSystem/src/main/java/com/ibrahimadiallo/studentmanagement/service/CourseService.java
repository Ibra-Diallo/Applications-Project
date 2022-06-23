package com.ibrahimadiallo.studentmanagement.service;

import com.ibrahimadiallo.studentmanagement.exception.CourseAlreadyAssignedException;
import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.model.UserCourse;
import com.ibrahimadiallo.studentmanagement.repository.CourseRepository;
import com.ibrahimadiallo.studentmanagement.repository.UserCourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private UserCourseRepository userCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserCourseRepository userCourseRepository) {
        this.courseRepository = courseRepository;
        this.userCourseRepository = userCourseRepository;
    }

    public void addCourse(Course course) {
        if (course.getStartDate().getMonth() == Month.DECEMBER ||
                course.getStartDate().getMonth() == Month.JANUARY ||
                course.getStartDate().getMonth() == Month.FEBRUARY) {
            course.setSemester("WINTER");
        } else if (course.getStartDate().getMonth() == Month.MARCH ||
                course.getStartDate().getMonth() == Month.APRIL ||
                course.getStartDate().getMonth() == Month.MAY
        ) {
            course.setSemester("SPRING");
        } else if (course.getStartDate().getMonth() == Month.JUNE ||
                course.getStartDate().getMonth() == Month.JULY ||
                course.getStartDate().getMonth() == Month.AUGUST) {
            course.setSemester("SUMMER");
        } else {
            course.setSemester("FALL");
        }
        courseRepository.save(course);
    }

    public void assignCourse(UserCourse course) {
        Optional<UserCourse> byUserIdAndCourseId = userCourseRepository.findByUserIdAndCourseId(course.getUserId(), course.getCourseId());
        if (byUserIdAndCourseId.isPresent()) {
            throw new CourseAlreadyAssignedException("Teacher has this course already assigned!");
        }
        userCourseRepository.save(course);
    }

    public List<Course> getAllCourseBasedOnUserId(long userId) {
        List<UserCourse> byUserId = userCourseRepository.findByUserId(userId);
        List<Course> courses = new ArrayList<>();
        for (UserCourse userCourse : byUserId) {
            Optional<Course> byId = courseRepository.findById(userCourse.getCourseId());
            if (byId.isPresent()) {
                courses.add(byId.get());
            }
        }
        return courses;
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }
}
