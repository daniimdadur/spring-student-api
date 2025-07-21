package com.imdadur.student_api.master.department.mapper;

import com.imdadur.student_api.exception.BusinessException;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.model.StudentRes;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {
    private final DepartmentRepo departmentRepo;

    public DepartmentRes toResponse(DepartmentEntity entity) {
        List<StudentRes> studentResList = this.toStudentResList(entity.getStudents());
        return DepartmentRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .students(studentResList)
                .build();
    }

    public List<DepartmentRes> toResponseList(List<DepartmentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public DepartmentEntity toEntity(DepartmentReq request) {
        validateDepartment(request);

        return DepartmentEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .build();
    }

    public DepartmentEntity toEntity(DepartmentReq request, DepartmentEntity entity) {
        validateDepartment(request);

        return DepartmentEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .build();
    }

    public List<StudentRes> toStudentResList(List<StudentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(student -> StudentRes.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .departmentId(student.getDepartment().getId())
                .departmentName(student.getDepartment().getName())
                .build()
        ).collect(Collectors.toList());
    }

    private void validateDepartment(DepartmentReq req) {
        if (this.departmentRepo.existsByName(req.getName())) {
            throw new BusinessException(String.format("department name %s already exists", req.getName()));
        }
    }
}
