package com.moringaschool.pettycashmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.pettycashmanagement.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.requestSubmitButton)
    Button submit_button;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.employeeName)
    EditText employeeName;
    @BindView(R.id.employeeId)
    EditText employeeId;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id.purpose)
    EditText purpose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        submit_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
       if(v==submit_button) {
          Intent intent= new Intent(AddActivity.this, DisplayRequestsActivity.class);
          startActivity(intent);
          finish();
       }

    }
}