package com.imdadur.student_api.master.lecturer_course.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.lecturer_course.mapper.LeCourseMapper;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseId;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.master.lecturer_course.repo.LeCourseRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeCourseServiceImpl implements LeCourseService {
    private final LeCourseRepo leCourseRepo;
    private final LeCourseMapper mapper;

    @Override
    public List<LeCourseRes> get() {
        return this.mapper.toResponseList(this.leCourseRepo.findAll());
    }

    @Override
    public Optional<LeCourseRes> getById(String lecturerId, String courseId) {
        LeCourseEntity result = this.getEntityById(lecturerId, courseId);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<LeCourseRes> save(LeCourseReq request) {
        LeCourseEntity result = this.mapper.toEntity(request);
        try {
            this.leCourseRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error saving lecturer course", e);
        }
    }

    @Override
    @Transactional
    public Optional<LeCourseRes> update(LeCourseReq request, String lecturerId, String courseId) {
        LeCourseEntity result = this.getEntityById(lecturerId, courseId);

        if (this.mapper.isSameId(request, result)) {
            result.setRole(request.getRole());
            result.setStatus(request.getStatus());

            try {
                this.leCourseRepo.save(result);
                return Optional.of(this.mapper.toResponse(result));
            } catch (Exception e) {
                throw new RuntimeException("error updating lecturer course", e);
            }
        } else {
            LeCourseEntity newEntity = this.mapper.toEntity(request);
            try {
                this.leCourseRepo.delete(result);
                this.leCourseRepo.save(newEntity);
                return Optional.of(this.mapper.toResponse(newEntity));
            } catch (Exception e) {
                throw new RuntimeException("error updating lecturer course", e);
            }
        }
    }

    @Override
    public Optional<LeCourseRes> delete(String lecturerId, String courseId) {
        LeCourseEntity result = this.getEntityById(lecturerId, courseId);

        try {
            this.leCourseRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error deleting lecturer course", e);
        }
    }

    private LeCourseEntity getEntityById(String lecturerId, String courseId) {
        return this.leCourseRepo.findById(new LeCourseId(lecturerId, courseId))
                .orElseThrow(() -> new NotFoundException(
                        String.format("lecturer course with id %s/%s not found", lecturerId, courseId)
                ));
    }
}
