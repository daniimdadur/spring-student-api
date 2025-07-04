package com.imdadur.student_api.master.course.repo;

import com.imdadur.student_api.master.course.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, String> {
}
