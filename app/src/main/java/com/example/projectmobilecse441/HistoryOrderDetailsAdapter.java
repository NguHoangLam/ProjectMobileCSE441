package com.example.projectmobilecse441;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryOrderDetailsAdapter extends RecyclerView.Adapter<HistoryOrderDetailsViewHolder>{
    private List<BillDetails> billDetails ;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public HistoryOrderDetailsAdapter(Context context,List<BillDetails> billDetails) {
        this.billDetails = billDetails;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public HistoryOrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.orderdetail_items, parent, false);
        return new HistoryOrderDetailsViewHolder(recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderDetailsViewHolder holder, int position) {
        BillDetails billDetails = this.billDetails.get(position);
        holder.tv_itemTitle.setText(billDetails.getBookTitle());
        holder.tv_author_orderDetails.setText(billDetails.getAuthor());
        holder.tv_price_orderDetails.setText(String.valueOf(billDetails.getPrice()));
        holder.tv_quantity_orderDetails.setText(String.valueOf(billDetails.getQuantitySale()));
        String total = String.valueOf(billDetails.getPrice()*billDetails.getQuantitySale());
        holder.tv_total_orderDetails.setText(total);
    }

    @Override
    public int getItemCount() {
        return billDetails.size();
    }
}
