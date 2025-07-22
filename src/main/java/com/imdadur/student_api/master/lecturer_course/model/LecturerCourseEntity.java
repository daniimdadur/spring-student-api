package com.imdadur.student_api.master.lecturer_course.model;

import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_lecturer_course")
public class LecturerCourseEntity {

    @NotNull
    @EmbeddedId
    private LecturerCourseId id;

    @ManyToOne
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity lecturer;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "roles")
    private List<String> roles;

    @Column(name = "status")
    private String status;
}
