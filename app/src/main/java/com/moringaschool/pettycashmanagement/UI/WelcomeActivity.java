package com.moringaschool.pettycashmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.pettycashmanagement.R;
import com.moringaschool.pettycashmanagement.UI.DisplayRequestsActivity;

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
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        if(view == mGoToRegister){
            Intent intent = new Intent(WelcomeActivity.this, SignupActivity.class);
            startActivity(intent);
        }
        if(view == mTerms){
        }
    }


}
