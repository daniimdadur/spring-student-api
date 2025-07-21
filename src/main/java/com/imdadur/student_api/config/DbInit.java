package com.imdadur.student_api.config;

import com.imdadur.student_api.master.course.model.CourseEntity;
import com.imdadur.student_api.master.course.repo.CourseRepo;
import com.imdadur.student_api.master.department.model.DepartmentEntity;
import com.imdadur.student_api.master.department.repo.DepartmentRepo;
import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import com.imdadur.student_api.master.student.model.StudentEntity;
import com.imdadur.student_api.master.student.repo.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbInit implements CommandLineRunner {
    private final DepartmentRepo departmentRepo;
    private final StudentRepo studentRepo;
    private final EnrollmentRepo enrollmentRepo;
    private final CourseRepo courseRepo;

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

        EnrollmentId brunoJavaId = new EnrollmentId("115286a6a53f4dca9ae980d69e84374f", "94d432c7d0944c259560cdf658ac8888");
        EnrollmentId aliceJavaId = new EnrollmentId("225386b7b64e5edbaf091e70a7f95485", "94d432c7d0944c259560cdf658ac8888");
        EnrollmentId bobJavaId = new EnrollmentId("335486c8c75f6fecb0a82f81b8e06596", "94d432c7d0944c259560cdf658ac8888");
        EnrollmentId carolJavaId = new EnrollmentId("445586d9d8607gfd0b93g92c9f1767a7", "94d432c7d0944c259560cdf658ac8888");
        EnrollmentId daveJavaId = new EnrollmentId("556686e0e9718hge1c04h03d0g2878b8", "94d432c7d0944c259560cdf658ac8888");

        EnrollmentEntity brunoJava = new EnrollmentEntity(brunoJavaId, "A");

        EnrollmentEntity aliceJava = new EnrollmentEntity(aliceJavaId, "A-");

        EnrollmentEntity bobJava = new EnrollmentEntity(bobJavaId, "B");

        EnrollmentEntity carolJava = new EnrollmentEntity(carolJavaId, "A");

        EnrollmentEntity daveJava = new EnrollmentEntity(daveJavaId, "B+");

        List<StudentEntity> studentList = new ArrayList<>();
        studentList.add(bruno);
        studentList.add(alice);
        studentList.add(bob);
        studentList.add(carol);
        studentList.add(dave);

        List<CourseEntity> courseList = new ArrayList<>();
        courseList.add(java);
        courseList.add(python);
        courseList.add(csharp);
        courseList.add(javascript);
        courseList.add(html);

        //department
        techno.addStudent(bruno);
        techno.addStudent(alice);
        techno.addStudent(bob);
        techno.addStudent(carol);
        techno.addStudent(dave);

        List<DepartmentEntity> departmentList = new ArrayList<>();
        departmentList.add(techno);

        try {
            this.departmentRepo.saveAll(departmentList);
            this.courseRepo.saveAll(courseList);
            this.studentRepo.saveAll(studentList);
        } catch (Exception e) {
            log.error("dbinit save failed, error: {}", e.getMessage());
        }

        //enrollments java
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

        try {
            this.enrollmentRepo.saveAll(enrollmentList);
        } catch (Exception e) {
            log.error("saved enrollments in db init failed, error: {}", e.getMessage());
        }
    }
}
