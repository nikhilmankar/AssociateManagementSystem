package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.felix.nikhil.associatemanagementsystem.Associate;
import com.felix.nikhil.associatemanagementsystem.AssociateListAdapter;
import com.felix.nikhil.associatemanagementsystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAssociatesFragment extends Fragment {


    List<Associate> displayArray;

    ArrayAdapter<Associate> valuesAdapter;
    RecyclerView recyclerView;
    ProgressDialog pd;
    private DatabaseReference databaseReference;

    //private FirebaseRecyclerAdapter<Associate, AssociateListViewHolder> firebaseRecyclerAdapter;

    public ViewAssociatesFragment() {
        // Required empty public constructor

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_associates, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_AssociateList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait ..");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Associates");
        displayArray = new ArrayList<>();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Associate associate = snapshot.getValue(Associate.class);

                    displayArray.add(associate);

                    Toast.makeText(getActivity(), "Name " + associate.getName() + associate.getMobilenumber() + associate.getDepartment() + associate.getSalary(), Toast.LENGTH_SHORT).show();

                }

                AssociateListAdapter adapter = new AssociateListAdapter(displayArray);

                recyclerView.setAdapter(adapter);

                /*valuesAdapter = new ArrayAdapter(getActivity(), R.layout.item_associate_list, displayArray);
                listView.setAdapter(valuesAdapter);*/

                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
            }
        });


        return view;
    }


}