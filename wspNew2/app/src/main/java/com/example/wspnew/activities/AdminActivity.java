package com.example.wspnew.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.wspnew.R;
import com.example.wspnew.R.id;
import com.example.wspnew.adapters.RecyclerViewAdapter;
import com.example.wspnew.users.Admin;
import com.example.wspnew.utils.Storage;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    public static Admin currentAdmin = (Admin) Storage.currentUser;
    RecyclerView rv;
    ArrayList<String> names;
    ArrayList<String> userTypes;
    ArrayList<ArrayList<String>> keys, values;
    SwipeRefreshLayout swipeRefreshLayout;

    public void gotoUpdateUser(String login, String password) {
        Intent intent = new Intent(AdminActivity.this, UpdateUserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    void refresh() {
        System.out.println(Storage.users);
        names = new ArrayList<>();
        userTypes = new ArrayList<>();
        keys = new ArrayList<>();
        values = new ArrayList<>();
        for(int i = 0; i < Storage.users.length(); i++) {
            ArrayList<String> tmpKeys = new ArrayList<>();
            ArrayList<String> tmpValues = new ArrayList<>();
            try {
                JSONObject user = Storage.users.getJSONObject(i);
                String name = user.getString("firstName") + " " + user.getString("lastName");
                String userType = user.getString("usertype");
                names.add(name);
                userTypes.add(userType);
                for(int j = 0; j < user.names().length(); j++) {
                    if(!user.names().getString(j).equals("firstName") &&
                            !user.names().getString(j).equals("lastName") &&
                            !user.names().getString(j).equals("usertype") &&
                            (user.get(user.names().getString(j)) instanceof Integer ||
                                    user.get(user.names().getString(j)) instanceof String ||
                                    user.get(user.names().getString(j)) instanceof Float)) {
                        tmpKeys.add(user.names().getString(j));
                        tmpValues.add(user.getString(user.names().getString(j)));
                    } else if(user.names().getString(j).equals("firstName")) {
                        Storage.firstNames.add(user.getString(user.names().getString(j)));
                    } else if(user.names().getString(j).equals("lastName")) {
                        Storage.lastNames.add(user.getString(user.names().getString(j)));
                    }
                }
                keys.add(tmpKeys);
                values.add(tmpValues);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        rv = findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, names, userTypes, keys, values);
        rv.setAdapter(recyclerViewAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        swipeRefreshLayout = findViewById(id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        refresh();

        Toolbar toolbar = findViewById(id.toolBar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                Storage.currentUser = null;
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
            case R.id.checkActivities:
                return true;
            case R.id.addUser:
                Intent intent2 = new Intent(AdminActivity.this, AddUserActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}