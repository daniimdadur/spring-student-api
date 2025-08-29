package com.imdadur.student_api.master.lecturer_course.model;

import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_lecturer_course")
public class LeCourseEntity {

    @Id
    @Column
    private String  id;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity lecturer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime created;

    public LeCourseEntity(String id, LecturerEntity lecturer, CourseEntity course, String role, String status) {
        this.id = id;
        this.lecturer = lecturer;
        this.course = course;
        this.role = role;
        this.status = status;
    }
}
