package com.university.service;

import com.university.model.Course;
import com.university.model.Grade;
import com.university.model.GradeDTO;
import com.university.model.User;
import com.university.repository.CourseRepository;
import com.university.repository.GradeRepository;
import com.university.repository.UserRepository;
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
