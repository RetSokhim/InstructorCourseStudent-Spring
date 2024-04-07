package org.example.homework002.service.serviceImp;

import org.example.homework002.model.Request.StudentRequest;
import org.example.homework002.model.Students;
import org.example.homework002.repository.StudentsRepo;
import org.example.homework002.service.StudentsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImp implements StudentsService {
    private final StudentsRepo studentsRepo;

    public StudentsServiceImp(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    @Override
    public List<Students> getAllStudent(int size,int page) {
        return studentsRepo.getAllStudents(size,page);
    }

    @Override
    public Students getStudentById(int id) {
        return studentsRepo.getStudentById(id);
    }

    @Override
    public Students deleteStudentById(int id) {
        return studentsRepo.deleteStudentById(id);
    }
    @Override
    public Students insertStudent(StudentRequest studentRequest) {
        Students student = studentsRepo.insertStudent(studentRequest);
        for (int courseId : studentRequest.getCoursesId()) {
            studentsRepo.insertIntoStudentCourse(student.getStudentId(), courseId);
        }
        return studentsRepo.getStudentById(student.getStudentId());
    }

    @Override
    public Students updateStudentById(int id, StudentRequest studentRequest) {
        studentsRepo.deleteStudentCourses(id);

        for (int courseId : studentRequest.getCoursesId()) {
            studentsRepo.insertIntoStudentCourse(id, courseId);
        }
        return studentsRepo.updateStudentById(id,studentRequest);
    }

    @Override
    public int getTotalStudents() {
        return studentsRepo.getTotalStudent();
    }
}
