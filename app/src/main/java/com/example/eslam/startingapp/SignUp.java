package com.example.eslam.startingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText email;
    private EditText password;
    private EditText password2;
    private Button button;
    private String code;
    private EditText conCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth=FirebaseAuth.getInstance();
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.pass);
        password2=(EditText)findViewById(R.id.pass2);
        button=(Button) findViewById(R.id.btn);
        conCode = (EditText) findViewById(R.id.code);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(SignUp.this, WakeUpActivity.class));
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        code = UUID.randomUUID().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    public void sendCode() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, email.getText());
        i.putExtra(Intent.EXTRA_SUBJECT, "confirmation code ");
        i.putExtra(Intent.EXTRA_TEXT, code);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(SignUp.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
    private void signUp() {
        String mail=email.getText().toString();

        String pas=password.getText().toString();
        String con=password2.getText().toString();
        if(!mail.equals(null)&&!pas.equals(null)&&!con.equals(null)){
            if(pas.equals(con)){
                if(pas.length()>=6) {
                    firebaseAuth.createUserWithEmailAndPassword(mail, pas)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    Toast.makeText(SignUp.this, "now log in", Toast.LENGTH_LONG).show();

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "re inter the email and password please!", Toast.LENGTH_LONG).show();
                                    }

                                    // ...
                                }
                            });
                }else{
                    Toast.makeText(SignUp.this,"password must be at least 6 chracters !",Toast.LENGTH_LONG).show();

                }
            }else{
                Toast.makeText(SignUp.this,"re inter the password please!",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(SignUp.this,"fill the mail and password field !",Toast.LENGTH_LONG).show();

        }
    }
}
