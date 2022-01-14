package com.moringaschool.pettycashmanagement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.pettycashmanagement.Models.PettyCashRequest;
import com.moringaschool.pettycashmanagement.R;


public class RequestAdapter extends ListAdapter<PettyCashRequest, RequestAdapter.RequestHolder> {

    private OnItemClickListener listener;


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
                    oldItem.getStatus().equals(newItem.getStatus())  &&
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

        PettyCashRequest currentRequest = getItem(position);

        holder.textViewName.setText(currentRequest.getName());
        holder.textViewPriority.setText(String.valueOf(currentRequest.getStatus()));
        holder.textViewPurpose.setText(currentRequest.getPurpose());
        holder.textViewCurrency.setText("Kes. ");
        holder.textViewAmount.setText(String.valueOf(currentRequest.getAmount()));

    }

    public PettyCashRequest getRequestPosition(int position){
        return getItem(position);
}




    class RequestHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPriority;
        private TextView textViewPurpose;
        private TextView date;
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


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) { //Check that we call setOnItemClickListener(), otherwise, it will be null.
                        // This prevents a crash
                        //'position != RecyclerView.NO_POSITION' checks that we do not click on an item inside the recycler view with an invalid position, which could be the case if we delete an item, but it's still in it's delete animation, in which case the position could be NO_POSITION, of -1.

                        listener.onItemClick(getItem(position));

                    }
                }
            });

        }
    }


    public interface OnItemClickListener {
        void onItemClick(PettyCashRequest pettyCashRequest);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { //'OnItemClickListener' is the one above, (take care of the package name)
        this.listener = listener; //We use the 'listener' variable to call 'onItemClick()' method, and pass a Petty Cash Request object to whatever method implements this interface

    }

}
