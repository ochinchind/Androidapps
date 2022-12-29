package com.example.wspnew.utils;


import com.example.wspnew.activities.AdminActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class addData extends Thread {
    String jsonString;
    public addData(String jsonString) {
        this.jsonString = jsonString;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(Storage.urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Access-Key", Storage.accessKey);
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            OutputStream out = connection.getOutputStream();
            boolean doesExist = false;
            JSONObject newObj = new JSONObject(jsonString);
            for(int i = 0; i < Storage.users.length(); i++) {
                JSONObject cur = (JSONObject) Storage.users.get(i);
                String login = cur.getString("login"), password = cur.getString("password");
                if(newObj.getString("login").equals(login) &&
                        newObj.getString("password").equals(password)) {
                    doesExist = true;
                    break;
                }
            }
            if(!doesExist) {
                Storage.users.put(newObj);
            }
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("users", Storage.users);
            out.write(tmpObj.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println(connection.getResponseMessage());
            System.out.println(jsonString);
            out.flush();
            out.close();
            connection.disconnect();
            new fetchData().start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
