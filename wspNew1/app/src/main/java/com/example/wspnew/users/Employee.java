package com.example.wspnew.users;

import com.example.wspnew.enums.Gender;

public class Employee extends User {
    String id;
    float salary;

    public Employee(String usertype, String firstName, String lastname, String login, String password, Gender gender, String id, float salary) {
        super(usertype, firstName, lastname, login, password, gender);
        this.id = id;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
