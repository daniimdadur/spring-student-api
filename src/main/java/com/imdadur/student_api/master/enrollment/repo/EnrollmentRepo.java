package com.imdadur.student_api.master.enrollment.repo;

import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepo extends JpaRepository<EnrollmentEntity, String> {
    boolean existsByStudentIdAndCourseId(@NotBlank String studentId, @NotBlank String courseId);
    List<EnrollmentEntity> findAllByOrderByCreatedAsc();
}
