package com.example.mycollege.ui.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.mycollege.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Branchadapter extends PagerAdapter {
    private Context context;
    private List<BranchModel> list;

    public Branchadapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item,container,false);
        ImageView branch_image;
        TextView br_title,br_desc;
        branch_image=view.findViewById(R.id.branch_image);
        br_title=view.findViewById(R.id.branch_title);
        br_desc=view.findViewById(R.id.branch_description);
        branch_image.setImageResource(list.get(position).getImg());
        br_title.setText(list.get(position).getTitle());
        br_desc.setText(list.get(position).getDescription());
        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view.equals(object);
    }
}
