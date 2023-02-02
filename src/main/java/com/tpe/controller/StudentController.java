package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//include @Controller and @ResponseBody.Also  @Controller include @Companent
@RequestMapping("/students")//http://localhost:8080/students //Map the coming request and the address
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping //http://localhost:8080/students + Post
    public ResponseEntity<Map<String ,String >> saveStudent(@Valid @RequestBody Student student){
        //@Valid--> Validate the fields of Student object
        //@ResponseBody--> Maps the Student object to Json Data
        //ResponseEntity-->we will decide return type. it should be for return type and http status code

        studentService.saveStudent(student);
        Map<String ,String > map=new HashMap<>();
        map.put("message","Student is created successfully");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping()//http://localhost:8080/students + Get
    public ResponseEntity<List<Student>> getAllStudents(){
    List<Student> students=studentService.getAllStudents();
    return ResponseEntity.ok(students);//new ResponseEntity<>(Student,HttpStatus.OK)

    }

    @GetMapping("/query")//http://localhost:8080/students/query?id= number
    public ResponseEntity<Student> getStudentById(@RequestParam("id") Long id){
        Student student=studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}")///http://localhost:8080/students/ number +get
    public ResponseEntity<Student> getStudentByIdUsingPathVariale(@PathVariable("id")Long id){
        Student student=studentService.getStudentById(id);
        return ResponseEntity.ok(student);

    }

    @DeleteMapping("{id}")//http://localhost:8080/students/
    public  ResponseEntity<Map<String,String >> deleteStudentById(@PathVariable("id")Long id){
        studentService.deleteStudent(id);
        Map<String ,String > map=new HashMap<>();
        map.put("message","Student is deleted successfully");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }


}
