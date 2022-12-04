package com.course.msp.contents;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FoodDBManager extends SQLiteOpenHelper {

    public static final String FOOD_DB = "Foods.db";
    public static final String FOOD_TABLE = "Foods";
    public Context context = null;
    public static FoodDBManager dbManager = null;

    public static final String CREATE_DB = " CREATE TABLE " + FOOD_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "  image TEXT NOT NULL, name TEXT NOT NULL, count TEXT NOT NULL, feel TEXT NOT NULL, time TEXT NOT NULL);";

    public static final String DROP_DB = " DROP TABLE " + FOOD_TABLE;

    public static FoodDBManager getInstance(Context context){
        if(dbManager == null){
            Log.d("ggogogogogogogo","ogogogogogogogo");
            dbManager = new FoodDBManager(context, FOOD_DB, null, 1);
        }
        return dbManager;
    }

    public FoodDBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Information !!!", CREATE_DB);
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }

    public void deleteAll(){
        getWritableDatabase().execSQL(DROP_DB);
    }

    public long insert(ContentValues addValues){
        return getWritableDatabase().insert(FOOD_TABLE, null, addValues);
    }

    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(FOOD_TABLE, columns, selection, selectionArgs, groupBy, groupBy, orderBy);
    }

    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(FOOD_TABLE, whereClause, whereArgs);
    }

}
