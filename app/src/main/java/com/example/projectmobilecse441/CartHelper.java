package com.example.projectmobilecse441;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartHelper {
    public static HashMap<Integer,Cart> getAll(Context context) {
        HashMap<Integer,Cart> list = new HashMap<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Carts", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            int orderId = cursor.getInt(1);
            int bookId = cursor.getInt(2);
            String bookTitle = cursor.getString(3);
            String author = cursor.getString(4);
            byte[] img = cursor.getBlob(5);
            int quantity = cursor.getInt(6);
            int price = cursor.getInt(7);
            int subTotalPrice = cursor.getInt(8);
            int quantityStorage = cursor.getInt(9);
            Cart cart = new Cart(id, orderId, bookId, bookTitle,price, img, quantity, subTotalPrice,author,quantityStorage);
            list.put(bookId,cart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public static List<Cart> getAllList(Context context) {
        List<Cart> list = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Carts", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            int orderId = cursor.getInt(1);
            int bookId = cursor.getInt(2);
            String bookTitle = cursor.getString(3);
            String author = cursor.getString(4);
            byte[] img = cursor.getBlob(5);
            int quantity = cursor.getInt(6);
            int price = cursor.getInt(7);
            int subTotalPrice = cursor.getInt(8);
            int quantityStorage = cursor.getInt(9);
            Cart cart = new Cart(id, orderId, bookId, bookTitle,price, img, quantity, subTotalPrice,author,quantityStorage);
            list.add(cart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public static boolean insert(Context context, int orderId, int bookId,String bookTitle, int price, byte[] img,int quantity,int subTotalPrice,String author,int quantityStorage){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OrderId",orderId);
        values.put("BookId",bookId);
        values.put("BookTitle",bookTitle);
        values.put("Author",author);
        values.put("Price",price);
        values.put("Img",img);
        values.put("Quantity",quantity);
        values.put("SubTotalPrice",subTotalPrice);
        values.put("QuantityStorage",quantityStorage);
        long row = db.insert("Carts",null,values);
        return (row>0);
    }
    public static boolean update(Context context, int id, int quantity,int subTotal){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Quantity",quantity);
        values.put("SubTotalPrice",subTotal);
        int row = db.update("Carts",values,"BookId=?",new String[]{id+""});
        return (row>0);
    }
    public static boolean delete(Context context, int id){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("Carts","BookId=?",new String[]{id+""});
        return (row>0);
    }
    public static void resetCart(Context context){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("Carts",null,null);
    }
}
