package com.example.projectmobilecse441;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbarCart;
    TextView tv_TotalPrice;
    Button btn_CheckOut, btn_Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mapping();
        List<Cart> carts = CartHelper.getAllList(getApplicationContext());
        int total = 0;
        for(int i =0;i<carts.size();i++){
            total+=carts.get(i).getSubTotalPrice();
        }
        tv_TotalPrice.setText(total+"");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CartRecycleViewAdapter(getApplicationContext(), carts));
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carts.clear();
                carts.addAll(CartHelper.getAllList(getApplicationContext()));
                recyclerView.setAdapter(new CartRecycleViewAdapter(getApplicationContext(), carts));
                int total = 0;
                for(int i =0;i<carts.size();i++){
                    total+=carts.get(i).getSubTotalPrice();
                }
                tv_TotalPrice.setText(total+"");
            }
        });
        btn_CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callerIntent = getIntent();
                Bundle packageFromCaller = callerIntent.getBundleExtra("UserPackage");
                int userId = packageFromCaller.getInt("UserId");
                int timesShopping = packageFromCaller.getInt("TimesShopping");
                String address = packageFromCaller.getString("Address");
                int phone = packageFromCaller.getInt("Phone");
                List<Cart> cartList = CartHelper.getAllList(getApplicationContext());
                int total = Integer.parseInt(tv_TotalPrice.getText().toString());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                int billCode = (int) (new Date().getTime()/1000);
                if(BillHelper.insert(getApplicationContext(),billCode,userId,total,formatter.format(date),address,phone)){
                    Toast.makeText(getApplicationContext(),getString(R.string.saved),Toast.LENGTH_SHORT).show();
                }
                for(int i=0;i<cartList.size();i++){
                    Cart cart = cartList.get(i);
                    BillDetailsHelper.insert(getApplicationContext(),billCode,cart.getBookTitle(),cart.getAuthor(),cart.getPrice(),cart.getQuantitySale());
                }
                UsersAccountHelper.update(getApplicationContext(),userId,timesShopping,total);
                CartHelper.resetCart(getApplicationContext());
                finish();
            }
        });
    }
    void mapping(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCart);
        toolbarCart = (Toolbar) findViewById(R.id.toolbarcart);
        tv_TotalPrice = (TextView) findViewById(R.id.tv_totalprice);
        btn_CheckOut = (Button) findViewById(R.id.btn_checkout);
        btn_Update = (Button) findViewById(R.id.btn_updateCart);
    }

}