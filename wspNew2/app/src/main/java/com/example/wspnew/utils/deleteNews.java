package com.example.wspnew.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class deleteNews extends Thread {
    String title, description;
    public deleteNews(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public void run() {
        try {
            JSONArray newJsonArray = new JSONArray();
            for (int i = 0; i < Storage.news.length(); i++) {
                JSONObject cur = Storage.news.getJSONObject(i);
                if(!cur.getString("title").equals(title) || !cur.getString("description").equals(description)) {
                    newJsonArray.put(cur);
                }
            }
            URL url = new URL(Storage.newsUrlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Access-Key", Storage.accessKey);
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            OutputStream out = connection.getOutputStream();
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("news", newJsonArray);
            Storage.news = newJsonArray;
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
