package com.imdadur.student_api.master.lecturer.service;

import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;

import java.util.List;
import java.util.Optional;

public interface LecturerService {
    List<LecturerRes> get();
    Optional<LecturerRes> getById(String id);
    Optional<LecturerRes> save(LecturerReq request);
    Optional<LecturerRes> update(LecturerReq request, String id);
    Optional<LecturerRes> delete(String id);
}
