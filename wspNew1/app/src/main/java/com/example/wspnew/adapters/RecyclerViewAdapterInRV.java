package com.example.wspnew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;

import java.util.ArrayList;

public class RecyclerViewAdapterInRV extends RecyclerView.Adapter<RecyclerViewAdapterInRV.ViewHolder> {
    ArrayList<String> data1, data2;
    Context context;
    public RecyclerViewAdapterInRV(Context context, ArrayList<String> s1, ArrayList<String> s2) {
        this.context = context;
        this.data1 = s1;
        this.data2 = s2;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterInRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_row2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterInRV.ViewHolder holder, int position) {
        holder.tv1.setText(data1.get(position));
        holder.tv2.setText(data2.get(position));
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tvRv1);
            tv2 = itemView.findViewById(R.id.tvRv3);
        }
    }
}
