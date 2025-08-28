package com.imdadur.student_api.master.lecturer.model;

import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_lecturer", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class LecturerEntity {

    @Id
    @Column
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "title")
    private String title;

    @Column(name = "created_at")
    private LocalDateTime created;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DepartmentEntity department;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<LeCourseEntity> lecturerCourses = new ArrayList<>();

    public LecturerEntity(String id, String name, String email, String title) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.title = title;
    }
}
