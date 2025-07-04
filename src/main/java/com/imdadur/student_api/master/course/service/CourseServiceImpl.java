package com.imdadur.student_api.master.course.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.model.CourseReq;
import com.imdadur.student_api.master.course.model.CourseRes;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    @Override
    public List<CourseRes> get() {
        List<CourseEntity> result = this.courseRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.convertReqToEntity(request);

        try {
            this.courseRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.getEntityById(id);
        BeanUtils.copyProperties(request, result);

        try {
            this.courseRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        try {
            this.courseRepo.delete(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private CourseRes convertEntityToRes(CourseEntity entity) {
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);
        if (entity.getEnrollments() != null) {
            result.setEnrollments(convertEnrollmentEntityToRes(entity.getEnrollments()));
        }

        return result;
    }

    private CourseEntity getEntityById(String id) {
        CourseEntity result = this.courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("course with id " + id + " not found"));

        return result;
    }

    private CourseEntity convertReqToEntity(CourseReq req) {
        CourseEntity result = new CourseEntity();
        BeanUtils.copyProperties(req, result);
        result.setId(CommonUtil.getUUID());
        return result;
    }

    private List<EnrollmentRes> convertEnrollmentEntityToRes(List<EnrollmentEntity> enrollments) {
        List<EnrollmentRes> result = new ArrayList<>();

        for (EnrollmentEntity enrollmentEntity : enrollments) {
            EnrollmentRes res = new EnrollmentRes();
            BeanUtils.copyProperties(enrollmentEntity, res);
            res.setStudentName(enrollmentEntity.getStudent().getName());
            res.setCourseName(enrollmentEntity.getCourse().getName());
            result.add(res);
        }

        return result;
    }
}
