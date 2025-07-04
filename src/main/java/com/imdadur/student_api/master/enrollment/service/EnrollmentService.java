package com.imdadur.student_api.master.enrollment.service;

import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    List<EnrollmentRes> get();
    Optional<EnrollmentRes> getById(String  id);
    Optional<EnrollmentRes> save(EnrollmentReq request);
    Optional<EnrollmentRes> update(EnrollmentReq request, String id);
    Optional<EnrollmentRes> delete(String id);
}
