package com.springbootcrudapp.controller;

import com.springbootcrudapp.entity.Student;
import com.springbootcrudapp.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;


    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);

    }

    @GetMapping("/api/students")
    public ResponseEntity<List<Student>> getstudents(){
        return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id){
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student stu){
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            student.get().setStudentName(stu.getStudentName());
            student.get().setStudentEmail(stu.getStudentEmail());
            student.get().setStudentAddress(stu.getStudentAddress());
            return  new ResponseEntity<>(studentRepo.save(student.get()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/api/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        Optional<Student> student = studentRepo.findById(id);
        if(student.isPresent()){
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
