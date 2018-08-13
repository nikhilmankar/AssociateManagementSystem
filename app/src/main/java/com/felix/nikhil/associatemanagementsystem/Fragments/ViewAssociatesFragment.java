package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v4.app.Fragment;

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
import com.felix.nikhil.associatemanagementsystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAssociatesFragment extends Fragment {


    ArrayList<Associate> displayArray;
    ArrayList<Associate> keysArray;
    ArrayAdapter<Associate> valuesAdapter;
    ListView listView;
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
        listView = view.findViewById(R.id.listView1);
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Please wait ..");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Associates");


        displayArray = new ArrayList<>();
        keysArray = new ArrayList<>();
        valuesAdapter = new ArrayAdapter<Associate>(getActivity(), R.layout.item_associate_list, displayArray);
        listView.setAdapter(valuesAdapter);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Associate associate = snapshot.getValue(Associate.class);

                    displayArray.add(associate);

                    Toast.makeText(getActivity(), "Name "+associate.getName()+associate.getMobilenumber()+associate.getDepartment()+associate.getSalary() ,Toast.LENGTH_SHORT).show();

                }


                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pd.dismiss();
            }
        });


        return view;
    }

    private void updateListView() {
        valuesAdapter.notifyDataSetChanged();
        listView.invalidate();
        Log.d(TAG, "Length: " + displayArray.size());
    }



}
