package com.example.wspnew.utils;

import com.example.wspnew.users.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class deleteData extends Thread {
    String login, password;
    public deleteData(String login, String password) {
        this.login = login;
        this.password = password;
    }
    @Override
    public void run() {
        try {
            JSONArray newJsonArray = new JSONArray();
            for (int i = 0; i < Storage.users.length(); i++) {
                JSONObject cur = Storage.users.getJSONObject(i);
                if(!cur.getString("login").equals(login) || !cur.getString("password").equals(password)) {
                    newJsonArray.put(cur);
                }
            }
            URL url = new URL(Storage.urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Access-Key", Storage.accessKey);
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            OutputStream out = connection.getOutputStream();
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("users", newJsonArray);
            Storage.users = newJsonArray;
            out.write(tmpObj.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println(connection.getResponseMessage());
            out.flush();
            out.close();
            connection.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
