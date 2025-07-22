package com.imdadur.student_api.master.lecturer_course.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerCourseRes {
    private LecturerCourseId id;
    private String lecturerId;
    private String lecturerName;
    private String courseId;
    private String courseName;
    private List<String> roles;
    private String status;
}
