package com.imdadur.student_api.config;

import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import com.imdadur.student_api.master.lecturer.model.LecturerEntity;
import com.imdadur.student_api.master.lecturer.repo.LecturerRepo;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseEntity;
import com.imdadur.student_api.master.lecturer_course.model.LeCourseId;
import com.imdadur.student_api.master.lecturer_course.repo.LeCourseRepo;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final DepartmentRepo departmentRepo;
    private final StudentRepo studentRepo;
    private final EnrollmentRepo enrollmentRepo;
    private final CourseRepo courseRepo;
    private final LecturerRepo lecturerRepo;
    private final LeCourseRepo leCouRepo;

    @Override
    public void run(String... args) throws Exception {
        initFixedDb();
    }

    private void initFixedDb() {
        if (!departmentRepo.findAll().isEmpty()) {
            return;
        }

        DepartmentEntity techno = new DepartmentEntity("3686bca2809b4c328526fdfa559b8cf7", "Department Techno");

        StudentEntity bruno = new StudentEntity("115286a6a53f4dca9ae980d69e84374f", "bruno", "bruno@gentara.com");
        StudentEntity alice = new StudentEntity("225386b7b64e5edbaf091e70a7f95485", "alice", "alice@gentara.com");
        StudentEntity bob = new StudentEntity("335486c8c75f6fecb0a82f81b8e06596", "bob", "bob@gentara.com");
        StudentEntity carol = new StudentEntity("445586d9d8607gfd0b93g92c9f1767a7", "carol", "carol@gentara.com");
        StudentEntity dave = new StudentEntity("556686e0e9718hge1c04h03d0g2878b8", "dave", "dave@gentara.com");

        CourseEntity java = new CourseEntity("94d432c7d0944c259560cdf658ac8888", "java language", "J001", 6);
        CourseEntity python = new CourseEntity("23b8f49b1e55557d984g78f9deg947d1", "python programming", "P002", 4);
        CourseEntity csharp = new CourseEntity("34c9g5ac2f66668ea95h89g0efh58ae2", "c# fundamentals", "C003", 5);
        CourseEntity javascript = new CourseEntity("45d0h6bd3g77779fba6i90h1fgi69bf3", "javascript basics", "J004", 3);
        CourseEntity html = new CourseEntity("56e1i7ce4h88880gcb7j01i2ghj70cg4", "html & css", "H005", 2);

        EnrollmentEntity brunoJava = new EnrollmentEntity("3b879a2f64c4b57b7d159a2e3b879a2","A");
        EnrollmentEntity aliceJava = new EnrollmentEntity("7c6d4e5f2a1b9c8d3e5f2a1b9c8d3e5f", "A-");
        EnrollmentEntity bobJava = new EnrollmentEntity("a1b2c3d4e5f67g8h9i0j1k2l3m4n5o6p", "B");
        EnrollmentEntity carolJava = new EnrollmentEntity("b7d159a2e3b879a2f64c4b57b7d159a2", "A");
        EnrollmentEntity daveJava = new EnrollmentEntity("5f2a1b9c8d3e7c6d4e5f2a1b9c8d3e5f", "B+");

        LecturerEntity john = new LecturerEntity("96bf08c221f2426ba7da0059c5991792", "John", "john@gentara.com", "S.Kom");
        LecturerEntity michael = new LecturerEntity("0354767bf7c646b6ad734e910f4e4043", "Michael", "michael@gentara.com", "S.T");
        LecturerEntity palmer = new LecturerEntity("ea7434cd2c6e4aefb04f7c229fa21ad2", "Palmer", "palmer@gentara.com", "S.Pd");
        LecturerEntity nunes = new LecturerEntity("a4c4d49dee5b442180bab03092a7b95e", "Nunes", "nunes@gentara.com", "S.Ag");
        LecturerEntity mason = new LecturerEntity("25274ae0ea7045aba6aaa22ee2e400a2", "Mason", "mason@gentara.com", "S.E");

        LeCourseEntity johnJava = new LeCourseEntity("2a7c5b8e9d4f1a2c3d4e5f6a7b8c9d0e", john, java, "Main Lecturer", "Active");
        LeCourseEntity johnPhyton = new LeCourseEntity("f1d9e8c7b6a54b3c2d1e0f9a8b7c6d5e", john, python, "Assistant Lecturer", "Active");
        LeCourseEntity michaelJava = new LeCourseEntity("4f8e1b5d6a7c3e9b0d2f5a6c1e8b3d7f", michael, java, "Assistant Lecturer", "Non-Active");
        LeCourseEntity palmerHtml = new LeCourseEntity("c9a8b7c6d5e4f3a2b1c0d9e8f7a6b5c4", palmer, html, "Main Lecturer", "Active");
        LeCourseEntity masonJs = new LeCourseEntity("e0f9d8c7b6a51d2c3e4f5a6b7c8d9e0f", mason, javascript, "Main Lecturer", "Active");

        //students
        List<StudentEntity> studentList = List.of(bruno, alice, bob, carol, dave);

        //courses
        List<CourseEntity> courseList = List.of(java, python, csharp, javascript, html);

        List<LecturerEntity> lecturerList = List.of(john, michael, nunes, mason, palmer);
        john.setDepartment(techno);
        michael.setDepartment(techno);
        palmer.setDepartment(techno);
        nunes.setDepartment(techno);
        mason.setDepartment(techno);

        //enrollments
        brunoJava.setStudent(bruno);
        aliceJava.setStudent(alice);
        bobJava.setStudent(bob);
        daveJava.setStudent(dave);
        carolJava.setStudent(carol);
        brunoJava.setCourse(java);
        aliceJava.setCourse(java);
        bobJava.setCourse(java);
        daveJava.setCourse(java);
        carolJava.setCourse(java);
        List<EnrollmentEntity> enrollmentList = List.of(brunoJava, aliceJava, bobJava, daveJava, carolJava);

        List<LeCourseEntity> leCouList = List.of(johnJava, johnPhyton, michaelJava, palmerHtml, masonJs);

        //department
        techno.addStudent(bruno);
        techno.addStudent(alice);
        techno.addStudent(bob);
        techno.addStudent(carol);
        techno.addStudent(dave);
        techno.setLecturers(lecturerList);

        List<DepartmentEntity> departmentList = List.of(techno);

        try {
            this.departmentRepo.saveAll(departmentList);
            this.courseRepo.saveAll(courseList);
            this.studentRepo.saveAll(studentList);
            this.lecturerRepo.saveAll(lecturerList);
            this.enrollmentRepo.saveAll(enrollmentList);
            this.leCouRepo.saveAll(leCouList);
        } catch (Exception e) {
            log.error("dbinit save failed, error: {}", e.getMessage());
        }
    }
}
