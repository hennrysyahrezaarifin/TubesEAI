package com.example.moviego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {
private Button btn_forgot;
private EditText editTextForgot;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        btn_forgot = findViewById(R.id.button_forgot);
        editTextForgot =findViewById(R.id.editTextForgot);
        mAuth = FirebaseAuth.getInstance();

        btn_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = editTextForgot.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(ForgotPass.this,"Please write your email address first",Toast.LENGTH_LONG).show();
                }else{
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPass.this, "Please check your email account", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPass.this,LoginActivity.class));
                            }else{
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPass.this, "Error Occured: "+message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
