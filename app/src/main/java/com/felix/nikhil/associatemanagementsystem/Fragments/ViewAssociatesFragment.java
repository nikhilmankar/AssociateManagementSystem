package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.felix.nikhil.associatemanagementsystem.Associate;
import com.felix.nikhil.associatemanagementsystem.AssociateListAdapter;
import com.felix.nikhil.associatemanagementsystem.AssociateListViewHolder;
import com.felix.nikhil.associatemanagementsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAssociatesFragment extends Fragment {

    private RecyclerView rView;

    private DatabaseReference databaseReference;

    //private FirebaseRecyclerAdapter<Associate, AssociateListViewHolder> firebaseRecyclerAdapter;

    public ViewAssociatesFragment() {
        // Required empty public constructor

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_associates, container, false);
        rView = view.findViewById(R.id.rvAssociate);

        databaseReference = FirebaseDatabase.getInstance().getReference("Associate");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Associate> associateArrayList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Associate associate = dataSnapshot1.getValue(Associate.class);
                    Associate associate1 = new Associate();

                    String name = associate.getName();
                    String mobile = associate.getMobilenumber();
                    String department = associate.getDepartment();
                    String salary = associate.getSalary();
                    associate1.setName(name);
                    associate1.setMobilenumber(mobile);
                    associate1.setDepartment(department);
                    associate1.setSalary(salary);
                    associateArrayList.add(associate1);
                    Log.e("record found", "User data is found!" + name + ", " + mobile + ", " + department + ", " + salary);


                }
                AssociateListAdapter associateListAdapter = new AssociateListAdapter(associateArrayList);
                //rView.LayoutManager layoutmanager = new LinearLayoutManager(getActivity());
                rView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //rView.setLayoutManager(layoutmanager);

                rView.setItemAnimator(new DefaultItemAnimator());
                rView.setAdapter(associateListAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

 /*       ArrayList<Associate> associateArrayList = new ArrayList<>();
        AssociateListAdapter associateListAdapter=new AssociateListAdapter(associateArrayList);
        rView.setLayoutManager(new LinearLayoutManager(this));

        rView.setAdapter(associateListAdapter);*/
        //Toast.makeText(getActivity(), "fragment_view_associates", Toast.LENGTH_LONG).show();
        return view;
    }

}
