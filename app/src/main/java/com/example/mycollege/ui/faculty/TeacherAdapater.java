package com.example.mycollege.ui.faculty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycollege.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherAdapater extends RecyclerView.Adapter<TeacherAdapater.TecherViewAdapter> {
    private List<TeacherData> data;

    private Context context;

    public TeacherAdapater(List<TeacherData> data, Context context) {

        this.data = data;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public TecherViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.faculty_item_layout, parent, false);
        return new TecherViewAdapter(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull TeacherAdapater.TecherViewAdapter holder, int position) {

        TeacherData input = data.get(position);
        holder.name.setText(input.getName());
        holder.email.setText(input.getEmail());
        holder.experience.setText(input.getExperience()+" year");
        holder.industry_exp.setText(input.getIndustry_experience()+" year");
        holder.post.setText(input.getPost());
        holder.qualification.setText(input.getDegrees());
        Picasso.get().load(input.getProfile()).into(holder.profile);
    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class TecherViewAdapter extends RecyclerView.ViewHolder {
        private TextView name, post, email, qualification,experience,industry_exp;

        private CircleImageView profile;

        public TecherViewAdapter(@NonNull @NotNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.faculty_name);
            email = itemView.findViewById(R.id.faculty_email);
            post = itemView.findViewById(R.id.faculty_post);
            experience=itemView.findViewById(R.id.faculty_exp);
            industry_exp=itemView.findViewById(R.id.faculty_industry_exp);
            qualification = itemView.findViewById(R.id.faculty_degrees);
            profile = itemView.findViewById(R.id.faculty_profile);

        }

    }

}
