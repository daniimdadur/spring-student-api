package com.imdadur.student_api.master.lecturer_course.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerCourseReq {
    @NotBlank(message = "lecturerId can't be empty")
    private String lecturerId;
    @NotBlank(message = "courseId can't be empty")
    private String courseId;
    @NotNull(message = "roles can't be empty")
    private List<String> roles;
    @NotBlank(message = "status can't be empty")
    private String status;
}
