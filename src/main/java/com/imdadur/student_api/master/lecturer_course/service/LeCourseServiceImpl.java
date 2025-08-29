package com.imdadur.student_api.master.lecturer_course.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.lecturer_course.mapper.LeCourseMapper;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.master.lecturer_course.repo.LeCourseRepo;
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
        return this.mapper.toResponseList(this.leCourseRepo.findAllByOrderByCreatedAsc());
    }

    @Override
    public Optional<LeCourseRes> getById(String id) {
        LeCourseEntity result = this.getEntityById(id);

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
    public Optional<LeCourseRes> update(LeCourseReq request, String id) {
        LeCourseEntity entity = this.getEntityById(id);

        if (this.mapper.isSameId(request, entity)) {
            entity.setRole(request.getRole());
            entity.setStatus(request.getStatus());

            try {
                this.leCourseRepo.save(entity);
                return Optional.of(this.mapper.toResponse(entity));
            } catch (Exception e) {
                throw new RuntimeException("error updating lecturer course", e);
            }
        } else {
            LeCourseEntity result = this.mapper.toEntity(request, entity);

            try {
                this.leCourseRepo.save(result);
                return Optional.of(this.mapper.toResponse(result));
            } catch (Exception e) {
                throw new RuntimeException("error updating lecturer course", e);
            }
        }
    }

    @Override
    public Optional<LeCourseRes> delete(String id) {
        LeCourseEntity result = this.getEntityById(id);

        try {
            this.leCourseRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error deleting lecturer course", e);
        }
    }

    private LeCourseEntity getEntityById(String id) {
        return this.leCourseRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("lecturer course with id %s not found", id)
                ));
    }
}
