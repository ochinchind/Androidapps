package com.example.wspnew.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;
import com.example.wspnew.activities.AdminActivity;
import com.example.wspnew.activities.MainActivity;
import com.example.wspnew.activities.UpdateUserActivity;
import com.example.wspnew.utils.Storage;
import com.example.wspnew.utils.deleteData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<String> data1, data2;
    ArrayList<ArrayList<String>> keys, values;
    Context context;
    public RecyclerViewAdapter(Context context, ArrayList<String> s1, ArrayList<String> s2, ArrayList<ArrayList<String>> keys, ArrayList<ArrayList<String>> values) {
        this.context = context;
        this.data1 = s1;
        this.data2 = s2;
        this.keys = keys;
        this.values = values;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv1.setText(data1.get(position));
        holder.tv2.setText(data2.get(position));
        RecyclerViewAdapterInRV recyclerViewAdapterInRV = new RecyclerViewAdapterInRV(getContext(), keys.get(position), values.get(position));
        holder.rv.setAdapter(recyclerViewAdapterInRV);
        holder.rv.setLayoutManager(new LinearLayoutManager(getContext()));

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.rv.getVisibility() == View.GONE) {
                    holder.rv.setVisibility(View.VISIBLE);
                    holder.btnLayout.setVisibility(View.VISIBLE);
                }
                else {
                    holder.rv.setVisibility(View.GONE);
                    holder.btnLayout.setVisibility(View.GONE);
                }
            }
        });
        holder.btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = "", password = "";
                boolean loginFound = false, passwordFound = false;
                for(int i = 0; i < keys.get(position).size(); i++) {
                    if(!loginFound && keys.get(position).get(i).equals("login")) {
                        login = values.get(position).get(i);
                        loginFound = true;
                    }
                    if(!passwordFound && keys.get(position).get(i).equals("password")) {
                        password = values.get(position).get(i);
                        passwordFound = true;
                    }
                    if(loginFound && passwordFound) break;
                }
                try {
                    AdminActivity.currentAdmin.deleteUser(login, password);
                    Toast.makeText(context, "Refresh the page to see the changes", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                Storage.keys = keys.get(position);
                Storage.values = values.get(position);
                Storage.usertype = data2.get(position);
                Storage.keys.add("firstName");
                Storage.values.add(Storage.firstNames.get(position));
                Storage.keys.add("lastName");
                Storage.values.add(Storage.lastNames.get(position));

                Storage.loginForUpdatingUser = getString("login");
                Storage.passwordForUpdatingUser = getString("password");
                Intent intent = new Intent(v.getContext(), UpdateUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        RecyclerView rv;
        Button btnUpdateUser, btnDeleteUser;
        ConstraintLayout constraintLayout, btnLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tvTest1);
            tv2 = itemView.findViewById(R.id.tvTest2);
            rv = itemView.findViewById(R.id.rvDescription);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            btnDeleteUser = itemView.findViewById(R.id.btnDeleteUser);
            btnUpdateUser = itemView.findViewById(R.id.btnUpdateUser);
            btnLayout = itemView.findViewById(R.id.constraint);
        }
    }
    public String getString(String key) {
        int position = 0;
        for(int i = 0; i < keys.size(); i++) {
            if(Storage.keys.get(i).equals(key)) {
                position = i;
                break;
            }
        }
        return Storage.values.get(position);
    }
    public void refresh() {
        try {
            Storage.firstNames = new ArrayList<>();
            Storage.lastNames = new ArrayList<>();
            for (int i = 0; i < Storage.users.length(); i++) {
                JSONObject user = Storage.users.getJSONObject(i);
                Storage.firstNames.add(user.getString("firstName"));
                Storage.lastNames.add(user.getString("lastName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
