package com.imdadur.student_api.master.enrollment.model;

import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.course.model.CourseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_enrollment")
public class EnrollmentEntity {

    @Id
    @Column
    private String id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentEntity student;

    @Column(name = "course_id", insertable = false, updatable = false)
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "grade")
    private String grade;

    public EnrollmentEntity(String id, String grade) {
        this.id = id;
        this.grade = grade;
    }
}
