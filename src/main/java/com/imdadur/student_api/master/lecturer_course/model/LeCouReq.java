package com.imdadur.student_api.master.lecturer_course.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeCouReq {
    @NotBlank(message = "lecturerId can't be empty")
    private String lecturerId;
    @NotBlank(message = "courseId can't be empty")
    private String courseId;
    @NotNull(message = "role can't be empty")
    private String role;
    @NotBlank(message = "status can't be empty")
    private String status;
}
