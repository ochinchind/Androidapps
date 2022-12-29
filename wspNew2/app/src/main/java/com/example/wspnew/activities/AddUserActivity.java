package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wspnew.R;
import com.example.wspnew.enums.Faculty;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.users.Admin;
import com.example.wspnew.users.Librarian;
import com.example.wspnew.users.Manager;
import com.example.wspnew.users.Student;
import com.example.wspnew.users.Teacher;
import com.example.wspnew.users.User;

public class AddUserActivity extends AppCompatActivity {
    Spinner selectUserType, selectGender;
    Button btnAddUser;

    EditText etID, etYear, etFaculty; // Student
    EditText etFirstName, etLastName, etLogin, etPassword; // User
    EditText etIDEmployee, etSalary; // Employee

    String userType, userFirstName, userLastName, userLogin, userPassword, userGender;
    String studentID, studentYear, studentFaculty;
    String employeeID, employeeSalary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddUserActivity.this, AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        // Student
        etID = findViewById(R.id.etID);
        selectGender = findViewById(R.id.selectGender);
        selectUserType = findViewById(R.id.selectUserType);
        etYear  = findViewById(R.id.etYear);
        etFaculty = findViewById(R.id.etFaculty);

        // User
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);

        // Employee
        etSalary = findViewById(R.id.etSalary);
        etIDEmployee = findViewById(R.id.etIDEmployee);

        btnAddUser = findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userFirstName = etFirstName.getText().toString();
                userLastName = etLastName.getText().toString();
                userLogin = etLogin.getText().toString();
                userPassword = etPassword.getText().toString();
                studentID = etID.getText().toString();
                studentYear = etYear.getText().toString();
                studentFaculty = etFaculty.getText().toString();
                employeeSalary = etSalary.getText().toString();
                employeeID = etIDEmployee.getText().toString();

                try {
                    switch (userType) {
                        case "Student":
                            User student = new Student(userType, userFirstName, userLastName, userLogin, userPassword, Gender.valueOf(userGender), Integer.parseInt(studentYear), Faculty.valueOf(studentFaculty), studentID);
                            AdminActivity.currentAdmin.addUser(student);
                            break;
                        case "Admin":
                            User admin = new Admin(userType, userFirstName, userLastName, userLogin, userPassword, Gender.valueOf(userGender));
                            AdminActivity.currentAdmin.addUser(admin);
                            break;
                        case "Teacher":
                            User teacher = new Teacher(userType, userFirstName, userLastName, userLogin, userPassword, Gender.valueOf(userGender), employeeID, Float.parseFloat(employeeSalary));
                            AdminActivity.currentAdmin.addUser(teacher);
                            break;
                        case "Manager":
                            User manager = new Manager(userType, userFirstName, userLastName, userLogin, userPassword, Gender.valueOf(userGender), employeeID, Float.parseFloat(employeeSalary));
                            AdminActivity.currentAdmin.addUser(manager);
                            break;
                        case "Librarian":
                            User librarian = new Librarian(userType, userFirstName, userLastName, userLogin, userPassword, Gender.valueOf(userGender), employeeID, Float.parseFloat(employeeSalary));
                            AdminActivity.currentAdmin.addUser(librarian);
                            break;
                    }
                    Toast.makeText(AddUserActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(AddUserActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.userTypes, R.layout.custom_spinner);
        adapter1.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        selectUserType.setAdapter(adapter1);
        selectUserType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userType = parent.getItemAtPosition(position).toString();
                if(userType.equals("Student")) {
                    etID.setVisibility(View.VISIBLE);
                    etYear.setVisibility(View.VISIBLE);
                    etFaculty.setVisibility(View.VISIBLE);
                    etSalary.setVisibility(View.GONE);
                    etIDEmployee.setVisibility(View.GONE);
                }
                else if(userType.equals("Admin")) {
                    etSalary.setVisibility(View.GONE);
                    etIDEmployee.setVisibility(View.GONE);
                    etID.setVisibility(View.GONE);
                    etYear.setVisibility(View.GONE);
                    etFaculty.setVisibility(View.GONE);
                }
                else {
                    etID.setVisibility(View.GONE);
                    etYear.setVisibility(View.GONE);
                    etFaculty.setVisibility(View.GONE);
                    etSalary.setVisibility(View.VISIBLE);
                    etIDEmployee.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.genders, R.layout.custom_spinner);
        adapter2.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        selectGender.setAdapter(adapter2);
        selectGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userGender = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}