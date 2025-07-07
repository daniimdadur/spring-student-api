package com.imdadur.student_api.master.student.repo;

import com.imdadur.student_api.master.student.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, String> {
    boolean existsByEmail(String email);
    Optional<StudentEntity> findByEmail(String email);
}
