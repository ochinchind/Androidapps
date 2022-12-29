package com.example.wspnew.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;
import com.example.wspnew.activities.ManagerActivity;
import com.example.wspnew.classes.Course;
import com.example.wspnew.utils.Getters;

import java.util.ArrayList;

public class RecyclerViewAdapterForTeacherCourses extends RecyclerView.Adapter<RecyclerViewAdapterForTeacherCourses.ViewHolder> {
    ArrayList<String> names;
    ArrayList<ArrayList<String>> courses;
    Context context;
    public RecyclerViewAdapterForTeacherCourses(Context context, ArrayList<String> names, ArrayList<ArrayList<String>> courses) {
        this.names = names;
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForTeacherCourses.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_teacher_course, parent, false);
        return new RecyclerViewAdapterForTeacherCourses.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForTeacherCourses.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.teacherName.setText(names.get(position));
        RecyclerViewAdapterForTeacherCourse adapter = new RecyclerViewAdapterForTeacherCourse(context, courses.get(position));
        holder.teacherCourses.setAdapter(adapter);
        holder.teacherCourses.setLayoutManager(new LinearLayoutManager(context));
        holder.btnAddCourseToTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
                myDialog.setTitle("Add course(write down course id)");
                EditText etID = new EditText(context);
                etID.setInputType(InputType.TYPE_CLASS_TEXT);
                myDialog.setView(etID);
                myDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = etID.getText().toString();
                        try {
                            Course course = Getters.getCourse(id);
                            if(course != null) {
                                ManagerActivity.currentManager.assignCourseToTeacher(Getters.getTeacher(names.get(position)), course);
                                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                myDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teacherName;
        RecyclerView teacherCourses;
        Button btnAddCourseToTeacher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherName = itemView.findViewById(R.id.tvTest1);
            teacherCourses = itemView.findViewById(R.id.rvDescription);
            btnAddCourseToTeacher = itemView.findViewById(R.id.btnAddCourseToTeacher);
        }
    }
}
