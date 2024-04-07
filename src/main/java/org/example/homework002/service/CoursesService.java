package org.example.homework002.service;
import org.example.homework002.model.Courses;
import org.example.homework002.model.Request.CoursesRequest;

import java.util.List;

public interface CoursesService {
    List<Courses> getAllCourse(int size,int page);
    Courses getCourseById(int id);
    Courses insertCourse(CoursesRequest coursesRequest);
    Courses deleteCourseById(int id);
    Courses updateCourseById(int id,CoursesRequest coursesRequest);
    int getTotalCourses();
}
