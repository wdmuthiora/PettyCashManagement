package com.moringaschool.pettycashmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moringaschool.pettycashmanagement.Adapters.RequestAdapter;
import com.moringaschool.pettycashmanagement.R;

public class DisplayRequestsActivity extends AppCompatActivity {

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
                //startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

    }
}