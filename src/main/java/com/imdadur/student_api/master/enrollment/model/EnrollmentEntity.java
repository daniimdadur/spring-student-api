package com.imdadur.student_api.master.enrollment.model;

import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.course.model.CourseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_enrollment")
public class EnrollmentEntity {

    @NotNull
    @EmbeddedId
    private EnrollmentId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "grade")
    private String grade;

    public EnrollmentEntity(EnrollmentId id, String grade) {
        this.id = id;
        this.grade = grade;
    }

    public EnrollmentEntity(StudentEntity student, CourseEntity course, String grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.id = new EnrollmentId(student.getId(), course.getId());
    }
}
