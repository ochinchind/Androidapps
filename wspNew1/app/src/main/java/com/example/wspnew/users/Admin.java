package com.example.wspnew.users;

import com.example.wspnew.enums.Gender;
import com.example.wspnew.utils.addData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Admin extends User {
    public Admin(String usertype, String firstName, String lastname, String login, String password, Gender gender) {
        super(usertype, firstName, lastname, login, password, gender);
    }
    public void addUser(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(user);
            new addData(json).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(User user) {

    }
}
