package com.felix.nikhil.associatemanagementsystem.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felix.nikhil.associatemanagementsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAssociatesFragment extends Fragment {


    public ViewAssociatesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_view_associates, container, false);
        Toast.makeText(getActivity(), "fragment_view_associates", Toast.LENGTH_LONG).show();
        return view;
    }

}
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="10dp"
    tools:context=".Fragments.AddAssociateFragment">

    <!-- TODO: Update blank fragment layout -->
    <TextView
        android:id="@+id/lbl_Name"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:padding="10dp"
        android:text="Associate Name"
        android:textAllCaps="true"
        android:textSize="22sp"
        android:textStyle="bold" />

    <EditText
        android:id="@+id/edt_Name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/lbl_Name"

        android:fontFamily="serif-monospace"
        android:hint="Associate name"
        android:imeOptions="actionNext"
        android:padding="10dp"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/lbl_Number"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_below="@id/edt_Name"
        android:padding="10dp"
        android:text="Mobile Number"
        android:textAllCaps="true"
        android:textSize="22sp"
        android:textStyle="bold" />

    <EditText
        android:id="@+id/edt_Number"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/lbl_Number"
        android:digits="0123456789"
        android:fontFamily="serif-monospace"
        android:hint="Associate Mobile Number"
        android:imeOptions="actionNext"
        android:inputType="number"
        android:padding="10dp"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/lbl_Department"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_below="@id/edt_Name"
        android:padding="10dp"
        android:text="Department"
        android:textAllCaps="true"
        android:textSize="22sp"
        android:textStyle="bold" />

    <Spinner
        android:id="@+id/spn_Department"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:entries="@array/departments"
        android:imeOptions="actionNext"
        android:nextFocusDown="@+id/edt_Salary"
        android:padding="10dp" />


    <TextView
        android:id="@+id/lbl_Salary"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_below="@id/spn_Department"
        android:padding="10dp"
        android:text="Salary"
        android:textAllCaps="true"
        android:textSize="22sp"
        android:textStyle="bold" />

    <EditText
        android:id="@+id/edt_Salary"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_below="@+id/lbl_Address"
        android:digits="0123456789"
        android:fontFamily="serif-monospace"
        android:hint="Associate Salary"
        android:inputType="number"
        android:padding="10dp"
        android:textSize="18sp" />

    <Button
        android:id="@+id/btn_Save"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:text="Save" />

    <Button
        android:id="@+id/btn_Clear"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Clear" />


</LinearLayout>*/