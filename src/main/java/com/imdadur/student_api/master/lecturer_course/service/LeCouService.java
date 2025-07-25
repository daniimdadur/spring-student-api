package com.imdadur.student_api.master.lecturer_course.service;

import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;

import java.util.List;
import java.util.Optional;

public interface LeCouService {
    List<LecturerEntity> get();
    Optional<LecturerEntity> getById(String id);
    Optional<LecturerEntity> save(LecturerReq request);
    Optional<LecturerEntity> update(LecturerReq request, String id);
    Optional<LecturerEntity> delete(String id);
}
