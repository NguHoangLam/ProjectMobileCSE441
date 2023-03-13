package com.example.projectmobilecse441;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

public class BookDetailsActivity extends Activity {
    Button AddCart, btn_Plus, btn_Minus;
    ImageView img_viewDetails;
    TextView tv_Title, tv_Price, tv_Category, tv_Author, tv_PublishDate,tv_Description;
    EditText edt_Quantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        mapping();
        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        int Id = packageFromCaller.getInt("Id");
        String price = packageFromCaller.getString("Price");
        String author = packageFromCaller.getString("Author");
        String category = packageFromCaller.getString("Category");
        String publishDate = packageFromCaller.getString("PublishDate");
        String description = packageFromCaller.getString("Description");
        String title = packageFromCaller.getString("Title");
        String quantityStorage = packageFromCaller.getString("Quantity");
        int Img = packageFromCaller.getInt("Img");
        byte[] imgByte = ByteBuffer.allocate(4).putInt(Img).array();
        img_viewDetails.setImageResource(Img);
        tv_Title.setText(title);
        tv_Price.setText(price);
        tv_Category.setText(category);
        tv_Author.setText(author);
        tv_PublishDate.setText(publishDate);
        tv_Description.setText(description);
        btn_Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(edt_Quantity.getText().toString());
                if(quantity==Integer.parseInt(quantityStorage)){
                    Toast.makeText(getApplicationContext(),getString(R.string.quantityStorage), Toast.LENGTH_SHORT).show();
                }else {
                    edt_Quantity.setText(quantity+1+"");
                }
            }
        });
        btn_Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = Integer.parseInt(edt_Quantity.getText().toString());
                if(quantity==1){
                    Toast.makeText(getApplicationContext(),getString(R.string.quantityZero), Toast.LENGTH_SHORT).show();
                }else {
                    edt_Quantity.setText(quantity-1+"");
                }
            }
        });
//        Buy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(getApplicationContext(),CartActivity.class);
//                startActivity(myIntent);
//               // Toast.makeText(getApplicationContext(),Id+" "+title, Toast.LENGTH_SHORT).show();
//            }
//        });

        AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int storage = Integer.parseInt(quantityStorage);
                int quantityCart = Integer.parseInt(edt_Quantity.getText().toString());
                int priceCart = Integer.parseInt(price);
                int subTotal = priceCart * quantityCart;
                HashMap<Integer,Cart> carts = CartHelper.getAll(getApplicationContext());
                if(carts.size()==0){
                    if(CartHelper.insert(getApplicationContext(),1,Id,title,priceCart,imgByte,quantityCart,subTotal,author,storage)){
                        Toast.makeText(getApplicationContext(),getString(R.string.addSuccess), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    if(!carts.containsKey(Id)){
                        if(CartHelper.insert(getApplicationContext(),1,Id,title,priceCart,imgByte,quantityCart,subTotal,author,storage)){
                            Toast.makeText(getApplicationContext(),getString(R.string.addSuccess), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Cart tempCart = carts.get(Id);
                        tempCart.setQuantitySale(tempCart.getQuantitySale()+quantityCart);
                        if(tempCart.getQuantitySale()>storage){
                            Toast.makeText(getApplicationContext(),getString(R.string.quantityStorage), Toast.LENGTH_SHORT).show();
                        }else{
                            CartHelper.delete(getApplicationContext(), Id);
                            if(CartHelper.insert(getApplicationContext(),1,Id,title,priceCart,imgByte,tempCart.getQuantitySale(),subTotal,author,storage)){
                                Toast.makeText(getApplicationContext(),getString(R.string.addSuccess), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }

            }
        });
    }
    void mapping(){
        tv_Title = (TextView) findViewById(R.id.tv_bookTitle_details);
        tv_Author = (TextView) findViewById(R.id.tv_author_details);
        tv_Category = (TextView) findViewById(R.id.tv_category_details);
        tv_Price = (TextView) findViewById(R.id.tv_price_details);
        tv_PublishDate=(TextView) findViewById(R.id.tv_publishDate_details);
        img_viewDetails = (ImageView) findViewById(R.id.imageView_details);
        tv_Description = (TextView) findViewById(R.id.tv_description);
        AddCart = (Button) findViewById(R.id.btn_addToCart_details);
//        Buy = (Button) findViewById(R.id.btn_buy_details);
        btn_Minus = (Button) findViewById(R.id.btn_minus);
        btn_Plus = (Button) findViewById(R.id.btn_plus);
        edt_Quantity = (EditText) findViewById(R.id.edt_quantity_details);
    }
}
