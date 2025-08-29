package com.imdadur.student_api.master.lecturer_course.repo;

import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeCourseRepo extends JpaRepository<LeCourseEntity, String> {
    boolean existsByLecturerIdAndCourseId(String lecturerId, String courseId);
    List<LeCourseEntity> findAllByOrderByCreatedAsc();
}
