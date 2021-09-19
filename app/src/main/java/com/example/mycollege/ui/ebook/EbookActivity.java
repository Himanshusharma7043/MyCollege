package com.example.mycollege.ui.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mycollege.R;
import com.example.mycollege.StudentData;
import com.example.mycollege.ui.notice.NoticeData;
import com.example.mycollege.ui.notice.myNoticedapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EbookActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private DatabaseReference reference;

    private EbookAdapter adapter;

    private List<EbookData> data;

    public String getyear = "All", getdepart = "All",userid;

    private FirebaseUser user;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("E-Book");
        recyclerView = findViewById(R.id.ebook_rv);
        progressBar = findViewById(R.id.ebook_progressbar);
        progressBar.setVisibility(View.VISIBLE);
        data = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(EbookActivity.this);
        recyclerView.setLayoutManager(layoutManager);
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
                Toast.makeText(getApplicationContext(), "SOMETHING WRONG HAPPEN !!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setfilter(String loadDepart, String loadYear) {
        reference = FirebaseDatabase.getInstance().getReference("pdf");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    EbookData ebookData = ds.getValue(EbookData.class);
                    if (ebookData.getTodepart().equals(loadDepart)&&ebookData.getToyear().equals(loadYear)){
                        data.add(ebookData);
                    }
                    if (ebookData.getTodepart().equals(loadDepart)&&ebookData.getToyear().equals("All")){
                        data.add(ebookData);
                    }
                    if (ebookData.getTodepart().equals("All")&&ebookData.getToyear().equals("All")){
                        data.add(ebookData);
                    }

                }
                adapter = new EbookAdapter(getApplicationContext(), data);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}