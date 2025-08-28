package com.imdadur.student_api.master.course.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.course.mapper.CourseMapper;
import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.model.CourseReq;
import com.imdadur.student_api.master.course.model.CourseRes;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CourseMapper mapper;

    @Override
    public List<CourseRes> get() {
        return this.mapper.toListResponse(this.courseRepo.findAllByOrderByCreatedAsc());
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.mapper.toEntity(request);

        try {
            this.courseRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error saving course", e);
        }
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity entity = this.getEntityById(id);
        CourseEntity result = this.mapper.toEntity(request, entity);

        try {
            this.courseRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error updating course", e);
        }
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        try {
            this.courseRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error deleting course", e);
        }
    }

    private CourseEntity getEntityById(String id) {
        return this.courseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("course with id %s not found", id)));
    }
}
