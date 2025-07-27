package com.imdadur.student_api.master.department.model;

import com.imdadur.student_api.master.lecturer.model.LecturerRes;
import com.imdadur.student_api.master.student.model.StudentRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRes {
    private String id;
    private String name;
    private List<StudentRes> students = new ArrayList<>();
    private List<LecturerRes> lecturers = new ArrayList<>();
}
