package org.example.homework002.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.homework002.model.Courses;
import org.example.homework002.model.Request.CoursesRequest;
import org.example.homework002.model.apiRespond.CourseApiRespond;
import org.example.homework002.model.apiRespond.list.CourseListApiRespond;
import org.example.homework002.service.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }
    @GetMapping
    @Operation(summary = "Show All Course")
    public ResponseEntity<CourseListApiRespond> getAllCourse(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        if (page <= 0) {
            return new ResponseEntity<>(new CourseListApiRespond("Page must be more than 0", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }
        int offset = (page - 1) * size;
        int totalCourses = coursesService.getTotalCourses();
        int totalPages = (int) Math.ceil((double) totalCourses / size);

        if (page > totalPages) {
            return new ResponseEntity<>(new CourseListApiRespond("Page is greater than total pages", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }

        List<Courses> courses = coursesService.getAllCourse(size, offset);

        if (courses.isEmpty()) {
            return new ResponseEntity<>(new CourseListApiRespond("There's No Content", null, HttpStatus.NO_CONTENT, LocalDateTime.now()), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(new CourseListApiRespond("Get All Successfully", courses, HttpStatus.OK, LocalDateTime.now()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Course By ID")
    public ResponseEntity<CourseApiRespond> getCourseById(@PathVariable int id){
        if(coursesService.getCourseById(id) == null){
            return new ResponseEntity<>(new CourseApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Courses courses = coursesService.getCourseById(id);
        return new ResponseEntity<>(new CourseApiRespond("Get Course Successfully",courses,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Insert New Course")
    public ResponseEntity<CourseApiRespond> insertCourse(@RequestBody CoursesRequest coursesRequest){
        return new ResponseEntity<>(new CourseApiRespond("Insert Successfully",coursesService.insertCourse(coursesRequest),HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Course By ID")
    public ResponseEntity<CourseApiRespond> deleteCourseById(@PathVariable int id){
        if(coursesService.deleteCourseById(id) == null){
            return new ResponseEntity<>(new CourseApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Courses courses = coursesService.deleteCourseById(id);
        return new ResponseEntity<>(new CourseApiRespond("Delete Successfully",courses,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Course By ID")
    public ResponseEntity<CourseApiRespond> updateCourseById(@PathVariable int id,@RequestBody CoursesRequest coursesRequest){
        if(coursesService.updateCourseById(id,coursesRequest) == null){
            return new ResponseEntity<>(new CourseApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Courses courses = coursesService.updateCourseById(id,coursesRequest);
        return new ResponseEntity<>(new CourseApiRespond("Update Successfully",courses,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
}
