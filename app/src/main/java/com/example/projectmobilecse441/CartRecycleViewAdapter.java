package com.example.projectmobilecse441;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CartRecycleViewAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<Cart> carts;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public CartRecycleViewAdapter(Context context,List<Cart> carts) {
        this.carts = carts;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.cart_items, parent, false);
        recyclerViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doClick((RecyclerView) parent, view);
            }
        });
        return new CartViewHolder(recyclerViewItem);
    }

    private void doClick(RecyclerView recyclerView, View itemView) {
        int itemPosition = recyclerView.getChildAdapterPosition(itemView);
        Cart cart = this.carts.get(itemPosition);
        Toast.makeText(this.context, cart.getBookId(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = this.carts.get(position);
//        byte[] imgByte = cart.getImage();
//        String imgString = new String(imgByte, StandardCharsets.UTF_8);
//        int imageResId = this.getDrawableResIdByName(imgString);
//        holder.cartView.setImageResource(imageResId);
        Bitmap img = BitmapFactory.decodeByteArray(cart.getImage(),0,cart.getImage().length);
        holder.cartView.setImageBitmap(img);
        holder.cartAuthor.setText(cart.getAuthor());
        holder.cartTitleView.setText(cart.getBookTitle());
        holder.cartPriceView.setText(cart.getPrice()+"");
        holder.edt_quantity_cart.setText(cart.getQuantitySale()+"");
        holder.cartSubTotal.setText(cart.getSubTotalPrice()+"");
        holder.btn_DeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CartHelper.delete(context.getApplicationContext(), cart.getBookId())){
                    Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.deleteSuccess),Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.btn_plus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantityStorage = cart.getQuantityStorage();
                int currentQuantity = Integer.parseInt(holder.edt_quantity_cart.getText().toString());
                if(currentQuantity==quantityStorage){
                    Toast.makeText(context.getApplicationContext(),context.getResources().getString(R.string.quantityStorage), Toast.LENGTH_SHORT).show();
                }else {
                    holder.edt_quantity_cart.setText(currentQuantity+1+"");
                    int updateQuantity = Integer.parseInt(holder.edt_quantity_cart.getText().toString());
                    int updateSubtotal = updateQuantity * Integer.parseInt(holder.cartPriceView.getText().toString());
                    if (CartHelper.update(context.getApplicationContext(),cart.getBookId(),updateQuantity,updateSubtotal)){
                        Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.quantityChanged),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        holder.btn_minus_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentQuantity = Integer.parseInt(holder.edt_quantity_cart.getText().toString());
                if(currentQuantity==1){
                    Toast.makeText(context.getApplicationContext(),context.getResources().getString(R.string.quantityZero), Toast.LENGTH_SHORT).show();
                }else {
                    holder.edt_quantity_cart.setText(currentQuantity-1+"");
                    int updateQuantity = Integer.parseInt(holder.edt_quantity_cart.getText().toString());
                    int updateSubtotal = updateQuantity * Integer.parseInt(holder.cartPriceView.getText().toString());
                    if (CartHelper.update(context.getApplicationContext(),cart.getBookId(),updateQuantity,updateSubtotal)){
                        Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.quantityChanged),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    //Find ImageID corresponding to the name of the image (in the directory drawable)
    public int getDrawableResIdByName(String resName) {
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
