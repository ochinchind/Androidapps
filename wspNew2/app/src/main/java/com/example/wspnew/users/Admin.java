package com.example.wspnew.users;

import com.example.wspnew.enums.Gender;
import com.example.wspnew.utils.addData;
import com.example.wspnew.utils.deleteData;
import com.example.wspnew.utils.updateData;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

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
    public void deleteUser(String login, String password) {
        new deleteData(login, password).start();
    }
    public void updateUser(User user, String login, String password) {
        new updateData(user, login, password).start();
    }
}
