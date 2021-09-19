package com.example.mycollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth=FirebaseAuth.getInstance();
        Thread bg = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser == null) {
                        Log.e("Firebase User", "false");
                        Intent it = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(it);
                    } else {
                        Log.e("Firebase User", "True");
                        Intent to_main=new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(to_main);
                    }
                }
            }
        };
        bg.start();
    }
}