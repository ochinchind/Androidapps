package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wspnew.R;
import com.example.wspnew.enums.Faculty;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.users.Admin;
import com.example.wspnew.users.Manager;
import com.example.wspnew.users.Student;
import com.example.wspnew.users.Teacher;
import com.example.wspnew.users.User;
import com.example.wspnew.utils.NewsFetchData;
import com.example.wspnew.utils.Storage;
import com.example.wspnew.utils.coursesFetchData;
import com.example.wspnew.utils.fetchData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private EditText etLogin;
    private EditText etPassword;

    private JSONObject matched;
    public static boolean isFetching = false;

    public static ArrayList<String> loginList = new ArrayList<>();
    public static ArrayList<String> passwordList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFetching = true;
                new fetchData().start();
                new coursesFetchData().start();
                new NewsFetchData().start();
                String inputLogin = etLogin.getText().toString();
                String inputPassword = etPassword.getText().toString();

                if (inputLogin.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the details correctly", Toast.LENGTH_SHORT).show();
                } else {
                    long cnt = 0;
                    while(isFetching) {
                        cnt++;
                        if(cnt >= 1e9 * 5) {
                            Toast.makeText(MainActivity.this, "You are connected to a potato", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    boolean isLogin = false;
                    try {
                        if (!isFetching) {
                            for (int i = 0; i < loginList.size(); i++) {
                                if (inputLogin.equals(loginList.get(i)) && inputPassword.equals(passwordList.get(i))) {
                                    isLogin = true;
                                    matched = Storage.users.getJSONObject(i);
                                    break;
                                }
                            }
                            if (isLogin) {
                                Toast.makeText(MainActivity.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                                String usertype = matched.getString("usertype");
                                String firstName = matched.getString("firstName");
                                String lastName = matched.getString("lastName");
                                String login = matched.getString("login");
                                String password = matched.getString("password");
                                Gender gender = Gender.valueOf(matched.getString("gender"));
                                if (matched.getString("usertype").equals("Admin")) {
                                    Admin admin = new Admin(usertype, firstName, lastName, login, password, gender);
                                    Storage.currentUser = admin;
                                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else if (matched.getString("usertype").equals("Manager")) {
                                    String id = matched.getString(("id"));
                                    float salary = Float.parseFloat(matched.getString(("salary")));
                                    Manager manager = new Manager(usertype, firstName, lastName, login, password, gender, id, salary);
                                    Storage.currentUser = manager;
                                    Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else if(matched.getString("usertype").equals("Teacher")) {
                                    String id = matched.getString(("id"));
                                    float salary = Float.parseFloat(matched.getString(("salary")));
                                    Teacher teacher = new Teacher(usertype, firstName, lastName, login, password, gender, id, salary);
                                    Storage.currentUser = teacher;
                                    Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else if(matched.getString("usertype").equals("Student")) {
                                    String id = matched.getString(("id"));
                                    int year = matched.getInt("year");
                                    Faculty faculty = Faculty.valueOf(matched.getString("faculty"));
                                    Student student = new Student(usertype, firstName, lastName, login, password, gender, year, faculty, id);
                                    Storage.currentUser = student;
                                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong login or password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}