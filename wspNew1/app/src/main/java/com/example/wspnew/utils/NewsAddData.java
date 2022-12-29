package com.example.wspnew.utils;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NewsAddData  extends Thread {
    String jsonString;
    public NewsAddData(String jsonString) {
        this.jsonString = jsonString;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(Storage.newsUrlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Access-Key", Storage.accessKey);
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            OutputStream out = connection.getOutputStream();
            JSONObject newObj = new JSONObject(jsonString);
            Storage.news.put(newObj);
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("news", Storage.news);
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
