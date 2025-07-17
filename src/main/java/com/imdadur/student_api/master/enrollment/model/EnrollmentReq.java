package com.imdadur.student_api.master.enrollment.model;

import com.imdadur.student_api.anotation.ValidGrade;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentReq {
    @NotBlank(message = "student id can't be empty")
    private String studentId;
    @NotBlank(message = "course id can't be empty")
    private String courseId;
    @ValidGrade
    private String grade;
}
