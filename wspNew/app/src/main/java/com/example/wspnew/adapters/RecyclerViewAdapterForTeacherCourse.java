package com.example.wspnew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;

import java.util.ArrayList;

public class RecyclerViewAdapterForTeacherCourse extends RecyclerView.Adapter<RecyclerViewAdapterForTeacherCourse.ViewHolder> {
    ArrayList<String> ids;
    Context context;
    public RecyclerViewAdapterForTeacherCourse(Context context, ArrayList<String> ids) {
        this.context = context;
        this.ids = ids;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterForTeacherCourse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_teacher_courses, parent, false);
        return new RecyclerViewAdapterForTeacherCourse.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForTeacherCourse.ViewHolder holder, int position) {
        holder.id.setText(ids.get(position));
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvTest1);
        }
    }
}