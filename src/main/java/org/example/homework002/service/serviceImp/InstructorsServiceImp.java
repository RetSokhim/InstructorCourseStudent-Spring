package org.example.homework002.service.serviceImp;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework002.model.Instructors;
import org.example.homework002.model.Request.InstructorsRequest;
import org.example.homework002.repository.InstructorsRepo;
import org.example.homework002.service.InstructorsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorsServiceImp implements InstructorsService {
    private final InstructorsRepo instructorsRepo;

    public InstructorsServiceImp(InstructorsRepo instructorsRepo) {
        this.instructorsRepo = instructorsRepo;
    }

    @Override
    public Instructors insertInstructor(InstructorsRequest instructorsRequest) {
        return instructorsRepo.insertInstructor(instructorsRequest);
    }

    @Override
    public List<Instructors> findAllInstructor(int size,int page) {
        return instructorsRepo.findAllInstructor(size,page);
    }

    @Override
    public Instructors searchInstructorById(int id) {
        return instructorsRepo.searchInstructorById(id);
    }

    @Override
    public Instructors deleteInstructorById(int id) {
        return instructorsRepo.deleteInstructorById(id);
    }

    @Override
    public Instructors updateInstructorById(int id,InstructorsRequest instructorsRequest) {
        return instructorsRepo.updateInstructorById(id,instructorsRequest);
    }

    @Override
    public int getTotalInstructors() {
        return instructorsRepo.getTotalInstructors();
    }

}
