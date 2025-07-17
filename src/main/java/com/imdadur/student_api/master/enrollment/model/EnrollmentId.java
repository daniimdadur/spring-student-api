package com.imdadur.student_api.master.enrollment.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentId implements Serializable {
    private String studentId;
    private String courseId;
}
