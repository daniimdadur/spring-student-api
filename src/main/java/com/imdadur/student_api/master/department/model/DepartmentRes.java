package com.imdadur.student_api.master.department.model;

import com.imdadur.student_api.master.student.model.StudentRes;
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
public class DepartmentRes {
    private String id;
    private String name;
    private List<StudentRes> students = new ArrayList<>();
}
