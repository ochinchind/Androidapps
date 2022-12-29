package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wspnew.R;
import com.example.wspnew.classes.Course;
import com.example.wspnew.enums.Faculty;

public class AddCourseActivity extends AppCompatActivity {
    EditText faculty, title, id, credits;
    Button btnAddCourse;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCourseActivity.this, ManagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        faculty = findViewById(R.id.courseFaculty);
        title = findViewById(R.id.courseTitle);
        id = findViewById(R.id.courseID);
        credits = findViewById(R.id.courseCredits);
        btnAddCourse = findViewById(R.id.addCourse);
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Faculty fac = Faculty.valueOf(faculty.getText().toString());
                    String courseID = id.getText().toString();
                    String courseTitle = title.getText().toString();
                    int cred = Integer.parseInt(credits.getText().toString());
                    Course course = new Course(cred, courseTitle, courseID, fac);
                    ManagerActivity.currentManager.addCourse(course);
                    Toast.makeText(AddCourseActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AddCourseActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}