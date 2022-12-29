package com.example.wspnew.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wspnew.R;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
                }
                else {
                    holder.rv.setVisibility(View.GONE);
                }
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
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tvTest1);
            tv2 = itemView.findViewById(R.id.tvTest2);
            rv = itemView.findViewById(R.id.rvDescription);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
