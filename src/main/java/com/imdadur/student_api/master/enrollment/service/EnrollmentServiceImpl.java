package com.imdadur.student_api.master.enrollment.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.enrollment.mapper.EnrollmentMapper;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import com.imdadur.student_api.master.enrollment.validator.EnrollmentValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<EnrollmentRes> getById(String id) {
        EnrollmentEntity result = this.getEntityById(id);

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
    public Optional<EnrollmentRes> update(EnrollmentReq request, String id) {
        EnrollmentEntity result = this.getEntityById(id);

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
    public Optional<EnrollmentRes> delete(String id) {
        EnrollmentEntity result = this.getEntityById(id);

        try {
            this.enrollmentRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("delete enrollment failed");
        }
    }

    private EnrollmentEntity getEntityById(String id) {

        return this.enrollmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("enrollment with id %s not found", id)
                ));
    }
}