package com.imdadur.student_api.master.department.repo;

import com.imdadur.student_api.master.department.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<DepartmentEntity, String> {
}
