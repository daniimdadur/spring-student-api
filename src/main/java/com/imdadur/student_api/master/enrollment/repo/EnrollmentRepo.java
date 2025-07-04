package com.imdadur.student_api.master.enrollment.repo;

import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<EnrollmentEntity, String > {
}
