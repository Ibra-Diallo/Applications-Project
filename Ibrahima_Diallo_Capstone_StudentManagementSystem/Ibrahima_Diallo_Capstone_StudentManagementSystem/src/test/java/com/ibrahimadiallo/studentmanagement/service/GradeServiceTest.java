package com.ibrahimadiallo.studentmanagement.service;

import com.ibrahimadiallo.studentmanagement.model.Course;
import com.ibrahimadiallo.studentmanagement.model.Grade;
import com.ibrahimadiallo.studentmanagement.model.GradeDTO;
import com.ibrahimadiallo.studentmanagement.model.User;
import com.ibrahimadiallo.studentmanagement.repository.CourseRepository;
import com.ibrahimadiallo.studentmanagement.repository.GradeRepository;
import com.ibrahimadiallo.studentmanagement.repository.UserRepository;
import com.ibrahimadiallo.studentmanagement.service.GradeService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserRepository userRepository;

    private GradeService gradeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        gradeService = new GradeService(gradeRepository, courseRepository, userRepository);
    }

    @Test
    public void testFindStudentGrades() {
        List<Grade> gradeList = new ArrayList<>();
        Grade grade = new Grade();
        grade.setTeacherId(1);
        grade.setCourseId(1);

        gradeList.add(grade);

        Mockito.when(gradeRepository.findAllByUserId(anyLong())).thenReturn(gradeList);
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.of(new Course()));

        List<GradeDTO> result = gradeService.findStudentGrades(new User());

        Assertions.assertEquals(1, result.size());
    }
}
