package com.ibrahimadiallo.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrahimadiallo.studentmanagement.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
