package com.example.wspnew.utils;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class addCourse extends Thread {
    String json;
    public addCourse(String json) {
        this.json = json;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(Storage.coursesUrlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Access-Key", Storage.accessKey);
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            OutputStream out = connection.getOutputStream();
            boolean doesExist = false;
            JSONObject newObj = new JSONObject(json);
            for(int i = 0; i < Storage.courses.length(); i++) {
                JSONObject cur = (JSONObject) Storage.courses.get(i);
                String id = cur.getString("id");
                if(newObj.getString("id").equals(id)) {
                    doesExist = true;
                    break;
                }
            }
            if(!doesExist) {
                Storage.courses.put(newObj);
            }
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("courses", Storage.courses);
            out.write(tmpObj.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println(connection.getResponseMessage());
            System.out.println(json);
            out.flush();
            out.close();
            connection.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
