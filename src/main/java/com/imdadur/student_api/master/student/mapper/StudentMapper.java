package com.imdadur.student_api.master.student.mapper;

import com.imdadur.student_api.exception.DuplicateException;
import com.imdadur.student_api.exception.BadRequestException;
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
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {
    private final StudentRepo studentRepo;
    private final DepartmentRepo departmentRepo;

    public StudentRes toResponse(StudentEntity entity) {
        List<EnrollmentRes> enrollmentResList = this.toEnrollmentResponse(entity.getEnrollments());

        return StudentRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .departmentId(entity.getDepartment().getId())
                .departmentName(entity.getDepartment().getName())
                .enrollments(enrollmentResList)
                .build();
    }

    public List<StudentRes> toListResponse(List<StudentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public StudentEntity toEntity(StudentReq request) {
        validateStudent(request);
        DepartmentEntity department = this.toDepartmentEntity(request.getDepartmentId());

        return StudentEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .build();
    }

    public StudentEntity toEntity(StudentReq request, StudentEntity entity) {
        validateStudent(request, entity);
        DepartmentEntity department = this.toDepartmentEntity(request.getDepartmentId());

        return StudentEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .email(entity.getEmail())
                .department(department)
                .build();
    }

    private List<EnrollmentRes> toEnrollmentResponse(List<EnrollmentEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(enrollment -> EnrollmentRes.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getName())
                .courseId(enrollment.getCourse().getId())
                .courseName(enrollment.getCourse().getName())
                .grade(enrollment.getGrade())
                .build()
        ).collect(Collectors.toList());
    }

    private void validateStudent(StudentReq request, StudentEntity entity) {
        if (!request.getEmail().equals(entity.getEmail())) {
            throw new BadRequestException("email can't be changed once set");
        }
    }

    private void validateStudent(StudentReq request) {
        if (this.studentRepo.existsByEmail(request.getEmail())) {
            throw new DuplicateException(String.format("student with email %s already exists", request.getEmail()));
        }
    }

    private DepartmentEntity toDepartmentEntity(String id) {
        return this.departmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Department with id %s not found", id)));
    }
}
