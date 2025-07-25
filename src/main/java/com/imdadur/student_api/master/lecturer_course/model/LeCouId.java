package com.imdadur.student_api.master.lecturer_course.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class LeCouId implements Serializable {
    private String lecturerId;
    private String courseId;
}
