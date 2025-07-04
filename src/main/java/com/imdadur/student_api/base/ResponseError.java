package com.imdadur.student_api.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private LocalDateTime timestamp;
    private int statusCode;
    private String message;
    private Object error;

    public ResponseError(int statusCode, String message, Object error) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.error = error;
    }
}
