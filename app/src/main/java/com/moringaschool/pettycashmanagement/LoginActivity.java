package com.moringaschool.pettycashmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.registerTextView) TextView mRegisterTextView;
    @BindView(R.id.forgotPasswordText) TextView mForgotPasswordText;
    @BindView(R.id.emailEditText) TextView mEmailEditText;
    @BindView(R.id.passwordEditText) TextView mPasswordEditText;
    @BindView(R.id.logInButton)
    Button mLogInButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mRegisterTextView.setOnClickListener(this);
        mForgotPasswordText.setOnClickListener(this);
        mLogInButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
//                Intent intent = new Intent(LoginActivity.this, DisplayRequests.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
                finish();
            }
        };
    }
    @Override
    public void onClick(View view) {
        if (view == mRegisterTextView) {
//            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
//            startActivity(intent);
            finish();
        }
        if (view == mLogInButton) {
            login();
        }
        if (view == mForgotPasswordText) {
//            Intent intent = new Intent(LoginActivity.this, Resetpassword.class);
//            startActivity(intent);
            finish();
        }
    }
    private void login() {
        String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        if (email.equals("")) {
            mEmailEditText.setError("Enter your email");
            return;
        }
        if (password.equals("")) {
            mPasswordEditText.setError("Blank Password");
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d("login method", "sign in success" + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        Log.w("login method",task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}