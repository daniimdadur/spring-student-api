package com.imdadur.student_api.master.enrollment.service;

import com.imdadur.student_api.exception.BusinessException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.enrollment.mapper.EnrollmentMapper;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import com.imdadur.student_api.master.enrollment.validator.EnrollmentValidator;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import com.imdadur.student_api.util.CommonUtil;
import jakarta.transaction.Transactional;
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
    private final EnrollmentValidator validator;
    private final EnrollmentMapper mapper;

    @Override
    public List<EnrollmentRes> get() {
        return this.mapper.toResponseList(this.enrollmentRepo.findAll());
    }

    @Override
    public Optional<EnrollmentRes> getById(String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<EnrollmentRes> save(EnrollmentReq request) {
        EnrollmentEntity result = this.mapper.toEntity(request);

        try {
            this.enrollmentRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("save enrollment failed");
        }
    }

    @Override
    @Transactional
    public Optional<EnrollmentRes> update(EnrollmentReq request, String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);

        if (this.validator.isSameId(request, result)) {
            result.setGrade(request.getGrade());

            try {
                this.enrollmentRepo.save(result);
                return Optional.of(this.mapper.toResponse(result));
            } catch (Exception e) {
                throw new RuntimeException("enrollment update failed");
            }
        } else {
            EnrollmentEntity enrollment = this.mapper.toEntity(request);
            try {
                this.enrollmentRepo.delete(result);
                this.enrollmentRepo.save(enrollment);
                return Optional.of(this.mapper.toResponse(enrollment));
            } catch (Exception e) {
                throw new RuntimeException("enrollment update failed");
            }
        }
    }

    @Override
    public Optional<EnrollmentRes> delete(String studentId, String courseId) {
        EnrollmentEntity result = this.getEntityById(studentId, courseId);

        try {
            this.enrollmentRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("delete enrollment failed");
        }
    }

    private EnrollmentEntity getEntityById(String studentId, String courseId) {
        EnrollmentId enrollmentId = new EnrollmentId(studentId, courseId);

        return this.enrollmentRepo.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("enrollment with id %s/%s not found", enrollmentId.getStudentId(), enrollmentId.getCourseId())
                ));
    }
}