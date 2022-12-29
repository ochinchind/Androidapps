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
import com.example.wspnew.users.Student;
import com.example.wspnew.utils.Getters;

import java.util.ArrayList;

public class RecyclerViewAdapterForStudentsCourses extends RecyclerView.Adapter<RecyclerViewAdapterForStudentsCourses.ViewHolder> {
    ArrayList<String> names;
    ArrayList<ArrayList<String>> courseId;
    Context context;

    public RecyclerViewAdapterForStudentsCourses(Context context, ArrayList<String> names, ArrayList<ArrayList<String>> courseId) {
        this.names = names;
        this.courseId = courseId;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForStudentsCourses.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_student_course, parent, false);
        return new RecyclerViewAdapterForStudentsCourses.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForStudentsCourses.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.studentName.setText(names.get(position));
        RecyclerViewAdapterForTeacherCourse recyclerView = new RecyclerViewAdapterForTeacherCourse(context, courseId.get(position));
        holder.studentCourses.setAdapter(recyclerView);
        holder.studentCourses.setLayoutManager(new LinearLayoutManager(context));
        holder.btnAddCourseToStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
                myDialog.setTitle("Add course");
                EditText etComment = new EditText(context);
                etComment.setInputType(InputType.TYPE_CLASS_TEXT);
                myDialog.setView(etComment);
                myDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = etComment.getText().toString();
                        try {
                            Course course = Getters.getCourse(id);
                            if(course != null) {
                                ManagerActivity.currentManager.assignCourseToStudent(Getters.getStudent(names.get(position)), course);
                                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e) {
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
        TextView studentName;
        RecyclerView studentCourses;
        Button btnAddCourseToStudent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.tvTest1);
            studentCourses = itemView.findViewById(R.id.rvDescription);
            btnAddCourseToStudent = itemView.findViewById(R.id.btnAddCourseToStudent);
        }
    }

}
