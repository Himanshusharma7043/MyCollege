package com.example.mycollege.ui.ebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycollege.R;
import com.example.mycollege.ui.gallery.mygalleryadapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookHolder> {
    private Context context;
    private List<EbookData> ebookData;

    public EbookAdapter(Context context, List<EbookData> ebookData) {
        this.context = context;
        this.ebookData = ebookData;
    }

    @NonNull
    @NotNull
    @Override
    public EbookHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ebook_item, parent, false);
        return new EbookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EbookAdapter.EbookHolder holder, int position) {
        EbookData data=ebookData.get(position);
        holder.ebook_name.setText(data.getTitle());
        Log.e("Title",""+data.getTitle());
        holder.ebook_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
                Intent topdfview=new Intent(context,PdfviewerActivity.class);
                topdfview.putExtra("pdfurl",data.getPdfurl());
                context.startActivity(topdfview);
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(data.getPdfurl()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ebookData.size();
    }

    public class EbookHolder extends RecyclerView.ViewHolder {
        private TextView ebook_name;
        private ImageView download;

        public EbookHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ebook_name = itemView.findViewById(R.id.ebook_name);
            download = itemView.findViewById(R.id.ebook_download);
        }
    }
}
