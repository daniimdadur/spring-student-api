package com.imdadur.student_api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomHttpStatus {
    DUPLICATE_KEY(409, "Duplicate Value");

    private final int value;
    private final String name;
}
