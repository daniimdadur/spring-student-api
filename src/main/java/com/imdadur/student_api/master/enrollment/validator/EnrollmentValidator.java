package com.imdadur.student_api.master.enrollment.validator;

import com.imdadur.student_api.master.enrollment.model.EnrollmentEntity;
import com.imdadur.student_api.master.enrollment.model.EnrollmentId;
import com.imdadur.student_api.master.enrollment.model.EnrollmentReq;
import com.imdadur.student_api.master.enrollment.repo.EnrollmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentValidator {
    private final EnrollmentRepo enrollmentRepo;

    public boolean isSameId(EnrollmentReq request, EnrollmentEntity entity) {
        return request.getStudentId().equals(entity.getStudent().getId()) &&
                request.getCourseId().equals(entity.getCourse().getId());
    }

    public boolean isDuplicate(EnrollmentReq request) {
        return this.enrollmentRepo.existsById(new EnrollmentId(request.getStudentId(), request.getCourseId()));
    }
}
