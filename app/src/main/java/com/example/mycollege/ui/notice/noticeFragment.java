package com.example.mycollege.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mycollege.R;
import com.example.mycollege.StudentData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class noticeFragment extends Fragment {

    RecyclerView user_list_rv1;

    myNoticedapter adapter;
    private FirebaseUser user;
    private ProgressBar progressBar;

    private DatabaseReference reference, reference1;

    public List<NoticeData> title;
    public String getyear="All", getdepart="All";
    public List<String> key;

    public List<Integer> time;
    private String userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        user_list_rv1 = view.findViewById(R.id.notice_list_rv1);
        progressBar = view.findViewById(R.id.progressBar);

       // setfilter(getdepart,getyear);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("student_details");
        userid = user.getUid();
        reference.keepSynced(true);
        reference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                StudentData studentData = snapshot.getValue(StudentData.class);
                if (studentData != null) {
                    setfilter(studentData.getDepartment(),studentData.getYear());
                    Log.e("GET",""+studentData.getDepartment()+"\n"+studentData.getYear());
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getContext(), "SOMETHING WRONG HAPPEN !!", Toast.LENGTH_LONG).show();
            }
        });
        user_list_rv1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        user_list_rv1.setLayoutManager(layoutManager);
        title = new ArrayList<>();
        time = new ArrayList<>();
        key = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }

    private void setfilter(String loadDepart, String loadYear) {

        reference1 = FirebaseDatabase.getInstance().getReference("Notice");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                title.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    NoticeData noticedata = snapshot1.getValue(NoticeData.class);
                    assert noticedata != null;
                    if (noticedata.getTodepart().equals(loadDepart) && noticedata.getToyear().equals(loadYear)) {
                        title.add(noticedata);
                    }
                    if (noticedata.getTodepart().equals(loadDepart) && noticedata.getToyear().equals("All")) {
                        title.add(noticedata);
                    }
                    if (noticedata.getTodepart().equals("All") && noticedata.getToyear().equals("All")) {
                        title.add(noticedata);
                    }
                }
                adapter = new myNoticedapter(title, getActivity());
                user_list_rv1.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}