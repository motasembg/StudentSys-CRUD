package com.example.demo.students.controller;

import com.example.demo.students.model.Student;
import com.example.demo.students.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentsController {
    private final StudentsService studentService;

    @Autowired
    public StudentsController(StudentsService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(@RequestParam(required = false) Long studentId){
        return studentService.getStudents(studentId);
    }

    @PostMapping
    public void registrationNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestBody Student request){
        studentService.updateStudent(studentId, request.getName(), request.getEmail());
    }
}
