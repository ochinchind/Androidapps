package com.example.wspnew.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.wspnew.activities.ManagerActivity;
import com.example.wspnew.users.Manager;
import com.example.wspnew.utils.Storage;

import java.util.ArrayList;

public class RecyclerViewAdapterNews extends RecyclerView.Adapter<RecyclerViewAdapterNews.ViewHolder> {
    ArrayList<String> titles;
    ArrayList<ArrayList<String>> keys, values;
    Context context;

    public RecyclerViewAdapterNews(Context ct, ArrayList<String> titles, ArrayList<ArrayList<String>> keys, ArrayList<ArrayList<String>> values) {
        this.context = ct;
        this.titles = titles;
        this.keys = keys;
        this.values = values;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterNews.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterNews.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(titles.get(position));
        RecyclerViewAdapterInRV recyclerViewAdapter = new RecyclerViewAdapterInRV(context, keys.get(position), values.get(position));
        holder.desc.setAdapter(recyclerViewAdapter);
        holder.desc.setLayoutManager(new LinearLayoutManager(context));

        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.desc.getVisibility() == View.GONE) {
                    holder.desc.setVisibility(View.VISIBLE);
                    holder.clForButton.setVisibility(View.VISIBLE);
                }
                else {
                    holder.desc.setVisibility(View.GONE);
                    holder.clForButton.setVisibility(View.GONE);
                }
            }
        });
        holder.btnDeleteNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titles.get(position), description = "";

                for(int i = 0; i < keys.get(position).size(); i++) {
                    if(keys.get(position).get(i).equals("description")) {
                        description = values.get(position).get(i);
                        break;
                    }
                }
                ManagerActivity.currentManager.deleteNews(title, description);
                Toast.makeText(context, "Successfully deleted! Refresh page to see the changes.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView desc;
        ConstraintLayout cl, clForButton;
        Button btnDeleteNews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            desc = itemView.findViewById(R.id.rvDescription);
            cl = itemView.findViewById(R.id.ctNews);
            clForButton = itemView.findViewById(R.id.cvForButtonNews);
            btnDeleteNews = itemView.findViewById(R.id.btnDeleteNews);
        }
    }
}
