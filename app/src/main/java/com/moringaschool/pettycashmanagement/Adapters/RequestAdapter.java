package com.moringaschool.pettycashmanagement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.R;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestHolder> {


    @NonNull
    @Override
    public RequestAdapter.RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent, false);
        return new RequestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.RequestHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class RequestHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewPriority;
        private TextView textViewDescription;
        private EditText date;
        private TextView textViewCurrency;
        private TextView textViewAmount;


        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            date = itemView.findViewById(R.id.edit_text_date);
            textViewCurrency = itemView.findViewById(R.id.text_view_currency);
            textViewAmount = itemView.findViewById(R.id.text_view_amount);
        }
    }

}
