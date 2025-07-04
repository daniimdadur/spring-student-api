package com.imdadur.student_api.master.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentRes {
    private String id;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private String grade;
}
