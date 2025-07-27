package com.imdadur.student_api.master.lecturer_course.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseReq;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import com.imdadur.student_api.master.lecturer_course.service.LeCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lecourse")
public class LeCourseController extends BaseController<LeCourseRes> {
    private final LeCourseService leCourseService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<LeCourseRes> result = this.leCourseService.get();
        return getResponse(result);
    }

    @GetMapping("/{lecturerId}/{courseId}")
    public ResponseEntity<Response> get(@PathVariable("lecturerId") String lecturerId,
                                        @PathVariable("courseId") String  courseId) {
        Optional<LeCourseRes> result = this.leCourseService.getById(lecturerId, courseId);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody LeCourseReq request) {
        Optional<LeCourseRes> result = this.leCourseService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{lecturerId}/{courseId}")
    public ResponseEntity<Response> patch(@PathVariable("lecturerId") String lecturerId,
                                          @PathVariable("courseId") String  courseId,
                                          @RequestBody LeCourseReq request) {
        Optional<LeCourseRes> result = this.leCourseService.update(request, lecturerId, courseId);
        return getResponse(result);
    }

    @DeleteMapping("/{lecturerId}/{courseId}")
    public ResponseEntity<Response> delete(@PathVariable("lecturerId") String lecturerId,
                                           @PathVariable("courseId") String  courseId) {
        Optional<LeCourseRes> result = this.leCourseService.delete(lecturerId, courseId);
        return getResponse(result);
    }
}
