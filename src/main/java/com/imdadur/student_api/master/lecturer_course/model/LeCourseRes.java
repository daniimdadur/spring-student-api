package com.imdadur.student_api.master.lecturer_course.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeCourseRes {
    private String  id;
    private String lecturerId;
    private String lecturerName;
    private String courseId;
    private String courseName;
    private String role;
    private String status;
}
