package com.imdadur.student_api.master.student.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.student.mapper.StudentMapper;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.model.StudentReq;
import com.imdadur.student_api.master.student.model.StudentRes;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final StudentMapper mapper;

    @Override
    public List<StudentRes> get() {
        return this.mapper.toListResponse(this.studentRepo.findAll());
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        StudentEntity result = this.getEntityById(id);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) {
        StudentEntity result = this.mapper.toEntity(request);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error saving student", e);
        }
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        StudentEntity entity = this.getEntityById(id);
        StudentEntity result = this.mapper.toEntity(request, entity);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error updating student", e);
        }
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        StudentEntity result = this.getEntityById(id);

        try {
            this.studentRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("error deleting student", e);
        }
    }

    private StudentEntity getEntityById(String id) {
        return this.studentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("student with id %s not found", id)));
    }
}
