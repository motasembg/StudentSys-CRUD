package com.example.demo.students;

import com.example.demo.students.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentsConfig {
    @Bean
    CommandLineRunner commandLineRunner (StudentsRepo repo){
        return args -> {
            Student Ahmed = new Student(
                    "Ahmed Algaber",
                    "Ahmed.algaber@gmail.com",
                    LocalDate.of(1998, Month.AUGUST,17),
                    27
            );
            Student Mohaned = new Student(
                    "Mohaned Ensir",
                    "Mohaned.ensir@gmail.com",
                    LocalDate.of(2000, Month.AUGUST,8),
                    24
            );
            repo.saveAll(
                    List.of(Ahmed, Mohaned)
            );
        };
    }
}
