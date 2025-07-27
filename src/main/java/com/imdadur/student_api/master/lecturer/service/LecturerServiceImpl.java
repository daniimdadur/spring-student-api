package com.imdadur.student_api.master.lecturer.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.lecturer.mapper.LecturerMapper;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;
import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.lecturer.repo.LecturerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepo lecturerRepo;
    private final LecturerMapper mapper;

    @Override
    public List<LecturerRes> get() {
        return this.mapper.toListResponse(this.lecturerRepo.findAll());
    }

    @Override
    public Optional<LecturerRes> getById(String id) {
        LecturerEntity result = this.getEntityById(id);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<LecturerRes> save(LecturerReq request) {
        LecturerEntity result = this.mapper.toEntity(request);

        try {
            this.lecturerRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error saving lecturer", e);
        }
    }

    @Override
    public Optional<LecturerRes> update(LecturerReq request, String id) {
        LecturerEntity entity = this.getEntityById(id);
        LecturerEntity result = this.mapper.toEntity(request, entity);

        try {
            this.lecturerRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error updating lecturer", e);
        }
    }

    @Override
    public Optional<LecturerRes> delete(String id) {
        LecturerEntity result = this.getEntityById(id);

        try {
            this.lecturerRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error deleting lecturer", e);
        }
    }

    private LecturerEntity getEntityById(String id) {
        return this.lecturerRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Lecturer with id %s not found", id)));
    }
}
