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

    @GetMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response> get(@PathVariable(value = "studentId") @NotBlank String  studentId,
                                        @PathVariable(value = "courseId") @NotBlank String courseId) {
        Optional<EnrollmentRes> result = this.enrollmentService.getById(studentId, courseId);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid EnrollmentReq request) {
        Optional<EnrollmentRes> result = this.enrollmentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response> patch(@PathVariable(value = "studentId") @NotBlank String studentId,
                                          @PathVariable(value = "courseId") @NotBlank String courseId,
                                          @RequestBody @Valid EnrollmentReq request) {
        Optional<EnrollmentRes> result = this.enrollmentService.update(request, studentId, courseId);
        return getResponse(result);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Response> delete(@PathVariable(value = "studentId") @NotBlank String  studentId,
                                           @PathVariable(value = "courseId") @NotBlank String courseId) {
        Optional<EnrollmentRes> result = this.enrollmentService.delete(studentId, courseId);
        return getResponse(result);
    }
}
