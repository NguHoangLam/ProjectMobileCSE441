package com.example.projectmobilecse441;

import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder{
    ImageView cartView;
    TextView cartTitleView;
    TextView cartPriceView;
    TextView cartAuthor,cartSubTotal;
    Button btn_DeleteCart, btn_minus_cart, btn_plus_cart;
    EditText edt_quantity_cart;



    public CartViewHolder(@NonNull View itemView){
        super(itemView);
        this.cartView = (ImageView) itemView.findViewById(R.id.imageCart);
        this.cartTitleView = (TextView) itemView.findViewById(R.id.tv_title_cart);
        this.cartAuthor = (TextView) itemView.findViewById(R.id.tv_author_cart);
        this.cartPriceView = (TextView) itemView.findViewById(R.id.tv_price_cart);
        this.edt_quantity_cart = (EditText) itemView.findViewById(R.id.edt_quantity_cart);
        this.cartSubTotal = (TextView) itemView.findViewById(R.id.tv_subtotal_cart);
        this.btn_DeleteCart = (Button) itemView.findViewById(R.id.btn_delete_cart);
        this.btn_minus_cart = (Button) itemView.findViewById(R.id.btn_minus_cart);
        this.btn_plus_cart =(Button) itemView.findViewById(R.id.btn_plus_cart);
    }
}
