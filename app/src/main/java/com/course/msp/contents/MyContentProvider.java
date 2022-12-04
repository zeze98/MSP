package com.course.msp.contents;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.ContentProvider;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;


public class MyContentProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.course.msp.contents.MyContentProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/foods";
    public static final Uri CONTENT_URI = Uri.parse(URL);
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String FOOD_COUNT = "count";
    public static final String EAT_TIME = "time";
    public static final String FOOD_FEEL = "feel";
    public static final String IMAGE = "image";

    public FoodDBManager dbManager;

    public MyContentProvider(){ }

    @Override
    public boolean onCreate() {
        dbManager = FoodDBManager.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return dbManager.query(projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // dbManager.deleteAll();
        long rowId = dbManager.insert(values);
        for (Map.Entry<String, Object> stringObjectEntry : values.valueSet()) {
            Log.d("Log","Key : " + stringObjectEntry.getKey().toString() + " Value : " + stringObjectEntry.getValue().toString());
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return dbManager.delete(selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
