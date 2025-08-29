package com.imdadur.student_api.master.lecturer.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.lecturer.model.LecturerReq;
import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.lecturer.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/lecturer")
public class LecturerController extends BaseController<LecturerRes> {
    private final LecturerService lecturerService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<LecturerRes> result = this.lecturerService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String  id) {
        Optional<LecturerRes> result = this.lecturerService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody LecturerReq request) {
        Optional<LecturerRes> result = this.lecturerService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @RequestBody LecturerReq request) {
        Optional<LecturerRes> result = this.lecturerService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<LecturerRes> result = this.lecturerService.delete(id);
        return getResponse(result);
    }
}
