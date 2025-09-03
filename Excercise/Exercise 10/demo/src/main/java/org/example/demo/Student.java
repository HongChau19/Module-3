package org.example.demo;

// File: Student.java
public class Student {
    private String studentId;
    private String fullName;
    private float gpa;

    public Student(String studentId, String fullName, float gpa) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.gpa = gpa;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    // Phương thức để lấy phân loại học lực
    public String getClassification() {
        if (gpa >= 8.0) {
            return "Giỏi";
        } else if (gpa >= 6.5) {
            return "Khá";
        } else if (gpa >= 5.0) {
            return "Trung bình";
        } else {
            return "Yếu";
        }
    }
}