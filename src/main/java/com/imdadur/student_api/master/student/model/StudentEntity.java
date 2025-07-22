package com.imdadur.student_api.master.student.model;

import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import jakarta.persistence.*;
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
@Table(name = "t_student", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class StudentEntity {
    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    public StudentEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
