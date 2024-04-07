package org.example.homework002.service;
import org.example.homework002.model.Request.StudentRequest;
import org.example.homework002.model.Students;
import java.util.List;

public interface StudentsService {
    List<Students> getAllStudent(int size,int page);
    Students getStudentById(int id);
    Students deleteStudentById(int id);
    Students insertStudent (StudentRequest studentRequest);
    Students updateStudentById(int id,StudentRequest studentRequest);
    int getTotalStudents();
}
