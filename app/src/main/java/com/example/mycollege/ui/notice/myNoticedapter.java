package com.example.mycollege.ui.notice;

import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycollege.Image_view_zoom;
import com.example.mycollege.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.skyhope.showmoretextview.ShowMoreTextView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class myNoticedapter extends RecyclerView.Adapter<myNoticedapter.ViewHolder> {

    private List<NoticeData> title;

    private List<String> date;

    private Context context;

    private List<String> time;
    private List<String> user_profile_image;
    private List<String> key;

    public DatabaseReference reference1;

    public myNoticedapter(List<NoticeData> title, Context context) {

        this.title = title;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.notice_feeds, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myNoticedapter.ViewHolder holder, int position) {
        NoticeData show_title = title.get(position);
        user_profile_image = new ArrayList<>();
        holder.notice_title.setText(show_title.getTitle());
        holder.notice_title.setShowingLine(2);
        holder.notice_title.addShowMoreText("Show more");
        holder.notice_title.addShowLessText("Show Less");
        holder.notice_title.setShowMoreColor(Color.BLUE);
        holder.notice_sender.setText(show_title.getUploader_name());
        holder.notice_date.setText(show_title.getDate());
        holder.notice_time.setText(show_title.getTime());
        reference1 = FirebaseDatabase.getInstance().getReference("Notice").child(show_title.getKey());
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot == null) {
                    Log.e("DATA", "NULL");
                } else {
                    NoticeData data = snapshot.getValue(NoticeData.class);
                    if (data != null) {
                        String link = data.getImage();
                        if (link == null) {
                            holder.notice_image.setVisibility(View.INVISIBLE);
                        } else {
                            Picasso.get().load(link).into(holder.notice_image);
                            user_profile_image.add(link);
                        }
                    } else {
                        Log.e("inner DATA", "NULL");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        holder.notice_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent tofull=new Intent(context,Image_view_zoom.class);
               tofull.putExtra("image",""+show_title.getImage());
               tofull.putExtra("context",""+context);
               context.startActivity(tofull);
            }
        });

    }


    @Override
    public int getItemCount() {

        return title.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  notice_date, notice_time,notice_sender;
        public ShowMoreTextView notice_title;
        public ImageButton delete;

        public ImageView notice_image;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            notice_title = (ShowMoreTextView) itemView.findViewById(R.id.notice_title);
            notice_date = (TextView) itemView.findViewById(R.id.notice_date);
            notice_sender = (TextView) itemView.findViewById(R.id.notice_sender);
            notice_time = (TextView) itemView.findViewById(R.id.notice_time);
            notice_image = (ImageView) itemView.findViewById(R.id.notice_image);
        }
    }
}
