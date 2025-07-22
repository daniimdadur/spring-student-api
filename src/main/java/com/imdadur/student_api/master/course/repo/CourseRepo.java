package com.imdadur.student_api.master.course.repo;

import com.imdadur.student_api.master.course.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, String> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}
