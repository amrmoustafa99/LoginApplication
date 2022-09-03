package com.example.loginapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.loginapplication.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
private static final String TAG="CheckLogin";
    ActivityMainBinding binding;
FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    public void loginpress(View view) {
         String email=binding.emailEtId.getText().toString();
          if(email.isEmpty()){
              Toast.makeText(this, "Required Email", Toast.LENGTH_SHORT).show();
              return;
          }

          String password=binding.passwordEtId.getText().toString();
        if(password.isEmpty()){
            Toast.makeText(this, "Required password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                             Log.i(TAG,"onComplete"+task.getResult().getUser().getUid());

                             startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }
                        else {
                            String Errormess = task.getException().getLocalizedMessage();

                            Toast.makeText(LoginActivity.this,Errormess , Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void registerpress(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

    }
}