package com.imdadur.student_api.master.lecturer_course.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.master.lecturer_course.service.LeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/lecourse")
public class LeCourseController extends BaseController<LeCourseRes> {
    private final LeCourseService leCourseService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<LeCourseRes> result = this.leCourseService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable("id") String id) {
        Optional<LeCourseRes> result = this.leCourseService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody LeCourseReq request) {
        Optional<LeCourseRes> result = this.leCourseService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable("id") String id,
                                          @RequestBody LeCourseReq request) {
        Optional<LeCourseRes> result = this.leCourseService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id) {
        Optional<LeCourseRes> result = this.leCourseService.delete(id);
        return getResponse(result);
    }
}
