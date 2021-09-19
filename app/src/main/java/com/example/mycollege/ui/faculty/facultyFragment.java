package com.example.mycollege.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.mycollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class facultyFragment extends Fragment {

    DatabaseReference reference;

    RecyclerView bca_recyclerView, bba_recyclerView, bcom_recyclerView;

    private TeacherAdapater adapter;

    private ProgressBar progressBar;

    private LinearLayout bca_ll, bba_ll, bcom_ll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        bca_recyclerView = view.findViewById(R.id.bca_rv);
        bba_recyclerView = view.findViewById(R.id.bba_rv);
        progressBar = view.findViewById(R.id.load_progressBar);
        bcom_recyclerView = view.findViewById(R.id.bcom_rv);
        bca_ll = view.findViewById(R.id.bca_no_data_found);
        bba_ll = view.findViewById(R.id.bba_no_data);
        bcom_ll = view.findViewById(R.id.bcom_no_data);
        reference = FirebaseDatabase.getInstance().getReference("teacher_details");
        showbca();
        showbba();
        showbcom();
        return view;
    }

    private void showbca() {

        List<TeacherData> bcadata = new ArrayList<>();
        Query mQuery = reference.orderByChild("faculty_of").equalTo("B.C.A");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                bcadata.clear();
                if (!snapshot.exists()) {
                    bca_ll.setVisibility(View.VISIBLE);
                } else {
                    bca_recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        TeacherData teacherData = ds.getValue(TeacherData.class);
                        bcadata.add(teacherData);

                    }
                    adapter = new TeacherAdapater(bcadata, getActivity());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    bca_recyclerView.setLayoutManager(layoutManager);
                    bca_recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        bcadata.clear();

    }

    private void showbba() {

        List<TeacherData> bbadata = new ArrayList<>();
        Query mQuery = reference.orderByChild("faculty_of").equalTo("B.B.A");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                bbadata.clear();
                if (!snapshot.exists()) {
                    bba_ll.setVisibility(View.VISIBLE);
                } else {
                    bba_recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        TeacherData teacherData = ds.getValue(TeacherData.class);
                        bbadata.add(teacherData);

                    }
                    adapter = new TeacherAdapater(bbadata, getActivity());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    bba_recyclerView.setLayoutManager(layoutManager);
                    bba_recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        bbadata.clear();

    }

    private void showbcom() {

        List<TeacherData> bcomdata = new ArrayList<>();
        Query mQuery = reference.orderByChild("faculty_of").equalTo("B,.COM");
        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                bcomdata.clear();
                if (!snapshot.exists()) {
                    bcom_ll.setVisibility(View.VISIBLE);
                } else {
                    bcom_recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        TeacherData teacherData = ds.getValue(TeacherData.class);
                        bcomdata.add(teacherData);

                    }
                    adapter = new TeacherAdapater(bcomdata, getActivity());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    bcom_recyclerView.setLayoutManager(layoutManager);
                    bcom_recyclerView.setAdapter(adapter);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        bcomdata.clear();
    }

}