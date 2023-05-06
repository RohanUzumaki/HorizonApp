package com.example.horizonapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {
    private final List<String> departmentNames;

    public DepartmentAdapter(List<String> departmentNames) {
        this.departmentNames = departmentNames;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.department_item, parent, false);
        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        String departmentName = departmentNames.get(position);
        holder.departmentNameTextView.setText(departmentName);
    }

    @Override
    public int getItemCount() {
        return departmentNames.size();
    }

    static class DepartmentViewHolder extends RecyclerView.ViewHolder {
        TextView departmentNameTextView;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentNameTextView = itemView.findViewById(R.id.department_item);
        }
    }
}
