package com.imdadur.student_api.master.course.service;

import com.imdadur.student_api.master.course.model.CourseReq;
import com.imdadur.student_api.master.course.model.CourseRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get();
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
