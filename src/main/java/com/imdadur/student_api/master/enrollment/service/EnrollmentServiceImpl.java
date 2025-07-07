package com.imdadur.student_api.master.enrollment.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepo enrollmentRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Override
    public List<EnrollmentRes> get() {
        List<EnrollmentEntity> result = enrollmentRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<EnrollmentRes> getById(String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);

        return Optional.of(convertEntityToRes(result));
    }

    @Override
    public Optional<EnrollmentRes> save(EnrollmentReq request) {
        EnrollmentEntity result = this.convertReqToEntity(request);

        try {
            this.enrollmentRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<EnrollmentRes> update(EnrollmentReq request, String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);
        convertReqToEntity(request, result);

        try {
            this.enrollmentRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<EnrollmentRes> delete(String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);

        try {
            this.enrollmentRepo.delete(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private EnrollmentRes convertEntityToRes(EnrollmentEntity entity) {
        EnrollmentRes result = new EnrollmentRes();
        BeanUtils.copyProperties(entity, result);
        if (entity.getCourseId() != null) {
            result.setCourseId(entity.getCourse().getId());
            result.setCourseName(entity.getCourse().getName());
        }
        if (entity.getStudentId() != null) {
            result.setStudentId(entity.getStudent().getId());
            result.setStudentName(entity.getStudent().getName());
        }

        return result;
    }

    private EnrollmentEntity getEntityById(String studentId, String courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);

        EnrollmentEntity result = this.enrollmentRepo.existsById(enrollmentId)
                .orElseThrow(() -> new NotFoundException(String.format("Enrollment with id %s not found", enrollmentId)));

        return result;
    }

    private EnrollmentEntity convertReqToEntity(EnrollmentReq req) {
        StudentEntity student = studentRepo.findById(req.getStudentId())
                .orElseThrow(() -> new NotFoundException("student with id " + req.getStudentId() + " not found"));

        CourseEntity course = courseRepo.findById(req.getCourseId())
                .orElseThrow(() -> new NotFoundException("course with id " + req.getCourseId() + " not found"));

        EnrollmentEntity result = new EnrollmentEntity();
        BeanUtils.copyProperties(req, result);
        result.setStudent(student);
        result.setCourse(course);
        return result;
    }

    private void convertReqToEntity(EnrollmentReq req, EnrollmentEntity entity) {
        StudentEntity student = studentRepo.findById(req.getStudentId())
                .orElseThrow(() -> new NotFoundException("student with id " + req.getStudentId() + " not found"));

        CourseEntity course = courseRepo.findById(req.getCourseId())
                .orElseThrow(() -> new NotFoundException("course with id " + req.getCourseId() + " not found"));

        BeanUtils.copyProperties(req, entity);
        entity.setStudent(student);
        entity.setCourse(course);
    }
}
