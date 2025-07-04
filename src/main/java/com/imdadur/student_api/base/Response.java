package com.imdadur.student_api.base;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int statusCode;
    private Object message;
    private T data;
}
