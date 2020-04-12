package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/post")
    public String post(@RequestBody Student student) {
        if (studentRepository.findByStudentName(student.getName()) == null) {
             studentRepository.addStudent(student.getName(), student.getGender(), student.getClassName());
            return "添加成功";
        } else {
            return "姓名重复";
        }
    }

    @GetMapping("/student/{name}")
    public Student getStudent(@PathVariable String name) {
        return studentRepository.findByStudentName(name);
    }

    @GetMapping("/students")
    public Iterable<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/delete/{name}")
    public int deleteStudent(@PathVariable String name) {
        return studentRepository.deleteByStudentName(name);
    }
}
