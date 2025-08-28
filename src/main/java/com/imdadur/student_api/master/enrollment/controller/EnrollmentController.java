package com.imdadur.student_api.master.enrollment.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/enrollment")
public class EnrollmentController extends BaseController<EnrollmentRes> {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<EnrollmentRes> result = this.enrollmentService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable(value = "id") @NotBlank String  id) {
        Optional<EnrollmentRes> result = this.enrollmentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid EnrollmentReq request) {
        Optional<EnrollmentRes> result = this.enrollmentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable(value = "id") @NotBlank String id,
                                          @RequestBody @Valid EnrollmentReq request) {
        Optional<EnrollmentRes> result = this.enrollmentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable(value = "id") @NotBlank String  id) {
        Optional<EnrollmentRes> result = this.enrollmentService.delete(id);
        return getResponse(result);
    }
}
