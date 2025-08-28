package com.imdadur.student_api.master.department.model;

import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.student.model.StudentEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_department", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class DepartmentEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime created;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentEntity> students = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LecturerEntity> lecturers = new ArrayList<>();

    public void addStudent(StudentEntity student) {
        students.add(student);
        student.setDepartment(this);
    }

    public DepartmentEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
