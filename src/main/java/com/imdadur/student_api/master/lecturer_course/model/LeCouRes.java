package com.imdadur.student_api.master.lecturer_course.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeCouRes {
    private LeCouId id;
    private String lecturerId;
    private String lecturerName;
    private String courseId;
    private String courseName;
    private String role;
    private String status;
}
