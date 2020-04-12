package com.example.demo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, String> {
    @Query("SELECT name, gender, class_name FROM student WHERE name = :name;")
    Student findByStudentName(@Param("name") String name);

    @Modifying
    @Query("DELETE FROM student WHERE name = :name;")
    int deleteByStudentName(@Param("name") String name);

    @Modifying
    @Query("INSERT INTO student (`name`, `gender`, `class_name`) VALUES (:name, :gender, :className);")
    int addStudent(@Param("name") String name, @Param("gender") String gender, @Param("className") String className);
}
