package com.example.darling.demotest.sqlitesource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "demotest.db";

    public static final String TABLE_NAME = "user";

    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
/*
CREATE TABLE <表名>(<列名> <数据类型>[列级完整性约束条件]
                  [,<列名> <数据类型>[列级完整性约束条件]]…);
 */
        sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME
                + "(" +
                "_id integer primary key autoincrement,"
                + "name text,"
                + "url text" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
