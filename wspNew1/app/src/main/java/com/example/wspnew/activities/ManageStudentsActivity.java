package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.wspnew.R;
import com.example.wspnew.adapters.RecyclerViewAdapterNews;
import com.example.wspnew.utils.Storage;

import org.json.JSONObject;

import java.util.ArrayList;

public class ManageStudentsActivity extends AppCompatActivity {
    ArrayList<String> titles;

    RecyclerView rv;
    ArrayList<ArrayList<String>> keys, values;
    SwipeRefreshLayout swipeRefreshLayout;

    void refresh() {
        titles = new ArrayList<>();
        keys = new ArrayList<>();
        values = new ArrayList<>();
        for(int i = 0; i < Storage.news.length(); i++) {
            ArrayList<String> tmpKeys = new ArrayList<>();
            ArrayList<String> tmpValues = new ArrayList<>();
            try {
                JSONObject news = Storage.news.getJSONObject(i);
                String name = news.getString("title");
                titles.add(name);
                for(int j = 0; j < news.names().length(); j++) {
                    if(news.names().getString(j).equals("author")) {
                        JSONObject author = new JSONObject(news.getString("author"));
                        String authorString = author.getString("firstName") + " " + author.getString("lastName");
                        tmpKeys.add("author");
                        tmpValues.add(authorString);
                    }
                    else if(!news.names().getString(j).equals("title")) {
                        tmpKeys.add(news.names().getString(j));
                        tmpValues.add(news.getString(news.names().getString(j)));
                    }
                }
                keys.add(tmpKeys);
                values.add(tmpValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rv = findViewById(R.id.recyclerView);
            RecyclerViewAdapterNews recyclerViewAdapter = new RecyclerViewAdapterNews(this, titles, keys, values);
            rv.setAdapter(recyclerViewAdapter);
            rv.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
    }
}