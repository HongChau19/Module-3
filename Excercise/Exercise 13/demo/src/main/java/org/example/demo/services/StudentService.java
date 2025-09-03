package org.example.demo.services;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    List<Student> filterBySubject(String Subject);
    Student save(Student student);
}
