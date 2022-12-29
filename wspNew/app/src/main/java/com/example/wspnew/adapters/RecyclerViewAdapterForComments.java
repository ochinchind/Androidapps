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

public class RecyclerViewAdapterForComments extends RecyclerView.Adapter<RecyclerViewAdapterForComments.ViewHolder> {
    ArrayList<String> titles, descriptions;
    Context context;
    public RecyclerViewAdapterForComments(Context context, ArrayList<String> s1, ArrayList<String> s2) {
        this.context = context;
        this.titles = s1;
        this.descriptions = s2;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterForComments.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_comment, parent, false);
        return new RecyclerViewAdapterForComments.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterForComments.ViewHolder holder, int position) {
        holder.author.setText(titles.get(position));
        holder.desc.setText(descriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.tvTest1);
            desc = itemView.findViewById(R.id.tvDescriptionOfComment);
        }
    }
}
