package com.imdadur.student_api.master.lecturer_course.service;

import com.imdadur.student_api.master.lecturer.model.LecturerReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;

import java.util.List;
import java.util.Optional;

public interface LeCourseService {
    List<LeCourseRes> get();
    Optional<LeCourseRes> getById(String lecturerId, String courseId);
    Optional<LeCourseRes> save(LeCourseReq request);
    Optional<LeCourseRes> update(LeCourseReq request, String lecturerId, String courseId);
    Optional<LeCourseRes> delete(String lecturerId, String courseId);
}
