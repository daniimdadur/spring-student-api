package com.imdadur.student_api.master.department.service;

import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<DepartmentRes> get();
    Optional<DepartmentRes> getById(String id);
    Optional<DepartmentRes> save(DepartmentReq request);
    Optional<DepartmentRes> update(DepartmentReq request, String id);
    Optional<DepartmentRes> delete(String id);
}
