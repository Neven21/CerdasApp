package com.example.cerdasappbyneven.ui.home;

public class Employees {
    String employeeId;
    String name;
    String email;
    String number;
    String position;
    String subject;
    String classes;
    String salary;
    String bio;

    public Employees(){

    }

    public Employees(String employeeId, String name, String email, String number, String position, String subject, String classes, String salary, String bio) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.number = number;
        this.position = position;
        this.subject = subject;
        this.classes = classes;
        this.salary = salary;
        this.bio = bio;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getSubject() {
        return subject;
    }

    public String getClasses() {
        return classes;
    }

    public String getSalary() {
        return salary;
    }

    public String getBio() {
        return bio;
    }
}
