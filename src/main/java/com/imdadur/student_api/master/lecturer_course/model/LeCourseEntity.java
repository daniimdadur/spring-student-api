package com.imdadur.student_api.master.lecturer_course.model;

import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
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
@Table(name = "t_lecturer_course")
public class LeCourseEntity {

    @NotNull
    @EmbeddedId
    private LeCourseId id;

    @ManyToOne
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity lecturer;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;
}
