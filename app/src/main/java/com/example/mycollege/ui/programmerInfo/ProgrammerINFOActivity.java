package com.example.mycollege.ui.programmerInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mycollege.R;

public class ProgrammerINFOActivity extends AppCompatActivity {
    TextView email, location, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programmer_infoactivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer");
        email = findViewById(R.id.programmer_email);
        location = findViewById(R.id.programmer_location);
        phone = findViewById(R.id.programmer_phone);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"himanshusharma704@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Develop a project");
                email.putExtra(Intent.EXTRA_TEXT, "Hello, Himanshu sharma");
                //need this to prompts email client only
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("geo:0,0?q=Model Town Regency saroli Surat Gujarat");
                Intent tomap = new Intent(Intent.ACTION_VIEW, uri);
                tomap.setPackage("com.google.android.apps.maps");
                startActivity(tomap);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = "7043437581";
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(Uri.parse("tel:" + number));
                startActivity(dial);
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