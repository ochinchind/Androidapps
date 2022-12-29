package com.example.wspnew.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class coursesFetchData extends Thread {
    @Override
    public void run() {
        String data = "";
        try {
            URL url = new URL(Storage.coursesUrlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                data += line;
            }
            if(!data.isEmpty()) {
                JSONObject jsonObject = (JSONObject) new JSONObject(data).get("record");
                JSONArray course = jsonObject.getJSONArray("courses");
                Storage.courses = course;
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
