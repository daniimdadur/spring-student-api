package com.imdadur.student_api.master.course.mapper;

import com.imdadur.student_api.exception.DuplicateException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.model.CourseReq;
import com.imdadur.student_api.master.course.model.CourseRes;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper {
    private final CourseRepo courseRepo;

    public CourseRes toResponse(CourseEntity entity) {
        List<EnrollmentRes> enrollmentResList = this.toEnrollmentResponse(entity.getEnrollments());

        return CourseRes.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .credit(entity.getCredit())
                .enrollments(enrollmentResList)
                .build();
    }

    public List<CourseRes> toListResponse(List<CourseEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CourseEntity toEntity(CourseReq request) {
        validateCourse(request);

        return CourseEntity.builder()
                .id(CommonUtil.getUUID())
                .name(request.getName())
                .code(request.getCode())
                .credit(request.getCredit())
                .build();
    }

    public CourseEntity toEntity(CourseReq request, CourseEntity entity) {
        validateCourse(request);

        return CourseEntity.builder()
                .id(entity.getId())
                .name(request.getName())
                .code(request.getCode())
                .credit(request.getCredit())
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

    private void validateCourse(CourseReq request) {
        if (this.courseRepo.existsByCode(request.getCode())) {
            throw new DuplicateException(String.format("code %s already exists", request.getCode()));
        }
        if (this.courseRepo.existsByName(request.getName())) {
            throw new DuplicateException(String.format("name %s already exists", request.getName()));
        }
    }
}
