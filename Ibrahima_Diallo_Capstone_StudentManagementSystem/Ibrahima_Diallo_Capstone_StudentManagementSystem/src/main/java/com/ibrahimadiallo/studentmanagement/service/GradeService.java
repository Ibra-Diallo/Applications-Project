package com.ibrahimadiallo.studentmanagement.service;

import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.model.Grade;
import com.ibrahimadiallo.studentmanagement.model.GradeDTO;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.repository.CourseRepository;
import com.ibrahimadiallo.studentmanagement.repository.GradeRepository;
import com.ibrahimadiallo.studentmanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private GradeRepository gradeRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public void addGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    public List<GradeDTO> findStudentGrades(User user) {
        List<Grade> grades = gradeRepository.findAllByUserId(user.getId());
        List<GradeDTO> result = new ArrayList<>();

        for (Grade grade : grades) {
            GradeDTO gradeDTO = new GradeDTO();
            gradeDTO.setGrade(grade.getGrade());
            gradeDTO.setFeedback(grade.getFeedback());
            Optional<User> teacher = userRepository.findById(grade.getTeacherId());
            gradeDTO.setTeacherName(teacher.get().getName());
            Optional<Course> course = courseRepository.findById(grade.getCourseId());
            gradeDTO.setCourseDescription(course.get().getDescription());
            gradeDTO.setCourseName(course.get().getName());
            gradeDTO.setCourseSemester(course.get().getSemester());

            result.add(gradeDTO);
        }

        return result;
    }
}
