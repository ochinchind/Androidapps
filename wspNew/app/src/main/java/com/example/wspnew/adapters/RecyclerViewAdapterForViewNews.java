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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;
import com.example.wspnew.activities.ManagerActivity;
import com.example.wspnew.classes.Comment;
import com.example.wspnew.classes.News;
import com.example.wspnew.utils.Getters;
import com.example.wspnew.utils.Storage;
import com.example.wspnew.utils.updateNews;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecyclerViewAdapterForViewNews extends RecyclerView.Adapter<RecyclerViewAdapterForViewNews.ViewHolder> {
    ArrayList<String> data1, data2;
    ArrayList<ArrayList<String>> keys, values;
    Context context;
    public RecyclerViewAdapterForViewNews(Context context, ArrayList<String> s1, ArrayList<String> s2, ArrayList<ArrayList<String>> keys, ArrayList<ArrayList<String>> values) {
        this.context = context;
        this.data1 = s1;
        this.data2 = s2;
        this.keys = keys;
        this.values = values;
    }
    @NonNull
    @Override
    public RecyclerViewAdapterForViewNews.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_news, parent, false);
        return new RecyclerViewAdapterForViewNews.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForViewNews.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(data1.get(position));
        holder.desc.setText(data2.get(position));
        RecyclerViewAdapterForComments recyclerView = new RecyclerViewAdapterForComments(context, keys.get(position), values.get(position));
        holder.rvComments.setAdapter(recyclerView);
        holder.rvComments.setLayoutManager(new LinearLayoutManager(context));
        holder.btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
                myDialog.setTitle("Add comment");
                EditText etComment = new EditText(context);
                etComment.setInputType(InputType.TYPE_CLASS_TEXT);
                myDialog.setView(etComment);
                myDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        News news = Getters.getNews(holder.title.getText().toString(), holder.desc.getText().toString());
                        System.out.println(news.toString());
                        String comment = etComment.getText().toString();
                        Comment newComment = new Comment(Storage.currentUser, new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date()), comment);
                        new updateNews(newComment, news).start();

                        RecyclerViewAdapterForComments recyclerView = new RecyclerViewAdapterForComments(context, keys.get(position), values.get(position));
                        holder.rvComments.setAdapter(recyclerView);
                        holder.rvComments.setLayoutManager(new LinearLayoutManager(context));
                    }
                });
                myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                myDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, tvComments;
        RecyclerView rvComments;
        Button btnAddComment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTest1);
            desc = itemView.findViewById(R.id.tvDescriptionForNews);
            tvComments = itemView.findViewById(R.id.tvComments);
            rvComments = itemView.findViewById(R.id.rvComments);
            btnAddComment = itemView.findViewById(R.id.btnAddComment);
        }
    }
}
