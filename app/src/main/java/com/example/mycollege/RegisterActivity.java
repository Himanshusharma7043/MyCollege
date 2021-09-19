package com.example.mycollege;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    EditText passwordDOB, studentRollNO, studentEmail, studentPh, studentName;

    Button signup;
    TextView signin;
    Calendar dateSelected = Calendar.getInstance();

    ImageView uploadprofile;
    CircleImageView profileImage;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    String[] departmentArray = {"SELECT DEPARTMENT", "B.C.A", "B.B.A", "B.COM"};

    String[] yearArray = {"SELECT YEAR", "F.Y", "S.Y", "T.Y"};

    FirebaseUser user;
    String[] sectionArray = {"SELECT SECTION", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Spinner departmentSpin, yearSpin, sectionSpin;

    private String getDepart, getYear, getSection, getRollNo, getName, getEmail, getPh, getDOB;
    private Bitmap bitmap;
    String downloadUrl = "";
    private static final int CAMERA_REQUEST = 188;
    private ProgressDialog pd;
    @Override
    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();
        pd = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        signin=findViewById(R.id.sign_inlink);
        passwordDOB = findViewById(R.id.studentDOB);
        studentRollNO = findViewById(R.id.rollno);
        studentEmail = findViewById(R.id.student_email);
        studentPh = findViewById(R.id.student_ph);
        studentName = findViewById(R.id.student_name);
        signup = findViewById(R.id.signupbtn);
        departmentSpin = findViewById(R.id.studentDepartment);
        yearSpin = findViewById(R.id.studentYear);
        sectionSpin = findViewById(R.id.studentSection);
        uploadprofile = findViewById(R.id.uploadprofile);
        profileImage = findViewById(R.id.student_profile);

        ArrayAdapter departmentAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, departmentArray);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        departmentSpin.setAdapter(departmentAdapter);
        ArrayAdapter yearAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, yearArray);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpin.setAdapter(yearAdapter);
        ArrayAdapter sectionAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sectionArray);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        signin.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
        sectionSpin.setAdapter(sectionAdapter);
        departmentSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getDepart = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getYear = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sectionSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getSection = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        passwordDOB.addTextChangedListener(new TextWatcher() {
            private String current = "";

            String ddmmyyyy = "DDMMYYYY";

            private final Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");
                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;
                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(0, 2));
                        int mon = Integer.parseInt(clean.substring(2, 4));
                        int year = Integer.parseInt(clean.substring(4, 8));
                        mon = mon < 1 ? 1 : Math.min(mon, 12);
                        cal.set(Calendar.MONTH, mon - 1);
                        year = (year < 1900) ? 1970 : Math.min(year, 2100);
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012
                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d", day, mon, year);
                    }
                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));
                    sel = Math.max(sel, 0);
                    current = clean;
                    passwordDOB.setText(current);
                    passwordDOB.setSelection(Math.min(sel, current.length()));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

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
        signup.setOnClickListener(v -> {

            getEmail = studentEmail.getText().toString().trim();
            getName = studentName.getText().toString().trim();
            getPh = studentPh.getText().toString().trim();
            getRollNo = studentRollNO.getText().toString();
            getDOB = passwordDOB.getText().toString();
            Integer validateDOB = null;
            try {
                validateDOB = Integer.parseInt(getDOB.replaceAll("[\\D]", ""));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Log.e("Length", "" + getDOB);
            if (getName.isEmpty()) {
                studentName.setError("Enter name !");
                studentName.requestFocus();
            } else if (getEmail.isEmpty()) {
                studentEmail.setError("Enter Email!");
                studentEmail.requestFocus();
            } else if (getPh.isEmpty()) {
                studentPh.setError("Enter Degrees!");
                studentPh.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {
                studentEmail.setError("Please Provide Proper EMAIL!");
                studentEmail.requestFocus();
            } else if (getDepart.equals("SELECT DEPARTMENT")) {
                TextView errorTextview = (TextView) departmentSpin.getSelectedView();
                errorTextview.setError("Please select one ");
                errorTextview.requestFocus();
                errorTextview.setText("Please select one ");
                errorTextview.setTextColor(Color.RED);
            } else if (getYear.equals("SELECT YEAR")) {
                TextView errorTextview = (TextView) yearSpin.getSelectedView();
                errorTextview.setError("Please select one ");
                errorTextview.requestFocus();
                errorTextview.setText("Please select one ");
                errorTextview.setTextColor(Color.RED);
            } else if (getSection.equals("SELECT SECTION")) {
                TextView errorTextview = (TextView) sectionSpin.getSelectedView();
                errorTextview.setError("Please select one ");
                errorTextview.requestFocus();
                errorTextview.setText("Please select one ");
                errorTextview.setTextColor(Color.RED);
            } else if (!(getPh.length() > 6 && getPh.length() <= 10)) {
                studentPh.setError("Enter valid number");
                studentPh.requestFocus();
            } else if (getDOB.isEmpty() || getDOB.equals("DD/MM/YYYY")) {
                passwordDOB.setError("Enter Your Date Of Birth");
                passwordDOB.requestFocus();
            } else if (!(Objects.requireNonNull(validateDOB).toString().length() >= 7)) {
                passwordDOB.setError("Enter Proper Date Of Birth");
                passwordDOB.requestFocus();
            }
            else if (getRollNo.isEmpty()) {
                studentRollNO.setError("Enter Roll no !");
                studentRollNO.requestFocus();
            } else if (!(getRollNo.length() > 3 && getRollNo.length() <= 6)) {
                studentRollNO.setError("Enter valid Roll number");
                studentRollNO.requestFocus();
            } else if (bitmap == null) {
                Toast.makeText(RegisterActivity.this, "SELECT IMAGE IF YOU WANT", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("SELECT IMAGE ");
                builder.setMessage("Please select image !!");
                builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                uploadimage();
            }
        });

        uploadprofile.setOnClickListener(v -> {

            Intent pickimg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(pickimg, CAMERA_REQUEST);
        });

    }
    private void uploadimage() {

        pd.setMessage("Uploading......");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child("student_profiles").child(finalimg + "jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<UploadTask.TaskSnapshot> task) {

                if (task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    downloadUrl = String.valueOf(uri);
                                    uploaddata();
                                }
                            });
                        }
                    });
                } else {
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this, "Something Wrong !Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bitmap = null;
    }
    @SuppressLint("SimpleDateFormat")
    private void uploaddata() {

        mAuth.createUserWithEmailAndPassword(getEmail, getDOB).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Calendar calfordate = Calendar.getInstance();
                    SimpleDateFormat currentdate = new SimpleDateFormat("dd:MM:yy");
                    String date = currentdate.format(calfordate.getTime());
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("profile", downloadUrl);
                    data.put("name", getName);
                    data.put("email", getEmail);
                    data.put("phone", getPh);
                    data.put("rollno", getRollNo);
                    data.put("dateofbirth", getDOB);
                    data.put("department", getDepart);
                    data.put("year", getYear);
                    data.put("section", getSection);
                    data.put("key", Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
                    data.put("uploaded_Date", date);
                    FirebaseDatabase.getInstance().getReference("student_details").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(data).addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    pd.dismiss();
                                    String onetimepassword=passwordDOB.getText().toString();
                                    AlertDialog.Builder check = new AlertDialog.Builder(RegisterActivity.this);
                                    check.setTitle("User Registered ");
                                    check.setMessage("Your one time password is your Date of Birth :"+onetimepassword);
                                    check.setPositiveButton("OK", (dialog, which) -> {
                                        Intent toback = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(toback);
                                    });
                                    AlertDialog alert = check.create();
                                    alert.show();
                                    studentRollNO.setText("");
                                    studentName.setText("");
                                    studentEmail.setText("");
                                    studentPh.setText("");
                                    profileImage.setImageResource(R.drawable.profile);
                                    passwordDOB.setText("");
                                    departmentSpin.setSelection(0);
                                    yearSpin.setSelection(0);
                                    sectionSpin.setSelection(0);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Not Register !!!", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setTitle("Not Register !!!");
                                    builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "FAILED !!", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setTitle("May be your account already register here !!!");
                    builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            profileImage.setImageBitmap(bitmap);
        }
    }
    @SuppressLint("SimpleDateFormat")
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent toaddstaff = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(toaddstaff);
            return true;
        }
        return false;
    }

}