package com.imdadur.student_api.master.course.model;

import com.imdadur.student_api.master.enrollment.model.EnrollmentRes;
import com.imdadur.student_api.master.lecturer_course.model.LeCouRes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRes {
    private String id;
    private String name;
    private String code;
    private Integer credit;
    private List<EnrollmentRes> enrollments = new ArrayList<>();
    private List<LeCouRes> lecturerCourses = new ArrayList<>();
}
