package com.imdadur.student_api.master.department.service;

import com.imdadur.student_api.exception.NotFoundException;
import com.imdadur.student_api.master.department.mapper.DepartmentMapper;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentRes> get() {
        return this.mapper.toResponseList(this.departmentRepo.findAll());
    }

    @Override
    public Optional<DepartmentRes> getById(String id) {
        DepartmentEntity result = this.getEntityById(id);

        return Optional.of(this.mapper.toResponse(result));
    }

    @Override
    public Optional<DepartmentRes> save(DepartmentReq request) {
        DepartmentEntity result = this.mapper.toEntity(request);

        try {
            this.departmentRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("save department failed", e);
        }
    }

    @Override
    public Optional<DepartmentRes> update(DepartmentReq request, String id) {
        DepartmentEntity entity = this.getEntityById(id);
        DepartmentEntity result = this.mapper.toEntity(request, entity);

        try {
            this.departmentRepo.save(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("update department failed", e);
        }
    }

    @Override
    public Optional<DepartmentRes> delete(String id) {
        DepartmentEntity result = this.getEntityById(id);

        try {
            this.departmentRepo.delete(result);
            return Optional.of(this.mapper.toResponse(result));
        } catch (Exception e) {
            throw new RuntimeException("delete department failed", e);
        }
    }

    private DepartmentEntity getEntityById(String id) {

        return this.departmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("department with id %s not found", id)));
    }
}
