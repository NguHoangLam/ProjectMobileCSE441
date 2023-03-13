package com.example.projectmobilecse441;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BillDetailsHelper {
    public static void insert(Context context, int billCode, String bookTitle, String author, int price, int quantitySale){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BillCode",billCode);
        values.put("BookTitle",bookTitle);
        values.put("Author",author);
        values.put("Price",price);
        values.put("QuantitySale",quantitySale);
        db.insert("billDetails",null,values);
    }
    public static List<BillDetails> getBillDetails(Context context, Integer billCodeInput) {
        List<BillDetails> mapBills = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String billCodeCondition = String.valueOf(billCodeInput);
        Cursor cursor = db.rawQuery("SELECT * FROM billDetails WHERE BillCode = ?", new String[]{billCodeCondition});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Integer id = cursor.getInt(0);
            Integer billCode = cursor.getInt(1);
            String title = cursor.getString(2);
            String author = cursor.getString(3);
            Integer price = cursor.getInt(4);
            Integer quantitySale = cursor.getInt(5);
            BillDetails BillDetails = new BillDetails(id,billCode,title,author,price,quantitySale);
            mapBills.add(BillDetails);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mapBills;
    }
}
