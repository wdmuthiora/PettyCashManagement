package com.moringaschool.pettycashmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.buttonGoToLogin) Button mGoToLogin;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.buttonRegister) Button mGoToRegister;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.textTerms) TextView mTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mGoToLogin.setOnClickListener(this);
        mGoToRegister.setOnClickListener(this);
        mTerms.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mGoToLogin){
            Toast.makeText(WelcomeActivity.this, "mGoToLogin", Toast.LENGTH_SHORT).show();
        }
        if(view == mGoToRegister){
            Toast.makeText(WelcomeActivity.this, "mGoToRegister", Toast.LENGTH_SHORT).show();
        }
        if(view == mTerms){
            Toast.makeText(WelcomeActivity.this, "mTerms", Toast.LENGTH_SHORT).show();
        }
    }
}
