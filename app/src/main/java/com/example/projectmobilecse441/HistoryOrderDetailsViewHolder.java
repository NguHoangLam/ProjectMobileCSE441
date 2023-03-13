package com.example.projectmobilecse441;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryOrderDetailsViewHolder extends RecyclerView.ViewHolder{
    TextView tv_itemTitle, tv_author_orderDetails, tv_price_orderDetails, tv_quantity_orderDetails, tv_total_orderDetails;
    public HistoryOrderDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tv_itemTitle = (TextView) itemView.findViewById(R.id.tv_title_orderdetails);
        this.tv_author_orderDetails = (TextView) itemView.findViewById(R.id.tv_author_orderdetails);
        this.tv_price_orderDetails = (TextView) itemView.findViewById(R.id.tv_price_orderdetails);
        this.tv_quantity_orderDetails = (TextView) itemView.findViewById(R.id.tv_quantity_orderdetails);
        this.tv_total_orderDetails = (TextView) itemView.findViewById(R.id.tv_totalprice_orderdetails);
    }
}
