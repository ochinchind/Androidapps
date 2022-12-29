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
import com.example.wspnew.adapters.RecyclerViewAdapterForTeacherCourse;
import com.example.wspnew.adapters.RecyclerViewAdapterForTeacherCourses;
import com.example.wspnew.utils.Storage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ManageTeachersActivity extends AppCompatActivity {
    ArrayList<String> teacherNames;
    ArrayList<ArrayList<String>> courseIds;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    public void refresh() {
        teacherNames = new ArrayList<>();
        courseIds = new ArrayList<>();
        try {
            for (int i = 0; i < Storage.users.length(); i++) {
                ArrayList<String> tmp = new ArrayList<>();
                JSONObject user = Storage.users.getJSONObject(i);
                if(user.getString("usertype").equals("Teacher")) {
                    teacherNames.add(user.getString("firstName") + " " + user.getString("lastName"));
                    JSONArray teaches = user.getJSONArray("teaches");
                    for(int j = 0; j < teaches.length(); j++) {
                        JSONObject course = teaches.getJSONObject(j);
                        tmp.add(course.getString("id"));
                    }
                    courseIds.add(tmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(teacherNames.toString());
        System.out.println(courseIds.toString());
        rv = findViewById(R.id.recyclerView);
        RecyclerViewAdapterForTeacherCourses adapter = new RecyclerViewAdapterForTeacherCourses(this, teacherNames, courseIds);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teachers);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageTeachersActivity.this, ManageCoursesActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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