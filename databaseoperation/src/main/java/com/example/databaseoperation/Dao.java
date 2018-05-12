package com.example.databaseoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
    private MyHelper myHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;

    public Dao(Context context) {
        myHelper = new MyHelper(context, "Data", null, 1);
        database = myHelper.getWritableDatabase();
    }

    //增
    public long insert(String UserName, String PassWord) {
        if (!database.query("user", null, "UserName=?", new String[]{UserName}, null, null, null).moveToNext()) {
            contentValues = new ContentValues();
            contentValues.put("UserName", UserName);
            contentValues.put("PassWord", PassWord);
            return database.insert("user", null, contentValues);
        } else {
            return -1;
        }
    }

    //删
    public int delete(String UserName) {
        return database.delete("user", "UserName=?", new String[]{UserName});
    }

    //查
    public Cursor select(){
        return database.query("user",null,null,null,null,null,null);
    }

    //改
    public int updata(String UserName,String PassWord){
        contentValues = new ContentValues();
        contentValues.put("PassWord",PassWord);
        return database.update("user",contentValues,"UserName=?",new String[]{UserName});
    }
}
