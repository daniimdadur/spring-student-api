package com.imdadur.student_api.master.enrollment.model;

import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.course.model.CourseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_enrollment")
public class EnrollmentEntity {

    @Id
    @Column
    private String id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "grade")
    private String grade;

    @Column(name = "created_at")
    private LocalDateTime created;

    public EnrollmentEntity(String id, String grade) {
        this.id = id;
        this.grade = grade;
    }
}
