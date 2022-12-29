package com.example.wspnew.utils;

import android.app.ProgressDialog;
import android.view.View;

import com.example.wspnew.activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetchData extends Thread {

    @Override
    public void run() {
        String data = "";
        try {
            URL url = new URL(Storage.urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Master-Key", Storage.masterKey);
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                data += line;
            }
            MainActivity.loginList.clear();
            MainActivity.passwordList.clear();
            if(!data.isEmpty()) {
                JSONObject jsonObject = (JSONObject) new JSONObject(data).get("record");
                JSONArray users = jsonObject.getJSONArray("users");
                Storage.users = users;
                for(int i = 0; i < users.length(); i++) {
                    JSONObject user = users.getJSONObject(i);
                    String login = user.getString("login");
                    String password = user.getString("password");
                    MainActivity.loginList.add(login);
                    MainActivity.passwordList.add(password);
                }
            }
            connection.disconnect();
            MainActivity.isFetching = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
