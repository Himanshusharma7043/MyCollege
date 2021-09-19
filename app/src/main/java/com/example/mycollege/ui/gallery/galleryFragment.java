package com.example.mycollege.ui.gallery;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class galleryFragment extends Fragment {
    RecyclerView farewell, republic_day,collegeEvent,holi,independenceDay,teachersDay,ganeshChaturthi,diwali,navratri;
    String[] country = {"SELECT", "Republic Day","Farewell","College Event", "Holi", "Independence Day", "Teachers Day","Ganesh Chaturthi","Diwali","Navratri"};
    mygalleryadapter adapter;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        farewell = view.findViewById(R.id.farewell);
        republic_day = view.findViewById(R.id.republic_day);
        collegeEvent = view.findViewById(R.id.collegeEvents);
        holi = view.findViewById(R.id.holi);
        independenceDay = view.findViewById(R.id.independenceDay);
        teachersDay = view.findViewById(R.id.teachersDay);
        ganeshChaturthi = view.findViewById(R.id.ganeshChaturthi);
        diwali = view.findViewById(R.id.diwali);
        navratri = view.findViewById(R.id.navratri);
        reference = FirebaseDatabase.getInstance().getReference("Gallery");
        loadgalleryrv("Republic Day",republic_day);
        loadgalleryrv("Farewell",farewell);
        loadgalleryrv("College Event",collegeEvent);
        loadgalleryrv("Holi",holi);
        loadgalleryrv("Independence Day",independenceDay);
        loadgalleryrv("Teachers Day",teachersDay);
        loadgalleryrv("Ganesh Chaturthi",ganeshChaturthi);
        loadgalleryrv("Diwali",diwali);
        loadgalleryrv("Navratri",navratri);

        return view;

    }

    private void loadgalleryrv(String load, RecyclerView rv) {
        List<String> image=new ArrayList<>();
        reference.child(load).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                image.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    GalleryData data=ds.getValue(GalleryData.class);
                    image.add(data.getImage());
                }
                adapter =new mygalleryadapter(image,getActivity());
                GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
                rv.setLayoutManager(gridLayoutManager);
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}