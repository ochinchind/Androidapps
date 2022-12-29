package com.example.wspnew.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.wspnew.R;
import com.example.wspnew.adapters.RecyclerViewAdapter;
import com.example.wspnew.adapters.RecyclerViewAdapterUpdateUser;
import com.example.wspnew.enums.Faculty;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.users.Admin;
import com.example.wspnew.users.Employee;
import com.example.wspnew.users.Student;
import com.example.wspnew.utils.Storage;

public class UpdateUserActivity extends AppCompatActivity {
    RecyclerView rv;
    Button btnAddUser;
    RecyclerViewAdapterUpdateUser recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        btnAddUser = findViewById(R.id.btnUpdateUserConfirm);
        rv = findViewById(R.id.rvUpdateUser);
        recyclerViewAdapter = new RecyclerViewAdapterUpdateUser(this, Storage.keys, Storage.values);
        rv.setAdapter(recyclerViewAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.notifyDataSetChanged();

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Storage.usertype);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateUserActivity.this, AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String firstname = recyclerViewAdapter.getString("firstName");
                    String lastname = recyclerViewAdapter.getString("lastName");
                    String login = recyclerViewAdapter.getString("login");
                    String password = recyclerViewAdapter.getString("password");
                    Gender gender = recyclerViewAdapter.getGender();
                    switch (Storage.usertype) {
                        case "Admin":
                            Admin admin = new Admin(Storage.usertype, firstname, lastname, login, password, gender);
                            AdminActivity.currentAdmin.updateUser(admin, Storage.loginForUpdatingUser, Storage.passwordForUpdatingUser);
                            break;
                        case "Student":
                            Student student = new Student(Storage.usertype, firstname, lastname, login, password, gender, Integer.parseInt(recyclerViewAdapter.getString("year")), Faculty.valueOf(recyclerViewAdapter.getString("faculty")), recyclerViewAdapter.getString("id"));
                            AdminActivity.currentAdmin.updateUser(student, Storage.loginForUpdatingUser, Storage.passwordForUpdatingUser);
                            break;
                        default:
                            Employee employee = new Employee(Storage.usertype, firstname, lastname, login, password, gender, recyclerViewAdapter.getString("id"), Float.parseFloat(recyclerViewAdapter.getString("salary")));
                            AdminActivity.currentAdmin.updateUser(employee, Storage.loginForUpdatingUser, Storage.passwordForUpdatingUser);
                            break;
                    }
                    Toast.makeText(v.getContext(), "Success!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(v.getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}