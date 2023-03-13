package com.example.projectmobilecse441;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btn_back;
    TextView tv_orderIDOrderDetails, tv_finaltotalOrderDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detailsdialog);
        mapping();
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("OrderID");
        int OrderID = packageFromCaller.getInt("OrderID");
        tv_orderIDOrderDetails.setText(String.valueOf(OrderID)+getString(R.string.including));
        List<BillDetails> billDetails = BillDetailsHelper.getBillDetails(getApplicationContext(),OrderID);
        int billTotal = 0;
        for(BillDetails detail: billDetails){
            int total = detail.getPrice()*detail.getQuantitySale();
            billTotal += total;
        }
        tv_finaltotalOrderDetails.setText(getString(R.string.totalOrder)+String.valueOf(billTotal));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HistoryOrderDetailsAdapter(getApplicationContext(), billDetails));
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    void mapping(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewOrderDetailsDialog);
        btn_back = (Button) findViewById(R.id.btn_back);
        tv_orderIDOrderDetails = (TextView) findViewById(R.id.tv_orderId_OrderDetails);
        tv_finaltotalOrderDetails = (TextView) findViewById(R.id.tv_fianltotal_OrderDetails);
    }
}
