package org.example.homework002.service;

import org.example.homework002.model.Instructors;
import org.example.homework002.model.Request.InstructorsRequest;

import java.util.List;

public interface InstructorsService{
    Instructors insertInstructor(InstructorsRequest instructorsRequest);
    List<Instructors> findAllInstructor(int size,int page);
    Instructors searchInstructorById(int id) ;
    Instructors deleteInstructorById(int id);
    Instructors updateInstructorById(int id, InstructorsRequest instructorsRequest);
    int getTotalInstructors();
}
