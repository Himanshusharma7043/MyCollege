package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import com.example.mycollege.ui.ebook.EbookActivity;
import com.example.mycollege.ui.programmerInfo.ProgrammerINFOActivity;
import com.github.chrisbanes.photoview.BuildConfig;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private NavController navController;

    ProgressDialog progressDialog;

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle;

    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view_drawer);
        navController = Navigation.findNavController(this, R.id.frame_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.video_lecture:
                String youtube = "https://www.youtube.com/channel/UCi0EhkKEFKPMx74iZY0tTGw";
                Intent toyoutube = new Intent(Intent.ACTION_VIEW);
                toyoutube.setData(Uri.parse(youtube));
                startActivity(toyoutube);
                Toast.makeText(this, "Video Lectures", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ebook:
                startActivity(new Intent(this, EbookActivity.class));
                Toast.makeText(this, "ebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Logout ?");
                builder.setMessage("ARE YOU SURE YOU WANT TO LOGOUT ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent tologin = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(tologin);

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.Websites:
                String url = "https://www.udhnacollege.org/Home.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                Toast.makeText(this, "Websites", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                share.putExtra(Intent.EXTRA_SUBJECT, "Download the College App :");
                share.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/file/d/1XyoKmuI8Hr6eTuFJjqB-_bzTHc1KIwDw/view?usp=sharing");
                startActivity(Intent.createChooser(share, "Share App:"));
                break;
            case R.id.rate_us:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View inflate = inflater.inflate(R.layout.rating_layout, null);
                RatingBar ratingbar = inflate.findViewById(R.id.ratingBar);
                Button submit = inflate.findViewById(R.id.rating_button);
                builder1.setView(inflate);
                AlertDialog dialog = builder1.create();
                dialog.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String rating = String.valueOf(ratingbar.getRating());
                        if (rating.isEmpty()) {
                            Toast.makeText(MainActivity.this, "RATE US ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });
                Toast.makeText(this, "rate_us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.developers:
                Intent to_developer=new Intent(this, ProgrammerINFOActivity.class);
                startActivity(to_developer);
                Toast.makeText(this, "developers", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return false;
    }
}