package com.moringaschool.pettycashmanagement.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moringaschool.pettycashmanagement.Adapters.RequestAdapter;
import com.moringaschool.pettycashmanagement.R;

public class DisplayRequests extends AppCompatActivity {

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

    }
}