package com.imdadur.student_api.master.course.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseReq {

    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "code can't be empty")
    private String code;
    @NotNull(message = "credit can't be empty")
    private Integer credit;
}
