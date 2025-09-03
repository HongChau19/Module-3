package org.example.demo1jdbc.services;

import org.example.demo1jdbc.models.Student;
import java.util.List;

public interface StudentService {
    List<Student> findAll();
    List<Student> filterBySubject(String Subject);
    Student save(Student student);
}
