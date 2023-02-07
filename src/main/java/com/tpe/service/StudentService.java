package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourseNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent=getStudentById(id);
        boolean emailExists=studentRepository.existsByEmail(studentDTO.getEmail());
        if(emailExists && !studentDTO.getEmail().equals((existingStudent.getEmail()))){
            throw new ConflictException("Student whose email is "+studentDTO.getEmail()+" already exist");
        }
        /* DB emailList:  [aaa@gamil.com, bbb@gmail.com, ccc@gmail.com]

        1.  studdentDTO.getEmail() -- aaa@gamil.com
                True && !True ==>False
        update the existingStudent

        2. studdentDTO.gethemail() -- aaa@gamil.com
                True && ! False ==>True
        result: exception message

        3. studdentDTO.getEmail() -- xxx@gamil.com
                False &&

                result: update the existingStudent
            */

        existingStudent.setFirstName(studentDTO.getName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setGrade(studentDTO.getGrade());
        existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
        existingStudent.setEmail(studentDTO.getEmail());

        studentRepository.save(existingStudent);
    }

    public Page<Student> getAllStudentsWithPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public List<Student> getStudentsByLastName(String lastName) {
        return studentRepository.findStudentByLastName(lastName);
    }

    public List<Student> getStudentsByGrade(Integer grade) {
        return studentRepository.findStudentByGradeWithSQL(grade);
    }

    public StudentDTO getStudentDTOById(Long id) {
        return studentRepository.findStudentDTOById(id).orElseThrow(()->
                new ResourseNotFoundException("Student whose id is "+id+ " is not found"));
    }
}
