package com.imdadur.student_api.master.enrollment.mapper;

import com.imdadur.student_api.exception.BusinessException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.enrollment.validator.EnrollmentValidator;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final EnrollmentValidator validator;

    public EnrollmentRes toResponse(EnrollmentEntity entity) {
        return EnrollmentRes.builder()
                .id(entity.getId())
                .studentId(entity.getStudent().getId())
                .studentName(entity.getStudent().getName())
                .courseId(entity.getCourse().getId())
                .courseName(entity.getCourse().getName())
                .grade(entity.getGrade())
                .build();
    }

    public EnrollmentEntity toEntity(EnrollmentReq request) {
        if (this.validator.isDuplicate(request)) {
            throw new BusinessException("enrollment already exists");
        }

        StudentEntity student = studentRepo.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException(String.format("student with id %s not found", request.getStudentId())));

        CourseEntity course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new NotFoundException(String.format("course with id %s not found", request.getCourseId())));

        return EnrollmentEntity.builder()
                .id(new EnrollmentId(student.getId(), course.getId()))
                .student(student)
                .course(course)
                .grade(request.getGrade())
                .build();
    }

    public List<EnrollmentRes> toResponseList(List<EnrollmentEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
