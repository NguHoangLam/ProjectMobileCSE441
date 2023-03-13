package com.example.projectmobilecse441;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

public class BookRecyclerViewAdapter  extends RecyclerView.Adapter<BookViewHolder>  implements Filterable{
    private List<Book> books;
    private List<Book> booksOld;
    private Context context;
    private LayoutInflater mLayoutInflater;
    Button Btn_holder;

    public BookRecyclerViewAdapter(Context context, List<Book> books) {
        this.books = books;
        this.booksOld = books;
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if (strSearch.isEmpty()){
                    books = booksOld;
                }else {
                    List<Book> newbooks = new ArrayList<>();
                    for (Book book: booksOld){
                        if (book.getBookTitle().toLowerCase().contains(strSearch.toLowerCase()) ||
                                book.getAuthor().toLowerCase().contains(strSearch.toLowerCase()) ||
                                book.getDescription().toLowerCase().contains(strSearch.toLowerCase())){
                            newbooks.add(book);
                        }
                    }
                    books = newbooks;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = books;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                books = (List<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType){
        View recyclerViewItem = mLayoutInflater.inflate(R.layout.book_recyclerview_layout, parent, false);
        recyclerViewItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                doClick((RecyclerView) parent, view);
            }
        });
        return new BookViewHolder(recyclerViewItem);
    }
    private void doClick(RecyclerView recyclerView, View itemView){
        int itemPosition = recyclerView.getChildAdapterPosition(itemView);
        Book book = this.books.get(itemPosition);
        Intent myIntent = new Intent(context,BookDetailsActivity.class);
        //Toast.makeText(this.context, book.getBookTitle(), Toast.LENGTH_SHORT).show();
        int id = book.getId();
        Bundle bundle = new Bundle();
        bundle.putString("Title",book.getBookTitle());
        bundle.putInt("Img",getDrawableResIdByName(book.getImage()));
        bundle.putInt("Id",id);
        bundle.putString("Author",book.getAuthor());
        bundle.putString("Price",book.getPrice());
        bundle.putString("Category",book.getCategory());
        bundle.putString("Description",book.getDescription());
        bundle.putString("PublishDate",book.getPublishDate());
        bundle.putString("Quantity",book.getQuantity());
        myIntent.putExtra("MyPackage",bundle);
        context.startActivity(myIntent);



    }
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position){
        Book book = this.books.get(position);
        int imageResId = this.getDrawableResIdByName(book.getImage());
        holder.bookView.setImageResource(imageResId);
        holder.author.setText(book.getAuthor());
        holder.bookTitleView.setText(book.getBookTitle());
        holder.priceView.setText(book.getPrice()+"đ");
        Integer bookId = book.getId();

        holder.btn_AddtoCartHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[]imgByte = ByteBuffer.allocate(4).putInt(imageResId).array();
                boolean checkAdded = false;
                int price = Integer.parseInt(book.getPrice());
                int quantity = Integer.parseInt(book.getQuantity());
                HashMap<Integer,Cart> carts = CartHelper.getAll(context.getApplicationContext());
                if(carts.size()==0){
                    if(CartHelper.insert(context.getApplicationContext(),1,book.getId(),book.getBookTitle(),price,imgByte,1,price,book.getAuthor(),quantity)){
                        Toast.makeText(context.getApplicationContext(),"Add Success !!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(!carts.containsKey(bookId)){
                        if(CartHelper.insert(context.getApplicationContext(),1,book.getId(),book.getBookTitle(),price,imgByte,1,price,book.getAuthor(),quantity)){
                            Toast.makeText(context.getApplicationContext(),"Add Success !!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Cart tempCart = carts.get(bookId);
                        tempCart.setQuantitySale(tempCart.getQuantitySale()+1);
                        CartHelper.delete(context.getApplicationContext(), bookId);
                        if(tempCart.getQuantitySale()>10){
                            Toast.makeText(context.getApplicationContext(),"Out of Storage !!!", Toast.LENGTH_SHORT).show();
                        }else {
                            if (CartHelper.insert(context.getApplicationContext(), 1, book.getId(), book.getBookTitle(), price, imgByte, tempCart.getQuantitySale(), price, book.getAuthor(), quantity)) {
                                Toast.makeText(context.getApplicationContext(), "Add Success !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });

    }
    //Find ImageID corresponding to the name of the image (in the directory drawable)
    public int getDrawableResIdByName(String resName){
        String pkgName = context.getPackageName();
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }
    @Override
    public int getItemCount(){
        return books.size();
    }
}
