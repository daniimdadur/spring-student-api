package com.imdadur.student_api.master.enrollment.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentId implements Serializable {
    private String enrollmentStudentId;
    private String enrollmentCourseId;
}
