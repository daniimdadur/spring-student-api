package com.imdadur.student_api.master.lecturer.mapper;

import com.imdadur.student_api.exception.BadRequestException;
import com.imdadur.student_api.exception.DuplicateException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;
import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.lecturer.repo.LecturerRepo;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LecturerMapper {
    private final LecturerRepo lecturerRepo;
    private final DepartmentRepo departmentRepo;

    public LecturerRes toResponse(LecturerEntity entity) {
        return LecturerRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .title(entity.getTitle())
                .departmentId(entity.getDepartment().getId())
                .departmentName(entity.getDepartment().getName())
                .lecturerCourses(toLeCouResponse(entity.getLecturerCourses()))
                .build();
    }

    public List<LecturerRes> toListResponse(List<LecturerEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public LecturerEntity toEntity(LecturerReq request) {
        validateRequest(request);
        DepartmentEntity department = this.toDepartmentEntity(request.getDepartmentId());

        return LecturerEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .email(request.getEmail())
                .title(request.getTitle())
                .created(LocalDateTime.now())
                .department(department)
                .build();
    }

    public LecturerEntity toEntity(LecturerReq request, LecturerEntity entity) {
        validateRequest(request, entity);
        DepartmentEntity department = this.toDepartmentEntity(request.getDepartmentId());

        return LecturerEntity.builder()
                .id((entity.getId()))
                .name(request.getName())
                .email(entity.getEmail())
                .title(request.getTitle())
                .created(entity.getCreated())
                .department(department)
                .build();
    }

    private List<LeCourseRes> toLeCouResponse(List<LeCourseEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(entity -> LeCourseRes.builder()
                .id(entity.getId())
                .lecturerId(entity.getLecturer().getId())
                .lecturerName(entity.getLecturer().getName())
                .courseId(entity.getCourse().getId())
                .courseName(entity.getCourse().getName())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build()
        ).collect(Collectors.toList());
    }

    private void validateRequest(LecturerReq request, LecturerEntity entity) {
        if (!request.getEmail().equals(entity.getEmail())) {
            throw new BadRequestException("email can't be changed once set");
        }
    }

    private void validateRequest(LecturerReq request) {
        if (this.lecturerRepo.existsByEmail(request.getEmail())) {
            throw new DuplicateException("email already exists");
        }
    }

    private DepartmentEntity toDepartmentEntity(String id) {
        return this.departmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("department not found"));
    }
}
