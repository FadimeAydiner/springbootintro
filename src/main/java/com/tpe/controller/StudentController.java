package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    //update existing students DTO
    @PutMapping("{id}")//http://localhost:8080/students/2+put+json
    public ResponseEntity<Map<String,String >> updateStudent(@PathVariable("id")Long id,
                                                             @Valid @RequestBody StudentDTO studentDTO) {
    studentService.updateStudent(id,studentDTO);
        Map<String ,String > map=new HashMap<>();
        map.put("message","Student is updated successfully");
        map.put("status","true");
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    //get students using Pageable
    @GetMapping("/page")//http://localhost:8080/students/page=GET
    public ResponseEntity<Page<Student>> getStudentWithPage(@RequestParam("page") int page,//required-page numbers starting from 0
                                                            @RequestParam("size") int size,//required-how many data shows in per  page
                                                            @RequestParam("sort")String prop,//optional-on which field sorting should be done
                                                            @RequestParam("direction")Sort.Direction direction){//optional-ascending or decending order)

        Pageable pageable= PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Student> studentPage=studentService.getAllStudentsWithPage(pageable);
        return ResponseEntity.ok(studentPage);
    }


    //method to bring student by their lastName
    @GetMapping("/querylastName")//http://localhost:8080/students/querylastName + GET
    public ResponseEntity<List<Student>> getStudentsByLastName(@RequestParam("lastName")String lastName){
        List<Student> studentList=studentService.getStudentsByLastName(lastName);
        return ResponseEntity.ok(studentList);
    }


    //get students by grade using JPQL--java Persistance Query Language
    @GetMapping("grade/{grade}")//http://localhost:8080/students/grade/{garde} +GET
    public ResponseEntity<List<Student>> getStudentByGrade(@PathVariable("grade") Integer grade){
        List<Student> studentList=studentService.getStudentsByGrade(grade);
        return ResponseEntity.ok(studentList);
    }

  //let s get DTO from Repository using JPQL
  @GetMapping("/query/dto")  //http://localhost:8080/students/query/dto + GET
    public ResponseEntity<StudentDTO> getStudentDTO(@RequestParam("id") Long id){
        StudentDTO studentDTO=studentService.getStudentDTOById(id);
      return ResponseEntity.ok(studentDTO);
  }



}
