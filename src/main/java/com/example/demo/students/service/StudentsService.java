package com.example.demo.students.service;

import com.example.demo.students.model.Student;
import com.example.demo.students.StudentsRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {

    private final StudentsRepo studentsRepo;

    @Autowired
    public StudentsService(StudentsRepo studentsRepo) {
        this.studentsRepo = studentsRepo;
    }

    public List<Student> getStudents(Long studentId){

            if (studentId != null){
                return studentsRepo.findById(studentId)
                        .map(Collections::singletonList)
                        .orElseThrow(()-> new IllegalStateException("Student with id " + studentId + " does not exist"));
            }
        return studentsRepo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentsRepo
                .findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentsRepo.save(student);

    }
    public void deleteStudent(Long studentId){
       boolean exist = studentsRepo.existsById(studentId);
        if (!exist){
            throw new IllegalStateException("student with id" + studentId + "deos not exist");
        }
        studentsRepo.deleteById(studentId);

    }


    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentsRepo.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "Student with id " + studentId + " deos not exist"
        ));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentsRepo.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
