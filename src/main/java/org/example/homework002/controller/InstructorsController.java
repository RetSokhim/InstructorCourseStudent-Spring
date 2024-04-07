package org.example.homework002.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.homework002.model.Instructors;
import org.example.homework002.model.Request.InstructorsRequest;
import org.example.homework002.model.apiRespond.InstructorApiRespond;
import org.example.homework002.model.apiRespond.list.InstructorListApiRespond;
import org.example.homework002.service.InstructorsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorsController {
    private final InstructorsService instructorsService;

    public InstructorsController(InstructorsService instructorsService) {
        this.instructorsService = instructorsService;
    }

    @PostMapping
    @Operation(summary = "Add Instructor")
    public ResponseEntity<InstructorApiRespond> insertInstructor (@RequestBody InstructorsRequest instructorsRequest){
        return new ResponseEntity<>(new InstructorApiRespond("Insert Successfully",instructorsService.insertInstructor(instructorsRequest),HttpStatus.CREATED,LocalDateTime.now()),HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Show All Instructor")
    public ResponseEntity<InstructorListApiRespond> findAllInstructor(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
        if (page <= 0) {
            return new ResponseEntity<>(new InstructorListApiRespond("Page must be more than 0", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }

        int offset = (page - 1) * size;

        int totalInstructors = instructorsService.getTotalInstructors();
        int totalPages = (int) Math.ceil((double) totalInstructors / size);

        if (page > totalPages) {
            return new ResponseEntity<>(new InstructorListApiRespond("Page is greater than total pages", null, HttpStatus.BAD_REQUEST, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
        }

        List<Instructors> instructors = instructorsService.findAllInstructor(size, offset);

        if (instructors.isEmpty()) {
            return new ResponseEntity<>(new InstructorListApiRespond("Not Found", null, HttpStatus.NOT_FOUND, LocalDateTime.now()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new InstructorListApiRespond("Find All successfully", instructors, HttpStatus.OK, LocalDateTime.now()), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Search Instructor By ID")
    public ResponseEntity<InstructorApiRespond> searchInstructorById(@PathVariable int id) {

        Instructors instructor = instructorsService.searchInstructorById(id);
        return new ResponseEntity<>(new InstructorApiRespond("Search Successfully",instructor,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Instructor By ID")
    public ResponseEntity<InstructorApiRespond> deleteInstructorById(@PathVariable int id){
        if(instructorsService.deleteInstructorById(id) == null){
            return new ResponseEntity<>(new InstructorApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Instructors instructor = instructorsService.deleteInstructorById(id);
        return new ResponseEntity<>(new InstructorApiRespond("Delete Successfully",instructor,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update By Instructor ID")
    public ResponseEntity<InstructorApiRespond> updateInstructorById(@PathVariable int id , @RequestBody InstructorsRequest instructorsRequest){
        if(instructorsService.updateInstructorById(id,instructorsRequest)== null){
            return new ResponseEntity<>(new InstructorApiRespond("Not Found",null,HttpStatus.NOT_FOUND,LocalDateTime.now()),HttpStatus.NOT_FOUND);
        }
        Instructors instructor = instructorsService.updateInstructorById(id,instructorsRequest);
        return new ResponseEntity<>(new InstructorApiRespond("Update Successfully",instructor,HttpStatus.OK,LocalDateTime.now()),HttpStatus.OK);
    }
}

