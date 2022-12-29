package com.example.wspnew.users;

import com.example.wspnew.classes.Course;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.interfaces.CanViewCourses;

import java.util.Vector;

public class Teacher extends Employee implements CanViewCourses {
    private Vector<Course> teaches = new Vector<>();
    public Teacher(String usertype, String firstName, String lastname, String login, String password, Gender gender, String id, float salary) {
        super(usertype, firstName, lastname, login, password, gender, id, salary);
    }
    public Vector<Course> getTeaches() {
        return teaches;
    }

    @Override
    public void viewCourses() {
        for(int i = 0; i < teaches.size(); i++) {
            System.out.println((i + 1) + ") " + teaches.get(i));
        }
    }
}
