package com.imdadur.student_api.master.department.service;

import com.imdadur.student_api.exception.BusinessException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.model.StudentRes;
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
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    @Override
    public List<DepartmentRes> get() {
        List<DepartmentEntity> result = departmentRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentRes> getById(String id) {
        DepartmentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<DepartmentRes> save(DepartmentReq request) {
        DepartmentEntity result = this.convertReqToEntity(request);

        try {
            this.departmentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DepartmentRes> update(DepartmentReq request, String id) {
        DepartmentEntity result = this.getEntityById(id);
        validateDepartment(request);
        BeanUtils.copyProperties(request, result);

        try {
            this.departmentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DepartmentRes> delete(String id) {
        DepartmentEntity result = this.getEntityById(id);

        try {
            this.departmentRepo.delete(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private DepartmentRes convertEntityToRes(DepartmentEntity entity) {
        DepartmentRes result = new DepartmentRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getStudents() != null) {
            result.setStudents(convertEntityToStudentRes(entity.getStudents()));
        }

        return result;
    }

    private DepartmentEntity getEntityById(String id) {
        DepartmentEntity result = this.departmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("department with id " + id + " not found"));

        return result;
    }

    private DepartmentEntity convertReqToEntity(DepartmentReq req) {
        DepartmentEntity result = new DepartmentEntity();
        validateDepartment(req);
        BeanUtils.copyProperties(req, result);
        result.setId(CommonUtil.getUUID());
        return result;
    }

    private void validateDepartment(DepartmentReq req) {
        if (this.departmentRepo.existsByName(req.getName())) {
            throw new BusinessException(String.format("department name %s already exists", req.getName()));
        }
    }

    private List<StudentRes> convertEntityToStudentRes(List<StudentEntity> students) {
        List<StudentRes> result = new ArrayList<>();
        for (StudentEntity studentEntity : students) {
            StudentRes studentRes = new StudentRes();
            BeanUtils.copyProperties(studentEntity, studentRes);
            studentRes.setDepartmentId(studentEntity.getDepartment().getId());
            studentRes.setDepartmentName(studentEntity.getDepartment().getName());
            result.add(studentRes);
        }

        return result;
    }
}
