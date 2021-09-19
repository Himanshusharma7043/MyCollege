package com.example.mycollege.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mycollege.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class aboutFragment extends Fragment {

private ViewPager viewPager;
private Branchadapter branchadapter;
private List<BranchModel> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);
        list=new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_book,"Bachelor of Commerce","BCom or Bachelor of Commerce is a three-year undergraduate course imparted in regular as well as distance education mode. BCom is the second most popular undergraduate course in the Commerce branch. Under BCom, there are three most popular courses, namely BCom or BCom-General, BCom (Honours) and BCom LLB."));
        list.add(new BranchModel(R.drawable.ic_computer,"Bachelors in Computer Application","The full form of BCA is Bachelors in Computer Application. BCA is a three year undergraduate degree programme for candidates wishing to delve into the world of Computer languages. ... A BCA degree is considered to be at par with a BTech/BE degree in Computer Science or Information Technology."));
        list.add(new BranchModel(R.drawable.ic_bba," Bachelor of Business Administration"," BBA is a three years degree course. Its an undergraduate course that helps develop entrepreneurship skills of the candidates. ... BBA develop entrepreneurship. The undergraduate course in business management is open for all three steam - science, arts and commerce - candidates."));
        branchadapter = new Branchadapter(getActivity(),list);
        viewPager =view.findViewById(R.id.viewpager);
        viewPager.setAdapter(branchadapter);
        ImageView imageView=view.findViewById(R.id.college_image);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/my-collage-app-3ed3e.appspot.com/o/college.jpg?alt=media&token=70a3b4bb-397d-440e-aabf-4a905e02dca3").into(imageView);
        return view;
    }

}