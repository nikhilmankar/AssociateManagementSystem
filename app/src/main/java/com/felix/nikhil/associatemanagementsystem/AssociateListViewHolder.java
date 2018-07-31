package com.felix.nikhil.associatemanagementsystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssociateListViewHolder extends RecyclerView.ViewHolder {

    TextView txtName, txtMobileNumber, txtDepartment, txtSalary;
    Button btnDelete;

    public AssociateListViewHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txt_Name);
        txtMobileNumber = itemView.findViewById(R.id.txt_MobileNumber);
        txtDepartment = itemView.findViewById(R.id.txt_Department);
        txtSalary = itemView.findViewById(R.id.txt_Salary);
        btnDelete = itemView.findViewById(R.id.btn_Delete);
    }
}
