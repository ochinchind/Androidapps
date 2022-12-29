package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wspnew.R;
import com.example.wspnew.adapters.RecyclerViewAdapterForViewNews;
import com.example.wspnew.utils.Storage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    RecyclerView rvNews;
    ArrayList<String> titles, descriptions;
    ArrayList<ArrayList<String>> commentAuthors, commentDescriptions;
    SwipeRefreshLayout swipeRefreshLayout;

    void refresh() {
        titles = new ArrayList<>();
        descriptions = new ArrayList<>();
        commentAuthors = new ArrayList<>();
        commentDescriptions = new ArrayList<>();
        try {
            for (int i = 0; i < Storage.news.length(); i++) {
                ArrayList<String> tmpAuthors = new ArrayList<>();
                ArrayList<String> tmpDescs = new ArrayList<>();
                JSONObject news = Storage.news.getJSONObject(i);
                JSONArray comments = news.getJSONArray("comments");
                titles.add(news.getString("title"));
                descriptions.add(news.getString("description"));
                for(int j = 0; j < comments.length(); j++) {
                    JSONObject comment = comments.getJSONObject(j);
                    JSONObject author = comment.getJSONObject("author");
                    String name = author.getString("firstName") + " " + author.getString("lastName");
                    tmpAuthors.add(name);
                    tmpDescs.add(comment.getString("text"));
                }
                commentAuthors.add(tmpAuthors);
                commentDescriptions.add(tmpDescs);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        rvNews = findViewById(R.id.rvNews);
        RecyclerViewAdapterForViewNews recyclerViewAdapter = new RecyclerViewAdapterForViewNews(this, titles, descriptions, commentAuthors, commentDescriptions);
        rvNews.setAdapter(recyclerViewAdapter);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();
    }
}