package com.imdadur.student_api.master.department.repo;

import com.imdadur.student_api.master.department.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity, String> {
    List<DepartmentEntity> findAllByOrderByCreatedAsc();
    boolean existsByName(String name);
}
