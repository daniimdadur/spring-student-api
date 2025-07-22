package com.imdadur.student_api.master.lecturer.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerReq {
    @NotBlank(message = "name can't be empty")
    private String name;
    @NotBlank(message = "email can't be empty")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "title can't be empty")
    private String title;
    @NotBlank(message = "departmentId can't be empty")
    private String departmentId;
}
