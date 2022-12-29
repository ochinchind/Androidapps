package com.example.wspnew.utils;

import com.example.wspnew.activities.AdminActivity;
import com.example.wspnew.users.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class updateData extends Thread {
    User newUser;
    String login, password;
    public updateData(User newUser, String login, String password) {
        this.newUser = newUser;
        this.login = login;
        this.password = password;
    }
    @Override
    public void run() {
        try {
            AdminActivity.currentAdmin.deleteUser(login, password);
            sleep(2000);
            AdminActivity.currentAdmin.addUser(newUser);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
