package com.imdadur.student_api.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(List<T> result) {
        return ResponseEntity.ok(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("success")
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(Optional<T> result) {
        if (result.isPresent()) {
            T res = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("success")
                            .data(res)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("bad request")
                            .data(null)
                            .build()
            );
        }
    }
}
