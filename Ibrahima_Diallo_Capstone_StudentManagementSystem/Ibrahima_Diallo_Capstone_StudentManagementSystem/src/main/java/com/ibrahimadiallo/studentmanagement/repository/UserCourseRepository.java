package com.ibrahimadiallo.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibrahimadiallo.studentmanagement.model.UserCourse;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    Optional<UserCourse> findByUserIdAndCourseId(long userId, long courseId);
    List<UserCourse> findByUserId(long userId);
}
