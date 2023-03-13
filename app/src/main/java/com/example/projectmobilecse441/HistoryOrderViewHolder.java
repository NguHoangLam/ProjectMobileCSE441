package com.example.projectmobilecse441;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryOrderViewHolder extends RecyclerView.ViewHolder{

    TextView orderID, orderDate, orderStatus;




    public HistoryOrderViewHolder(@NonNull View itemView){
        super(itemView);
        this.orderID = (TextView) itemView.findViewById(R.id.tv_orderId);
        this.orderDate = (TextView) itemView.findViewById(R.id.tv_orderDate);
        this.orderStatus = (TextView) itemView.findViewById(R.id.tv_status);


    }
}
