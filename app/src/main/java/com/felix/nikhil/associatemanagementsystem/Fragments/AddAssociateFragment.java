package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.felix.nikhil.associatemanagementsystem.Associate;
import com.felix.nikhil.associatemanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAssociateFragment extends Fragment {

    public EditText edtName, edtMobileNumber, edtSalary;
    public Spinner spnDepartment;
    public Button btnSave, btnClear;
    public String userId;
    public DatabaseReference mFirebaseDatabase;
    public FirebaseDatabase mFirebaseInstance;

    public String associateId;

    public AddAssociateFragment() {
        // Required empty public constructor


        //android:digits="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_associate, container, false);
        edtName = view.findViewById(R.id.edt_Name);
        edtMobileNumber = view.findViewById(R.id.edt_MobileNumber);
        edtSalary = view.findViewById(R.id.edt_Salary);
        spnDepartment = view.findViewById(R.id.spn_Department);
        btnSave = view.findViewById(R.id.btn_Save);
        btnClear = view.findViewById(R.id.btn_Clear);
        //Toast.makeText(getActivity(), "fragment_add_associates", Toast.LENGTH_LONG).show();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Associates");

        // store app title to 'app_title' node
        //mFirebaseInstance.getReference("Asscociates").setValue("Associate Management System Database");
        // app_title change listener
        mFirebaseInstance.getReference("Asscociates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String appTitle = dataSnapshot.getValue(String.class);
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.d("Token", "Refreshed token: " + refreshedToken);
                // update toolbar title
                //getSupportActionBar().setTitle(appTitle);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Failed", "Failed to read app title value.", databaseError.toException());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String mobilenumber = edtMobileNumber.getText().toString();
                String department = spnDepartment.getSelectedItem().toString();
                String salary = edtSalary.getText().toString().trim();


                if (name.isEmpty()) {
                    edtName.setError("Please enter a name");
                    edtName.requestFocus();
                    return;
                }
                if (TextUtils.isDigitsOnly(name)) {
                    edtName.setError("Please enter a name in alphabets");
                    edtName.requestFocus();
                    return;
                }
                if (mobilenumber.isEmpty()) {
                    edtMobileNumber.setError("Please enter a mobile number");
                    edtMobileNumber.requestFocus();
                    return;
                }
                if (salary.isEmpty() || Integer.parseInt(salary) <= 0) {
                    edtSalary.setError("Please enter salary");
                    edtSalary.requestFocus();
                    return;
                }

                // Check for already existed userId
                AddAssociate(name, mobilenumber, department, salary);


            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtName.setText("");
                edtMobileNumber.setText("");
                edtSalary.setText("");
            }
        });

        return view;
    }


    public void AddAssociate(String name, String mobilenumber, String department, String salary) {
        //Toast.makeText(getActivity(), "associate as   : " + name + "," + mobilenumber + "," + department + "," + salary, Toast.LENGTH_LONG).show();

        Associate associate = new Associate(name, mobilenumber, department, salary);

        if (TextUtils.isEmpty(associateId)) {
            associateId = mFirebaseDatabase.push().getKey();
        }

        mFirebaseDatabase.child(associateId).setValue(associate);
        Toast.makeText(getActivity(), "associate added successfully ", Toast.LENGTH_LONG).show();
        edtName.setText("");
        edtMobileNumber.setText("");
        edtSalary.setText("");

    }


}
