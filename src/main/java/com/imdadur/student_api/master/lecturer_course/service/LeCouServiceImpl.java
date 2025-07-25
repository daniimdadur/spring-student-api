package com.imdadur.student_api.master.lecturer_course.service;

import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;

import java.util.List;
import java.util.Optional;

public class LeCouServiceImpl implements LeCouService {
    @Override
    public List<LecturerEntity> get() {
        return List.of();
    }

    @Override
    public Optional<LecturerEntity> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<LecturerEntity> save(LecturerReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<LecturerEntity> update(LecturerReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<LecturerEntity> delete(String id) {
        return Optional.empty();
    }
}
