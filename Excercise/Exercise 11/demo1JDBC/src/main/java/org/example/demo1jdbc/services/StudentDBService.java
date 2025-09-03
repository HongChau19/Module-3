package org.example.demo1jdbc.services;

import org.example.demo1jdbc.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDBService implements StudentService {

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();

        String sql = "select * from students";

        //try - with - resources
        try (   Connection conn = DBConection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();){

            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String subject = rs.getString("subject");

                Student student = new Student(id, name, subject);
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }
@Override
    public List<Student> filterBySubject(String subject) {
        return List.of();
    }
@Override
    public Student save(Student student) {
        String sql = "INSERT INTO students (id, name, subject) VALUES (?, ?, ?);";
        try (Connection conn = DBConection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ){
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSubject());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
}
}