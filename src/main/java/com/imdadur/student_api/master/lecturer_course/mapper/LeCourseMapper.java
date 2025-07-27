package com.imdadur.student_api.master.lecturer_course.mapper;

import com.imdadur.student_api.exception.DuplicateException;
import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.repo.LecturerRepo;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseId;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.master.lecturer_course.repo.LeCourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LeCourseMapper {
    private final CourseRepo courseRepo;
    private final LecturerRepo lecturerRepo;
    private final LeCourseRepo leCouRepo;

    public LeCourseRes toResponse(LeCourseEntity entity) {
        return LeCourseRes.builder()
                .id(entity.getId())
                .lecturerId(entity.getLecturer().getId())
                .lecturerName(entity.getLecturer().getName())
                .courseId(entity.getCourse().getId())
                .courseName(entity.getCourse().getName())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

    public LeCourseEntity toEntity(LeCourseReq request) {
        if (this.isDuplicate(request)) {
            throw new DuplicateException("leCourse already exists");
        }

        LecturerEntity lecturer = this.getLecturer(request.getLecturerId());
        CourseEntity course = this.getCourse(request.getCourseId());
        return LeCourseEntity.builder()
                .id(new LeCourseId(lecturer.getId(), course.getId()))
                .lecturer(lecturer)
                .course(course)
                .role(request.getRole())
                .status(request.getStatus())
                .build();
    }

    public List<LeCourseRes> toResponseList(List<LeCourseEntity> entities) {
        if (entities == null || entities.isEmpty()) return Collections.emptyList();

        return entities.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private CourseEntity getCourse(String id) {
        return this.courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("course with id %s not found", id)));
    }

    private LecturerEntity getLecturer(String id) {
        return this.lecturerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("lecturer with id %s not found", id)));
    }

    private boolean isDuplicate(LeCourseReq request) {
        return this.leCouRepo.existsById(new LeCourseId(request.getLecturerId(), request.getCourseId()));
    }

    public boolean isSameId(LeCourseReq request, LeCourseEntity entity) {
        return request.getLecturerId().equals(entity.getLecturer().getId()) &&
                request.getCourseId().equals(entity.getCourse().getId());
    }
}
