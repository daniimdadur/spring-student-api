package com.imdadur.student_api.master.course.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.course.model.CourseReq;
import com.imdadur.student_api.master.course.model.CourseRes;
import com.imdadur.student_api.master.course.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController<CourseRes> {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<CourseRes> result = this.courseService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @NotBlank String id) {
        Optional<CourseRes> result = this.courseService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid CourseReq request) {
        Optional<CourseRes> result = this.courseService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable @NotBlank String id, @RequestBody @Valid CourseReq request) {
        Optional<CourseRes> result = this.courseService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<CourseRes> result = this.courseService.delete(id);
        return getResponse(result);
    }
}
