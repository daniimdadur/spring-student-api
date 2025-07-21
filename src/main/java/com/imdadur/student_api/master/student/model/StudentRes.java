package com.imdadur.student_api.master.student.model;

import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRes {
    private String id;
    private String name;
    private String email;
    private String departmentId;
    private String departmentName;
    private List<EnrollmentRes> enrollments = new ArrayList<>();
}
