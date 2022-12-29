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
import com.example.wspnew.adapters.RecyclerViewAdapterForStudentsCourses;
import com.example.wspnew.adapters.RecyclerViewAdapterNews;
import com.example.wspnew.utils.Storage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageStudentsActivity extends AppCompatActivity {
    ArrayList<String> students;

    RecyclerView rv;
    ArrayList<ArrayList<String>> courses;
    SwipeRefreshLayout swipeRefreshLayout;

    void refresh() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        for(int i = 0; i < Storage.users.length(); i++) {
            ArrayList<String> tmpCourses = new ArrayList<>();
            try {
                JSONObject users = Storage.users.getJSONObject(i);
                String name = users.getString("firstName") + " " + users.getString("lastName");
                String userType = users.getString("usertype");
                if(userType.equals("Student")) {
                    students.add(name);
                    JSONArray courses1 = (users.getJSONArray("courses"));
                    for(int j = 0; j < courses1.length(); j++) {
                        JSONObject curCourse = courses1.getJSONObject(j);
                        tmpCourses.add(curCourse.getString("id"));
                    }
                    courses.add(tmpCourses);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rv = findViewById(R.id.recyclerView);
            RecyclerViewAdapterForStudentsCourses recyclerViewAdapter = new RecyclerViewAdapterForStudentsCourses(this, students, courses);
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
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageStudentsActivity.this, ManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }
}