package com.imdadur.student_api.master.student.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentReq {
    @NotBlank(message = "name can't be empty")
    private String name;
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "department id can't empty")
    private String departmentId;
}
