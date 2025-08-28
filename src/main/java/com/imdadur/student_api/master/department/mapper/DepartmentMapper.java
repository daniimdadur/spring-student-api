package com.imdadur.student_api.master.department.mapper;

import com.imdadur.student_api.exception.DuplicateException;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.model.StudentRes;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {
    private final DepartmentRepo departmentRepo;

    public DepartmentRes toResponse(DepartmentEntity entity) {
        return DepartmentRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .students(toStudentResList(entity.getStudents()))
                .lecturers(toLecturerResList(entity.getLecturers()))
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
                .created(LocalDateTime.now())
                .build();
    }

    public DepartmentEntity toEntity(DepartmentReq request, DepartmentEntity entity) {
        validateDepartment(request);

        return DepartmentEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .created(entity.getCreated())
                .build();
    }

    private List<StudentRes> toStudentResList(List<StudentEntity> entities) {
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

    private List<LecturerRes> toLecturerResList(List<LecturerEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(lecturer -> LecturerRes.builder()
                .id(lecturer.getId())
                .name(lecturer.getName())
                .email(lecturer.getEmail())
                .title(lecturer.getTitle())
                .departmentId(lecturer.getDepartment().getId())
                .departmentName(lecturer.getDepartment().getName())
                .build()
        ).collect(Collectors.toList());
    }

    private void validateDepartment(DepartmentReq req) {
        if (this.departmentRepo.existsByName(req.getName())) {
            throw new DuplicateException(String.format("department name %s already exists", req.getName()));
        }
    }
}
