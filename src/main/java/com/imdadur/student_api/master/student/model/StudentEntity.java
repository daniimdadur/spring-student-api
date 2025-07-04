package com.imdadur.student_api.master.student.model;

import com.imdadur.student_api.master.department.model.DepartmentEntity;
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
@Table(name = "t_student")
public class StudentEntity {
    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "department_id", insertable = false, updatable = false)
    private String departmentId;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity department;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments = new ArrayList<>();

    public void addEnrollment(EnrollmentEntity enrollment) {
        enrollments.add(enrollment);
        enrollment.setStudent(this);
    }

    public StudentEntity(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
