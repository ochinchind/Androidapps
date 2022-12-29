package com.example.wspnew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;
import com.example.wspnew.enums.Gender;

import java.util.ArrayList;

public class RecyclerViewAdapterUpdateUser extends RecyclerView.Adapter<RecyclerViewAdapterUpdateUser.ViewHolder> {
    ArrayList<String> keys, values;
    Context context;
    ArrayList<EditText> etValues = new ArrayList<>();

    public RecyclerViewAdapterUpdateUser(Context context, ArrayList<String> keys, ArrayList<String> values) {
        this.keys = keys;
        this.values = values;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterUpdateUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_row3, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterUpdateUser.ViewHolder holder, int position) {
        holder.key.setText(keys.get(position));
        holder.value.setText(values.get(position));
        etValues.add(holder.value);
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView key, tv;
        public EditText value;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.tvKey);
            tv = itemView.findViewById(R.id.textView);
            value = itemView.findViewById(R.id.etValue);
        }
    }
    public Gender getGender() {
        int position = 0;
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals("gender")) {
                position = i;
                break;
            }
        }
        Gender gender = Gender.valueOf(etValues.get(position).getText().toString());
        return gender;
    }
    public String getString(String key) {
        int position = 0;
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i).equals(key)) {
                position = i;
                break;
            }
        }
        return etValues.get(position).getText().toString();
    }
}
