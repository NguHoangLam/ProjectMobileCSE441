package com.example.projectmobilecse441;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillHelper {
    public static boolean insert(Context context, int billId, int customerId, int total, String date,String address,int phone){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",billId);
        values.put("CustomerId",customerId);
        values.put("Total",total);
        values.put("Date",date);
        values.put("Address",address);
        values.put("Phone",phone);
        long row = db.insert("bills",null,values);
        return (row>0);
    }
    public static List<Bill> getBillBaseCustomerId(Context context, Integer customerIdInput) {
        List<Bill> mapBills = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String UserId = String.valueOf(customerIdInput);
        Cursor cursor = db.rawQuery("SELECT * FROM bills WHERE CustomerId = ?", new String[]{UserId});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            int customerId = cursor.getInt(1);
            int total = cursor.getInt(2);
            String date = cursor.getString(3);
            String address = cursor.getString(4);
            int phone = cursor.getInt(5);
            Bill bill = new Bill(id,customerId,total,date,address,phone);
            mapBills.add(bill);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mapBills;
    }
}
