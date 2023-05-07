package com.example.horizonapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    private final List<String> mDepartmentNames;

    public DepartmentAdapter(List<String> departmentNames) {
        mDepartmentNames = departmentNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String departmentName = mDepartmentNames.get(position);
        holder.departmentNameTextView.setText(departmentName);
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DoctorsActivity.class);
            intent.putExtra("departmentName", departmentName);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mDepartmentNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView departmentNameTextView;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentNameTextView = itemView.findViewById(R.id.department_item);
            cardView = itemView.findViewById(R.id.card_view_dept);
        }
    }
}
