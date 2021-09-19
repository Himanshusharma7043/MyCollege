package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Resetpassword extends AppCompatActivity {
    EditText email;
    Button send;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        Objects.requireNonNull(getSupportActionBar()).hide();
        back = findViewById(R.id.reset_back);
        email=findViewById(R.id.reset_password_email);
        send=findViewById(R.id.sendmail_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomain = new Intent(Resetpassword.this, LoginActivity.class);
                startActivity(tomain);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_email = email.getText().toString();
                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (input_email.isEmpty()) {
                    email.setError("ENTER EMAIL");
                    email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(input_email).matches()) {
                    email.setError("ENTER VAILD EMAIL");
                    email.requestFocus();
                } else {
                    auth.fetchSignInMethodsForEmail(input_email)
                            .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<SignInMethodQueryResult> task) {
                                    boolean isNewUser = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getSignInMethods()).isEmpty();
                                    if (isNewUser) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(Resetpassword.this);
                                        builder.setMessage("Your email not in Database !!!");
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();
                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }else{
                                        auth.sendPasswordResetEmail(input_email)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(Resetpassword.this);
                                                            builder.setMessage("Check your mail box for reset your passwors !!!");
                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    dialog.cancel();
                                                                    Intent tomain = new Intent(Resetpassword.this, LoginActivity.class);
                                                                    startActivity(tomain);
                                                                }
                                                            });
                                                            AlertDialog alert = builder.create();
                                                            alert.show();
                                                        }
                                                    }
                                                });
                                    }
                                }
                            });

                }
            }
        });
    }

}