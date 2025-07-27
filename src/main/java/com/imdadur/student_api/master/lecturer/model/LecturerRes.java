package com.imdadur.student_api.master.lecturer.model;

import com.imdadur.student_api.master.lecturer_course.model.LeCourseRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerRes {
    private String id;
    private String name;
    private String email;
    private String title;
    private String departmentId;
    private String departmentName;
    private List<LeCourseRes> lecturerCourses;
}
