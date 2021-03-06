package com.moringaschool.pettycashmanagement.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.pettycashmanagement.Constants.Constants;
import com.moringaschool.pettycashmanagement.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    //Constants.
    public static final String EXTRA_ID = "com.moringaschool.pettycashmanagement.EXTRA_ID";
    public static final String EXTRA_NAME = "com.moringaschool.pettycashmanagement.EXTRA_NAME";
    public static final String EXTRA_EMPLOYEE_ID = "com.moringaschool.pettycashmanagement.EXTRA_EMPLOYEE_ID";
    public static final String EXTRA_AMOUNT = "com.moringaschool.pettycashmanagement.EXTRA_AMOUNT";
    public static final String EXTRA_PRIORITY = "com.moringaschool.pettycashmanagement.EXTRA_PRIORITY";
    public static final String EXTRA_PURPOSE = "com.moringaschool.pettycashmanagement.EXTRA_PURPOSE";
    public static final String EXTRA_REJECTED = "com.moringaschool.pettycashmanagement.EXTRA_REJECTED";
    public static final String EXTRA_APPROVED = "com.moringaschool.pettycashmanagement.EXTRA_APPROVED";
    private static final String EXTRA_PENDING = "com.moringaschool.pettycashmanagement.EXTRA_PENDING";


    @BindView(R.id.requestApproveButton)
    Button approve_button;
    @BindView(R.id.requestRejectButton)
    Button reject_button;
    @BindView(R.id.approve_reject)
    LinearLayout approve_reject;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etEmployeeName)
    EditText etEmployeeName;
    @BindView(R.id.etEmployeeId)
    EditText etEmployeeId;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.etPurpose)
    EditText etPurpose;
    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        approve_reject.setOnClickListener(this);
        reject_button.setOnClickListener(this);

//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) { //Get intent extra values from the clicked Card inside the RecyclerView, to edit.

            setTitle("Action Request");
            etEmployeeId.setText(intent.getStringExtra(EXTRA_EMPLOYEE_ID));
            etEmployeeName.setText(intent.getStringExtra(EXTRA_NAME));
            etAmount.setText(intent.getStringExtra(EXTRA_AMOUNT));
            tvStatus.setText(intent.getStringExtra(EXTRA_PENDING));
            etPurpose.setText(intent.getStringExtra(EXTRA_PURPOSE));

            //unhide approve-reject
            approve_reject.setVisibility(View.VISIBLE);
            etEmployeeId.setVisibility(View.GONE);
            etAmount.setVisibility(View.GONE);
            tvTitle.setText("Action Request");


        } else {
            setTitle("Add new request"); //Place this text onto the Action Bar (Top bit of the app)
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_request_menu, menu); //Tell the system to use this custom menu for our app.
        return true; //'True' means the menu should be displayed. 'False' means the menu should not be displayed.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Handle clicks on menu items
        switch (item.getItemId()) {
            case R.id.save_request:
                saveRequest();
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void saveRequest() {

        String employeeName = etEmployeeName.getText().toString();
        String employeeId = etEmployeeId.getText().toString();
        String amount = etAmount.getText().toString();
        String purpose = etPurpose.getText().toString();
        String priority = tvStatus.getText().toString();

        //Validation
        if (employeeName.trim().isEmpty() || purpose.trim().isEmpty() || priority.trim().isEmpty()) { //'.trim' removes the empty spaces
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return; //Exit 'saveRequest()' if there is a blank. Otherwise, proceed to saving
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, employeeName);
        data.putExtra(EXTRA_EMPLOYEE_ID, employeeId);
        data.putExtra(EXTRA_AMOUNT, amount);
        data.putExtra(EXTRA_PURPOSE, purpose);
        data.putExtra(EXTRA_PRIORITY, priority);


        int id = getIntent().getIntExtra(EXTRA_ID, -1); //Default value is -1 because no entry in the DB will have this value, this no conflict, and the ID is invalid.

        if (id != -1) { //Only add intent extra of ID if the value is not -1.
            data.putExtra(EXTRA_ID, id);
        }

        //If validation was successful, set result as value of 'RESULT_OK', and pass the intent Extra data to the next Activity
        setResult(RESULT_OK, data); //'RESULT_OK' - this is simply an integer constant.
        finish(); //To close this activity

    }


    @Override
    public void onClick(View v) {
        if (v == approve_button) {
            Intent intent = new Intent(AddActivity.this, DisplayRequestsActivity.class);
            //set status indicator to item and update its status value
            intent.putExtra(AddActivity.EXTRA_REJECTED, Constants.EXTRA_APPROVED );
            Bundle animate = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent,animate);
            finish();
        } 
          if(v == reject_button) {
            Intent intent = new Intent(AddActivity.this, DisplayRequestsActivity.class);
            //set status indicator to item and update its status value
            intent.putExtra(AddActivity.EXTRA_REJECTED, Constants.EXTRA_REJECTED);

            Bundle animate = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent,animate);
            finish();

        }

    }


}