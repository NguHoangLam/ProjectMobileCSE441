package com.example.projectmobilecse441;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookViewHolder  extends RecyclerView.ViewHolder{
    ImageView bookView;
    TextView bookTitleView;
    TextView priceView;
    TextView author;
    Button btn_AddtoCartHome;

    public BookViewHolder(@NonNull View itemView){
        super(itemView);
        this.bookView = (ImageView) itemView.findViewById(R.id.img_book);
        this.bookTitleView = (TextView) itemView.findViewById(R.id.tv_title);
        this.author = (TextView) itemView.findViewById(R.id.tv_author);
        this.priceView = (TextView) itemView.findViewById(R.id.tv_price);
        this.btn_AddtoCartHome = (Button) itemView.findViewById(R.id.btn_addToCartHome);
    }
}
