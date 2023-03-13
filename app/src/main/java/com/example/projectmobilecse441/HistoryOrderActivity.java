package com.example.projectmobilecse441;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HistoryOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btn_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        mapping();
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("UserId");
        int userId = packageFromCaller.getInt("UserId");
        List<Bill> bills = BillHelper.getBillBaseCustomerId(getApplicationContext(), userId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new HistoryOrderAdapter(getApplicationContext(), bills));
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
    void mapping(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHistoryOrder);
        btn_home = (Button) findViewById(R.id.btn_home);
    }

}
