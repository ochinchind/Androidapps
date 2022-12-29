package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wspnew.R;
import com.example.wspnew.classes.News;
import com.example.wspnew.enums.NewsType;
import com.example.wspnew.users.Manager;
import com.example.wspnew.utils.Storage;

public class AddNewsActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText etTitle, etDescription;
    String title, description;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_one_news);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewsActivity.this, ManageNewsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        Button btnAddNew = findViewById(R.id.btnAddNew);
        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                if (radioId == -1){
                    Toast.makeText(v.getContext(), "Please select!", Toast.LENGTH_SHORT).show();
                } else {
                    title = etTitle.getText().toString();
                    description = etDescription.getText().toString();
                    String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                    News news = new News(title, description, currentDate, (Manager) Storage.currentUser, NewsType.valueOf((String) radioButton.getText()));
                    try {

                        ((Manager) Storage.currentUser).addNews(news);
                        System.out.println("yes");
                    }catch (Exception e) {
                        Toast.makeText(AddNewsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected: " + radioButton.getText(), Toast.LENGTH_SHORT);
    }


}