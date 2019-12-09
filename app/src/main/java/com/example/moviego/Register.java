package com.example.moviego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText editTextEmail, editTextUser, editTextPass, editTextJob, editTextTahun;
    private Button btn_regis, btn_sign;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextEmail = findViewById(R.id.editTextEmaill);
        editTextUser = findViewById(R.id.editTextUsername);
        editTextPass = findViewById(R.id.editTextPassword);
        editTextJob = findViewById(R.id.editTextJob);
        editTextTahun = findViewById(R.id.editTextTahunLahir);
        mAuth = FirebaseAuth.getInstance();
        btn_regis = findViewById(R.id.button_regis);
        btn_sign = findViewById(R.id.button_sign);

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent(Register.this,LoginActivity.class);
                startActivity(z);
            }
        });
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
            private void registerUser () {
                final String email = editTextEmail.getText().toString().trim();
                final String username = editTextUser.getText().toString().trim();
                final String password = editTextPass.getText().toString().trim();
                final String job = editTextJob.getText().toString().trim();
                final String tahun = editTextTahun.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextEmail.setError("Please enter your email");
                    editTextEmail.requestFocus();
                    return;
                }
                if (username.isEmpty()) {
                    editTextUser.setError("Please enter your Username");
                    editTextUser.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTextPass.setError("Please enter your password");
                    editTextPass.requestFocus();
                    return;
                }
                if (job.isEmpty()) {
                    editTextJob.setError("Please enter your job details");
                    editTextJob.requestFocus();
                    return;
                }
                if (tahun.isEmpty()) {
                    editTextTahun.setError("Please enter your year of birth");
                    editTextTahun.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Your email is invalid");
                    editTextEmail.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    editTextPass.setError("Your password is less than 6 characters");
                    editTextPass.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    User user = new User(
                                            email,
                                            username,
                                            password,
                                            job,
                                            tahun
                                    );
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "Success", Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(Register.this, "Fail", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        }
    }

