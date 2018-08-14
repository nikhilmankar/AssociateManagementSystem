package com.felix.nikhil.associatemanagementsystem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AssociateListAdapter extends RecyclerView.Adapter<AssociateListViewHolder>{

    List<Associate> associateArrayList;
    public AssociateListAdapter(List<Associate> associateArrayList){
        this.associateArrayList=associateArrayList;
    }

    @NonNull
    @Override
    public AssociateListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_associate_list,parent,false);

        return new AssociateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssociateListViewHolder holder, int position) {
        Associate associate=associateArrayList.get(position);
        holder.txtName.setText(associate.getName());
        holder.txtMobileNumber.setText(associate.getMobilenumber());
        holder.txtDepartment.setText(associate.getDepartment());
        holder.txtSalary.setText(associate.getSalary());
        //holder.btnDelete.setImageResource(person.getImgid());
    }

    @Override
    public int getItemCount() {
        return associateArrayList.size();
    }
}
