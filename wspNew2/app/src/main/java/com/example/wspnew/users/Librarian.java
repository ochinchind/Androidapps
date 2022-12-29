package com.example.wspnew.users;

import com.example.wspnew.enums.Gender;

public class Librarian extends Employee {
    public Librarian(String usertype, String firstName, String lastname, String login, String password, Gender gender, String id, float salary) {
        super(usertype, firstName, lastname, login, password, gender, id, salary);
    }
}