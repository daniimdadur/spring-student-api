package com.imdadur.student_api.master.lecturer_course.repo;

import com.imdadur.student_api.master.lecturer_course.model.LeCouEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCouId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeCouRepo extends JpaRepository<LeCouEntity, LeCouId> {
}
