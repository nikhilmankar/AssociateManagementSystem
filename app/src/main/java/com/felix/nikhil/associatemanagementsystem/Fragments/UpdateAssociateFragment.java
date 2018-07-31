package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateAssociateFragment extends Fragment {
    public EditText edtName, edtMobileNumber, edtSalary;
    public Spinner spnDepartment;
    public Button btnSearch, btnClear, btnUpdate;
    public String userId;
    public DatabaseReference mFirebaseDatabase;
    public FirebaseDatabase mFirebaseInstance;

    public String associateId;

    public UpdateAssociateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_associate, container, false);
        Toast.makeText(getActivity(), "fragment_update_associates", Toast.LENGTH_LONG).show();

        edtName = view.findViewById(R.id.edt_Name);
        edtMobileNumber = view.findViewById(R.id.edt_MobileNumber);
        edtSalary = view.findViewById(R.id.edt_Salary);
        spnDepartment = view.findViewById(R.id.spn_Department);
        btnSearch = view.findViewById(R.id.btn_Search);
        btnUpdate = view.findViewById(R.id.btn_Update);
        btnClear = view.findViewById(R.id.btn_Clear);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = edtName.getText().toString().trim();

                if (TextUtils.isEmpty(Name)) {
                    edtName.setError("Please enter a name");
                    edtName.requestFocus();
                    return;
                }
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference reference = firebaseDatabase.getReference("Associates");
                //reference.child("Associates").orderByChild("name").equalTo(Name);
                //Query query = firebaseDatabase.getReference("Associates").child("Associates").orderByChild("name").equalTo(Name);
                /*Query query = reference.orderByChild("name").equalTo(Name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Associate associate= dataSnapshot.getValue(String.class).getValue(Associate.class);
                        if (associate==null){
                            Log.e("Not found","Data not found");
                        }
                        Log.e("record found", "User data is found!" + associate.getName() + ", " + associate.getMobilenumber() + ", " + associate.getSalary());
                        String mobile=associate.mobilenumber;
                        String salary=associate.salary;
                        edtMobileNumber.setText(mobile);
                        edtSalary.setText(salary);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Read failed", "Failed to read user", databaseError.toException());
                    }
                });*/

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.child("name").getValue(String.class).equals(Name)){
                                Associate associate = snapshot.getValue(Associate.class);
                                if (associate==null){
                                    Log.e("Not found","Data not found");
                                }
                                //Log.e("record found", "User data is found!" + associate.getName() + ", " + associate.getMobilenumber() + ", " + associate.getSalary());
                                String mobile=associate.mobilenumber;
                                String designation=associate.department;
                                String salary=associate.salary;
                                ArrayAdapter myAdap = (ArrayAdapter) spnDepartment.getAdapter(); //cast to an ArrayAdapter

                                int spinnerPosition = myAdap.getPosition(designation);

                                //set the default according to value
                                spnDepartment.setSelection(spinnerPosition);
                                edtMobileNumber.setText(mobile);
                                edtSalary.setText(salary);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Read failed", "Failed to read user", databaseError.toException());
                    }
                });
            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = edtName.getText().toString();
                final String mobilenumber = edtMobileNumber.getText().toString();
                final String department = spnDepartment.getSelectedItem().toString();
                final String salary = edtSalary.getText().toString().trim();

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
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                final DatabaseReference reference = firebaseDatabase.getReference("Associates");
                //Query updateQuery = reference.child("name").orderByKey().equalTo(name);
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.child("name").getValue(String.class).equals(name)){
                                Associate associate = snapshot.getValue(Associate.class);
                                String key = snapshot.getKey();
                                if (associate==null){
                                    Log.e("Not found","Data not found");
                                    return;
                                }
                                Log.e("record found", "User data is found!" + associate.getName() + ", " + associate.getMobilenumber() + ", " + associate.getSalary());
                                associate.setDepartment(department);
                                associate.setMobilenumber(mobilenumber);
                                associate.setSalary(salary);
                                reference.child(key).setValue(associate);
                                //mFirebaseDatabase.child(associateId).setValue(associate);
                                Toast.makeText(getActivity(), "associate updated successfully ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Read failed", "Failed to read user", databaseError.toException());
                    }
                });
                /*
                if (associate == null) {
                    Log.e("Data Null", "User data is null!");
                    return;
                }

                Log.e("Data Changed", "User data is changed!" + user.name + ", " + user.email);


                    }*/
                 /*
                to update particular record
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference reference = firebaseDatabase.getReference();
    Query query = reference.child("Associates").orderByChild("name").equalTo(Name);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            DataSnapshot nodeDataSnapshot = dataSnapshot.getChildren().iterator().next();
            String key = nodeDataSnapshot.getKey(); // this key is `K1NRz9l5PU_0CFDtgXz`
            String path = "/" + dataSnapshot.getKey() + "/" + key;
            HashMap<String, Object> result = new HashMap<>();
            result.put("Status", "COMPLETED");//new values
            reference.child(path).updateChildren(result);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Logger.error(TAG, ">>> Error:" + "find onCancelled:" + databaseError);

        }
    });*/

                edtName.setText("");
                edtMobileNumber.setText("");
                edtSalary.setText("");
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
}
