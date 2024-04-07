package org.example.homework002.service.serviceImp;

import org.example.homework002.model.Courses;
import org.example.homework002.model.Request.CoursesRequest;
import org.example.homework002.repository.CoursesRepo;
import org.example.homework002.service.CoursesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesServiceImp implements CoursesService {
    private final CoursesRepo coursesRepo;

    public CoursesServiceImp(CoursesRepo coursesRepo) {
        this.coursesRepo = coursesRepo;
    }

    @Override
    public List<Courses> getAllCourse(int size,int page) {
        return coursesRepo.getAllCourse(size,page);
    }

    @Override
    public Courses getCourseById(int id) {
        return coursesRepo.getCourseById(id);
    }

    @Override
    public Courses insertCourse(CoursesRequest coursesRequest) {
        return coursesRepo.insertCourse(coursesRequest);
    }

    @Override
    public Courses deleteCourseById(int id) {
        return coursesRepo.deleteCourseById(id);
    }

    @Override
    public Courses updateCourseById(int id, CoursesRequest coursesRequest) {
        return coursesRepo.updateCourseById(id,coursesRequest);
    }

    @Override
    public int getTotalCourses() {
        return coursesRepo.getTotalCourses();
    }
}
