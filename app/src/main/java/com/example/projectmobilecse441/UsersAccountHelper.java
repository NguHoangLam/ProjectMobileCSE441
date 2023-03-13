package com.example.projectmobilecse441;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UsersAccountHelper {
    public static Map<String, UsersAccount> getAll(Context context) {
        Map<String, UsersAccount> mapUsers = new HashMap<>();
        ArrayList<UsersAccount> list = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from UsersAccount", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String userName = cursor.getString(1);
            String password = cursor.getString(2);
            String fullName = cursor.getString(3);
            int accountType = cursor.getInt(4);
            int totalSpent = cursor.getInt(5);
            int rewardPoint = cursor.getInt(6);
            int timesShopping = cursor.getInt(7);
            String address = cursor.getString(8);
            int phone = cursor.getInt(9);
            UsersAccount user = new UsersAccount(id,userName,password,fullName,accountType,totalSpent,rewardPoint,timesShopping,address,phone);
            list.add(user);
            mapUsers.put(userName,user);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return mapUsers;
    }
    public static void update(Context context, int id, int timesShopping,int spent){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        int totalSpent = 0;
        int rewardPoint = 0;
        int accountType = -1;
        Cursor cursor = db.rawQuery("Select * from UsersAccount", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int userId = cursor.getInt(0);
            if(userId==id){
                totalSpent = cursor.getInt(5);
                rewardPoint = cursor.getInt(6);
                accountType = cursor.getInt(4);
            }
            cursor.moveToNext();
        }
        cursor.close();
        ContentValues values = new ContentValues();
        values.put("TimesShopping",timesShopping+1);
        values.put("TotalSpent",spent+totalSpent);
        if((totalSpent+spent)>=5000000){
            values.put("AccountType",1);
        }else{
            values.put("AccountType",accountType);
        }
        if(accountType==0){
            values.put("RewardPoint",(spent*0.05)+rewardPoint);
        }else if(accountType==1){
            values.put("RewardPoint",(spent*0.1)+rewardPoint);
        }

        db.update("UsersAccount",values,"ID=?",new String[]{id+""});

    }
    public static boolean insert(Context context,String userName, String password, String fullName, String address, Integer phone){
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserName",userName);
        values.put("Password",password);
        values.put("FullName",fullName);
        values.put("AccountType",0);
        values.put("TotalSpent",0);
        values.put("RewardPoint",0);
        values.put("TimesShopping",0);
        values.put("Address",address);
        values.put("Phone",phone);
        long row = db.insert("UsersAccount",null,values);
        return (row>0);
    }
}
