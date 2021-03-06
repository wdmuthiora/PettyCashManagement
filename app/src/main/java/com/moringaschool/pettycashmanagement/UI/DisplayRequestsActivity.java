package com.moringaschool.pettycashmanagement.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.pettycashmanagement.Adapters.RequestAdapter;
import com.moringaschool.pettycashmanagement.Constants.Constants;
import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.R;
import com.moringaschool.pettycashmanagement.RequestsViewModel;

import java.util.List;

public class DisplayRequestsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    public static final int ADD_PETTY_CASH_REQUEST = 1; //To add a request
    public static final int EDIT_PETTY_CASH_REQUEST = 2; //To edit a request

    private RequestsViewModel requestsViewModel; //We need an instance to call CRUD operations on our DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requests);

        //Authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName());
                } else {

                }
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        RequestAdapter requestAdapter = new RequestAdapter();
        recyclerView.setAdapter(requestAdapter);

        FloatingActionButton buttonAddRequest = findViewById(R.id.button_add_request);

        buttonAddRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayRequestsActivity.this, AddActivity.class);

                startActivityForResult(intent, ADD_PETTY_CASH_REQUEST);
            }
        });

        requestsViewModel = new ViewModelProvider(this).get(RequestsViewModel.class); //Ask Android system to provide ViewModel that we've specified

        //Attach observer to the LiveData sitting inside requestsViewModel
        requestsViewModel.getAllRequests().observe(this, new Observer<List<PettyCashRequest>>() {
            @Override
            public void onChanged(List<PettyCashRequest> pettyCashRequests) {
                requestAdapter.submitList(pettyCashRequests);
              //Toast.makeText(DisplayRequestsActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        //Swipe & Drag
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                requestsViewModel.delete(requestAdapter.getRequestPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(DisplayRequestsActivity.this, "Request deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        requestAdapter.setOnItemClickListener(new RequestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PettyCashRequest pettyCashRequest) { //Pass the clicked Petty Cash Request item inside the constructor

                //Use Shift+F6 to edit a field project-wide.
                Intent intent = new Intent(DisplayRequestsActivity.this, AddActivity.class); //Since we are in this anonymous inner class, we can not call 'this' context
                intent.putExtra(AddActivity.EXTRA_ID, pettyCashRequest.getId()); //We need to pass the clicked item's ID because Room uses it as the Primary Key
                intent.putExtra(AddActivity.EXTRA_EMPLOYEE_ID, pettyCashRequest.getId());
                intent.putExtra(AddActivity.EXTRA_NAME, pettyCashRequest.getName());
                intent.putExtra(AddActivity.EXTRA_AMOUNT, pettyCashRequest.getAmount());
                intent.putExtra(AddActivity.EXTRA_PRIORITY, pettyCashRequest.getStatus());
                intent.putExtra(AddActivity.EXTRA_PURPOSE, pettyCashRequest.getPurpose());
                Bundle animate = ActivityOptions.makeSceneTransitionAnimation(DisplayRequestsActivity.this).toBundle();
                startActivityForResult(intent, Constants.EDIT_PETTY_CASH_REQUEST);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //This function will still be triggered if we left the AddActivity using the back button, meaning abortion of creation of a new request, but now, the result will not be set to RESULT_OK, but will be set by the system to RESULT_CANCELLED.

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PETTY_CASH_REQUEST && resultCode==RESULT_OK){

            String name = data.getStringExtra(AddActivity.EXTRA_NAME);
            String employee_Id = data.getStringExtra(AddActivity.EXTRA_EMPLOYEE_ID);
            int amount = data.getIntExtra(AddActivity.EXTRA_AMOUNT, 1); //Integer values are not nullable, so we pass a default value, in this case, '1'. This can also serve as a default value.
            String priority = data.getStringExtra(AddActivity.EXTRA_PRIORITY);
            String purpose = data.getStringExtra(AddActivity.EXTRA_PURPOSE);


            PettyCashRequest pettyCashRequest = new PettyCashRequest(name, employee_Id, amount, priority, purpose);

            requestsViewModel.insert(pettyCashRequest);
            Toast.makeText(this, "Petty Cash Request saved", Toast.LENGTH_SHORT).show();

        } else  if (requestCode == EDIT_PETTY_CASH_REQUEST && resultCode==RESULT_OK){

            int id = data.getIntExtra(AddActivity.EXTRA_ID, -1); //Default value means it does not exist in Db , hence no conflict.

            if (id==-1){
                Toast.makeText(DisplayRequestsActivity.this, "Petty Cash Request can't be edited", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddActivity.EXTRA_NAME);
            String employee_Id = data.getStringExtra(AddActivity.EXTRA_EMPLOYEE_ID);
            int amount = data.getIntExtra(AddActivity.EXTRA_AMOUNT, 1); //Integer values are not nullable, so we pass a default value, in this case, '1'. This can also serve as a default value.
            String priority = data.getStringExtra(AddActivity.EXTRA_PRIORITY);
            String purpose = data.getStringExtra(AddActivity.EXTRA_PURPOSE);

            String status = data.getStringExtra(AddActivity.EXTRA_REJECTED);

            PettyCashRequest pettyCashRequest = new PettyCashRequest(name, employee_Id, amount, priority, purpose);

            pettyCashRequest.setId(id); //Set the ID of the Petty Cash Request object we are creating, in order for Room to identify which pettyCashRequest (row) we are editing.

            requestsViewModel.update(pettyCashRequest);
            Toast.makeText(DisplayRequestsActivity.this, "Petty Cash Request updated", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Petty Cash Request not saved", Toast.LENGTH_SHORT).show();

        }
    }

    //Logout functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(DisplayRequestsActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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