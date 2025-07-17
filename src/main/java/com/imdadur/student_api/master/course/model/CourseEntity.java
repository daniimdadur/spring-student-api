package com.imdadur.student_api.master.course.model;

import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_course", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class CourseEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "credit")
    private Integer credit;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    public CourseEntity(String id, String name, String code, Integer credit) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }
}
