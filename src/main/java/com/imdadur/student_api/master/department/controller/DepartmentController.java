package com.imdadur.student_api.master.department.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.department.model.DepartmentReq;
import com.imdadur.student_api.master.department.model.DepartmentRes;
import com.imdadur.student_api.master.department.service.DepartmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/department")
public class DepartmentController extends BaseController<DepartmentRes> {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<DepartmentRes> result = this.departmentService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<DepartmentRes> result = this.departmentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid DepartmentReq request) {
        Optional<DepartmentRes> result = this.departmentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> path(@RequestBody @Valid DepartmentReq request, @PathVariable @NotBlank String id){
        Optional<DepartmentRes> result = this.departmentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<DepartmentRes> result = this.departmentService.delete(id);
        return getResponse(result);
    }
}
