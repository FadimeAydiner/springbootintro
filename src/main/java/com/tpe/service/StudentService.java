package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourseNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void saveStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Student whose email is "+ student.getEmail()+" already exist");
        }
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
       Student student=studentRepository.findById(id).orElseThrow(()->new ResourseNotFoundException("Student whose id is "+id+ " is not found"));
        return student;
    }

    public void deleteStudent(Long id) {
    Student student=getStudentById(id);
    studentRepository.delete(student);
    }
}
