package com.imdadur.student_api.master.course.model;

import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCouEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_course", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class CourseEntity {

    @Id
    @Column(name = "cid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "credit")
    private Integer credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LeCouEntity> lecturerCourses = new ArrayList<>();

    public CourseEntity(String id, String name, String code, Integer credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }
}
