package com.imdadur.student_api.master.student.service;

import com.imdadur.student_api.exception.BusinessException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.model.StudentReq;
import com.imdadur.student_api.master.student.model.StudentRes;
import com.imdadur.student_api.master.student.repo.StudentRepo;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final DepartmentRepo departmentRepo;

    @Override
    public List<StudentRes> get() {
        List<StudentEntity> result = this.studentRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        StudentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) {
        StudentEntity result = this.convertReqToEntity(request);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        StudentEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        StudentEntity result = this.getEntityById(id);

        try {
            this.studentRepo.delete(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private StudentRes convertEntityToRes(StudentEntity entity) {
        StudentRes result = new StudentRes();
        BeanUtils.copyProperties(entity, result);
        if (entity.getDepartment().getId() != null) {
            result.setDepartmentId(entity.getDepartment().getId());
            result.setDepartmentName(entity.getDepartment().getName());
        }
        if (entity.getEnrollments() != null) {
            result.setEnrollments(convertEnrollmentEntityToRes(entity.getEnrollments()));
        }

        return result;
    }

    private StudentEntity getEntityById(String id) {

        return this.studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("student with id " + id + " not found"));
    }

    private StudentEntity convertReqToEntity(StudentReq req) {
        DepartmentEntity department = departmentRepo.findById(req.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("Department with id " + req.getDepartmentId() + " not found"));
        validateStudent(req);

        StudentEntity result = new StudentEntity();
        BeanUtils.copyProperties(req, result);
        result.setId(CommonUtil.getUUID());
        result.setDepartment(department);
        return result;
    }

    private void convertReqToEntity(StudentReq request, StudentEntity entity) {
        validateStudent(request);
        BeanUtils.copyProperties(request, entity);

        DepartmentEntity department = departmentRepo.findById(request.getDepartmentId())
                .orElseThrow(() -> new NotFoundException("department with id " + request.getDepartmentId() + " not found"));
        entity.setDepartment(department);
    }

    private void validateStudent(StudentReq request) {
        this.studentRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(String.format("student with email %s already exists", request.getEmail())));
    }

    private List<EnrollmentRes> convertEnrollmentEntityToRes(List<EnrollmentEntity> enrollments) {
        List<EnrollmentRes> result = new ArrayList<>();
        for (EnrollmentEntity enrollmentEntity : enrollments) {
            EnrollmentRes res = new EnrollmentRes();
            BeanUtils.copyProperties(enrollmentEntity, res);
            res.setStudentId(enrollmentEntity.getStudent().getId());
            res.setStudentName(enrollmentEntity.getStudent().getName());
            res.setCourseId(enrollmentEntity.getCourse().getId());
            res.setCourseName(enrollmentEntity.getCourse().getName());
            result.add(res);
        }

        return result;
    }
}
