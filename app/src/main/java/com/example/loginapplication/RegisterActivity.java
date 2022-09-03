package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginapplication.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG="CheckRegister";
    ActivityRegisterBinding binding;

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_register);
    }

    public void registerpress(View view) {

        String email=binding.emailId.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(this, "Required Email", Toast.LENGTH_SHORT).show();
            return;
        }

        String password=binding.passwordId.getText().toString();
        if(password.isEmpty()){
            Toast.makeText(this, "Required password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Log.i(TAG,"ACOUNT Creared");
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String Errormess = e.getLocalizedMessage();

                        Toast.makeText(RegisterActivity.this,Errormess , Toast.LENGTH_SHORT).show();
                    }
                });

    }
}