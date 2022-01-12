package com.moringaschool.pettycashmanagement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.R;


public class RequestAdapter extends ListAdapter<PettyCashRequest, RequestAdapter.RequestHolder> {


    public RequestAdapter() {
        super(DIFF_CALLBACK);
    }

    //compare lists when there's a change
    public static final DiffUtil.ItemCallback<PettyCashRequest> DIFF_CALLBACK=
            new DiffUtil.ItemCallback<PettyCashRequest>() {
        @Override
        public boolean areItemsTheSame(@NonNull PettyCashRequest oldItem, @NonNull PettyCashRequest newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PettyCashRequest oldItem, @NonNull PettyCashRequest newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getPurpose().equals(newItem.getPurpose()) &&
                    oldItem.getPriority().equals(newItem.getPriority())  &&
                    oldItem.getCurrency().equals(newItem.getCurrency()) &&
                    oldItem.getAmount()==newItem.getAmount();
        }
    };

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent, false);
        return new RequestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        PettyCashRequest currentRequest= getItem(position);

        holder.textViewName.setText(currentRequest.getName());
        holder.textViewPriority.setText(String.valueOf(currentRequest.getPriority()));
        holder.textViewPurpose.setText(currentRequest.getPurpose());
        holder.textViewCurrency.setText(currentRequest.getCurrency());
        holder.textViewAmount.setText(currentRequest.getAmount());


    }

    public PettyCashRequest getRequestPosition(int position){
        return getItem(position);
}


    class RequestHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPriority;
        private TextView textViewPurpose;
        private EditText date;
        private TextView textViewCurrency;
        private TextView textViewAmount;


        public RequestHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewPurpose = itemView.findViewById(R.id.text_view_purpose);
            date = itemView.findViewById(R.id.edit_text_date);
            textViewCurrency = itemView.findViewById(R.id.text_view_currency);
            textViewAmount = itemView.findViewById(R.id.text_view_amount);


        }
    }

}
