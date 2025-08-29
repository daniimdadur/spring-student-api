package com.imdadur.student_api.master.course.repo;

import com.imdadur.student_api.master.course.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, String> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
    boolean existsByCodeAndIdNot(String code, String id);
    boolean existsByNameAndIdNot(String name, String id);
    List<CourseEntity> findAllByOrderByCreatedAsc();
}
