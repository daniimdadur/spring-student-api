package com.imdadur.student_api.master.lecturer.repo;

import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturerRepo extends JpaRepository<LecturerEntity, String> {
    boolean existsByEmail(String email);
    List<LecturerEntity> findAllByOrderByCreatedAsc();
}
