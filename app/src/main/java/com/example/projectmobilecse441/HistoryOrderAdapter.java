package com.example.projectmobilecse441;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderViewHolder> {
    private List<Bill> bills ;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public HistoryOrderAdapter(Context context,List<Bill> bills) {
        this.bills = bills;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.order_items, parent, false);
        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick((RecyclerView) parent, view, context);
            }
        });
        return new HistoryOrderViewHolder(recyclerViewItem);
    }
    private void doClick(RecyclerView recyclerView, View itemView, Context context) {
        int itemPosition = recyclerView.getChildAdapterPosition(itemView);
        Bill bill = this.bills.get(itemPosition);
        String orderID = String.valueOf(bill.getId());
        Intent myIntent = new Intent(context.getApplicationContext(),HistoryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("OrderID", bill.getId());
        myIntent.putExtra("OrderID",bundle);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderViewHolder holder, int position) {
        Bill bill = this.bills.get(position);
        String orderID = String.valueOf(bill.getId());
        holder.orderID.setText(orderID);
        holder.orderDate.setText(bill.getDate());
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }
}
