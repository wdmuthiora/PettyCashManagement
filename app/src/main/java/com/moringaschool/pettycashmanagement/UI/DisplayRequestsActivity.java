package com.moringaschool.pettycashmanagement.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moringaschool.pettycashmanagement.Adapters.RequestAdapter;
import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.R;
import com.moringaschool.pettycashmanagement.RequestsViewModel;

import java.util.List;

public class DisplayRequestsActivity extends AppCompatActivity {

    private RequestsViewModel requestsViewModel; //We need an instance to call CRUD operations on our DB

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_requests);

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
                startActivity(intent);
                //startActivityForResult(intent, ADD_REQUEST_REQUEST);
            }
        });

        requestsViewModel = new ViewModelProvider(this).get(RequestsViewModel.class); //Ask Android system to provide ViewModel that we've specified

        //Attach observer to the LiveData sitting inside requestsViewModel
        requestsViewModel.getAllRequests().observe(this, new Observer<List<PettyCashRequest>>() {
            @Override
            public void onChanged(List<PettyCashRequest> pettyCashRequests) {
                requestAdapter.submitList(pettyCashRequests);
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
                Toast.makeText(DisplayRequestsActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


    }
}