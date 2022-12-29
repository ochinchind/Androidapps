package com.example.wspnew.activities;

import static com.example.wspnew.utils.Storage.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class AddCourseToTeacherActivity extends AppCompatActivity {

    ArrayList<String> teachers;

    RecyclerView rv;
    ArrayList<ArrayList<String>> keys, values;
    SwipeRefreshLayout swipeRefreshLayout;

    void refresh() {
        teachers = new ArrayList<>();
        keys = new ArrayList<>();
        values = new ArrayList<>();
        for(int i = 0; i < users.length(); i++) {
            ArrayList<String> tmpKeys = new ArrayList<>();
            ArrayList<String> tmpValues = new ArrayList<>();
            try {
                JSONObject user = users.getJSONObject(i);
                String name = user.getString("firstName") + " " + user.getString("lastName");
                String userType = user.getString("usertype");
                if(userType.equals("Teacher")) {
                    teachers.add(name);
                    for (int j = 0; j < user.names().length(); j++) {
                        tmpKeys.add(user.names().getString(j));
                        tmpValues.add(user.getString(user.names().getString(j)));
                    }
                    keys.add(tmpKeys);
                    values.add(tmpValues);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rv = findViewById(R.id.recyclerView);
            RecyclerViewAdapterNews recyclerViewAdapter = new RecyclerViewAdapterNews(this, teachers, keys, values);
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
        setContentView(R.layout.activity_add_course_to_teacher);
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

    }




}