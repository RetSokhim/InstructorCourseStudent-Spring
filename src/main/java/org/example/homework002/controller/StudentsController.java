package org.example.homework002.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.homework002.model.Request.StudentRequest;
import org.example.homework002.model.Students;
import org.example.homework002.model.apiRespond.StudentApiRespond;
import org.example.homework002.model.apiRespond.list.StudentsListApiRespond;
import org.example.homework002.service.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }
    @GetMapping
    @Operation(summary = "Show All Student")
    public ResponseEntity<StudentsListApiRespond> getAllStudent(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size){
        if (page <= 0) {
            return new ResponseEntity<>(new StudentsListApiRespond("Page must be more than 0", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }

        int totalStudents = studentsService.getTotalStudents();
        int totalPages = (int) Math.ceil((double) totalStudents / size);

        if (page > totalPages) {
            return new ResponseEntity<>(new StudentsListApiRespond("Page is greater than total pages", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }

        int offset = (page - 1) * size;

        List<Students> students = studentsService.getAllStudent(size, offset);

        if(students.isEmpty()){
            return new ResponseEntity<>(new StudentsListApiRespond("Not Found", null, HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new StudentsListApiRespond("Get All Students Successfully", students, HttpStatus.OK, LocalDateTime.now()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Search Student By ID")
    public ResponseEntity<StudentApiRespond> getStudentById(@PathVariable int id){
        if(studentsService.getStudentById(id) == null){
            return new ResponseEntity<>(new StudentApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Students student = studentsService.getStudentById(id);
        return  new ResponseEntity<>(new StudentApiRespond("Get All Students Successfully",student, HttpStatus.OK, LocalDateTime.now()),HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add New Student")
    public ResponseEntity<StudentApiRespond> insertStudent(@RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(new StudentApiRespond("Insert Successfully",studentsService.insertStudent(studentRequest),HttpStatus.CREATED,LocalDateTime.now()),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Student By ID")
    public ResponseEntity<StudentApiRespond> deleteStudentById(@PathVariable int id){
        if(studentsService.deleteStudentById(id) == null){
            return new ResponseEntity<>(new StudentApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new StudentApiRespond("Delete Successfully",studentsService.deleteStudentById(id),HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update Student By ID")
    public ResponseEntity<StudentApiRespond> updateStudentById(@PathVariable int id,@RequestBody StudentRequest studentRequest){
        if(studentsService.updateStudentById(id,studentRequest) == null){
            return new ResponseEntity<>(new StudentApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new StudentApiRespond(" Successfully",studentsService.updateStudentById(id,studentRequest),HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
}
