package com.imdadur.student_api.master.student.model;

import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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
