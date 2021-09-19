package com.example.mycollege.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycollege.Image_view_zoom;
import com.example.mycollege.R;
import com.example.mycollege.ui.notice.NoticeData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class mygalleryadapter extends RecyclerView.Adapter<mygalleryadapter.ViewHolder> {

    public Context context;
    private List<String> image;


    public DatabaseReference reference1;

    public mygalleryadapter(List<String> image, Context context) {

        this.image = image;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.gallery_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull mygalleryadapter.ViewHolder holder, int position) {

        Picasso.get().load(image.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tofull=new Intent(context,Image_view_zoom.class);
                tofull.putExtra("image",""+image.get(position));
                tofull.putExtra("context",""+context);
                context.startActivity(tofull);
            }
        });
    }


    @Override
    public int getItemCount() {

        return image.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.gallery_image);

        }

    }

}
