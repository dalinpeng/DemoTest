package com.example.darling.demotest.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.darling.demotest.sqlitesource.DBHelper;

public class UserProvider extends ContentProvider {

    private Context mContext;
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String authorities = "com.example.darling.demotest.provider.UserProvider";

    public static final String User_Uri = "content://"+authorities+"/"+DBHelper.TABLE_NAME;

    public static final int USER_CODE = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authorities,"user",USER_CODE);
     // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
    }
    @Override
    public boolean onCreate() {

        mContext = getContext();
        dbHelper = new DBHelper(mContext);

        sqLiteDatabase = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String table = getTableName(uri);

        /*
        String table, String[] columns, String selection,
            String[] selectionArgs, String groupBy, String having,
            String orderBy, String limit
         */
        Cursor cursor = sqLiteDatabase.query(table,strings,s,
                strings1, null,null,
                s1,null);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        String table = getTableName(uri);
        sqLiteDatabase.insert(table,null,contentValues);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri){
       String table = null;
       switch (uriMatcher.match(uri)){
           case USER_CODE:

               table = DBHelper.TABLE_NAME;
               break;
       }
       return table;
    }
}
