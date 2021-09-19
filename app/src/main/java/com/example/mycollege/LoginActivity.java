package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycollege.ui.faculty.TeacherAdapater;
import com.example.mycollege.ui.faculty.TeacherData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;

    TextView signup, forget;

    EditText passwordDOB, loginemail;

    private FirebaseAuth mAuth;
    private Boolean checkmail=false;

    Calendar dateSelected = Calendar.getInstance();

    private ProgressDialog pd;

    @Override
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();
        pd = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        loginbtn = findViewById(R.id.loginbtn);
        passwordDOB = findViewById(R.id.loginstudentDOB);
        loginemail = findViewById(R.id.login_email);
        signup = findViewById(R.id.signuplink);
        forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toreset = new Intent(LoginActivity.this, Resetpassword.class);
                startActivity(toreset);
            }
        });
        signup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        loginemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    checkmail=false;
                    Log.e(" checkmail", "afterTextChanged : "+checkmail);
                    Log.e("Over", "Done");
                    isstudent(s.toString());
                }
            }
        });
        passwordDOB.setOnTouchListener((v, event) -> {
            final int DRAWABLE_LEFT = 0;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getX() <= (passwordDOB.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
                    setDateTimeField();
                    return true;
                }
            }
            return false;
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginemail.getText().toString().trim();
                String pwd = passwordDOB.getText().toString().trim();
                Log.e("checkemail", "" + checkmail);
                if (email.isEmpty()) {
                    loginemail.setError("ENTER EMAIL");
                    loginemail.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    loginemail.setError("ENTER VAILD EMAIL");
                    loginemail.requestFocus();
                } else if (!checkmail) {
                    loginemail.setError("Enter student email address");
                    loginemail.requestFocus();
                    checkmail=false;
                } else if (pwd.isEmpty()) {
                    passwordDOB.setError("Enter password");
                    passwordDOB.requestFocus();
                } else if (pwd.length() <= 7) {
                    passwordDOB.setError("Enter password length should be greater then 7 ");
                    passwordDOB.requestFocus();
                } else {
                    checkmail=false;
                    pd.setMessage("Login......");
                    pd.show();
                    mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "WELCOME", Toast.LENGTH_LONG).show();
                                Intent n = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(n);
                            } else {
                                Toast.makeText(LoginActivity.this, "PLEASE REGISTER AND TRY AGAIN !!!", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("PLEASE REGISTER AND TRY AGAIN !!!");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();
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

    public void isstudent(String check) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("student_details");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        StudentData data = ds.getValue(StudentData.class);
                        if (data != null) {
                            if (data.getEmail().equals(check)) {
                                checkmail = true;
                                Log.e("if checkmail", ""+checkmail);
                            }else{
                                Log.e("else email", "false");
                            }
                        } else {
                            Log.e("else email", "false");
                        }
                    }
                } else {
                    Log.e("snapshot", " not exists()");
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public boolean istudent(Boolean check) {

        return check;
    }

    private void setDateTimeField() {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar newCalendar = dateSelected;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year, monthOfYear, dayOfMonth) -> {
            dateSelected.set(year, monthOfYear, dayOfMonth, 0, 0);
            passwordDOB.setText(dateFormatter.format(dateSelected.getTime()));
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return false;
    }

}