package com.imdadur.student_api.master.student.controller;

import com.imdadur.student_api.base.BaseController;
import com.imdadur.student_api.base.Response;
import com.imdadur.student_api.master.student.model.StudentReq;
import com.imdadur.student_api.master.student.model.StudentRes;
import com.imdadur.student_api.master.student.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController extends BaseController<StudentRes> {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<StudentRes> result = this.studentService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable @Valid String  id) {
        Optional<StudentRes> result = this.studentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody @Valid StudentReq request) {
        Optional<StudentRes> result = this.studentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable @NotBlank String id, @RequestBody @Valid StudentReq request) {
        Optional<StudentRes> result = this.studentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable @NotBlank String id) {
        Optional<StudentRes> result = this.studentService.delete(id);
        return getResponse(result);
    }
}
