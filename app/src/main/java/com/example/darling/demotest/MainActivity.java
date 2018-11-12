package com.example.darling.demotest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.darling.demotest.entity.User;
import com.example.darling.demotest.provider.UserProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ContentResolver resolver;
    Uri uri;
    List<User> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置要操作的数据源
        uri = Uri.parse(UserProvider.User_Uri);
        //要插入的数据
        ContentValues contentValues = new ContentValues();
        //指定插入id
        contentValues.put("_id",3);
        contentValues.put("name","first");
        contentValues.put("url","first url");

        resolver = getContentResolver();
        resolver.insert(uri,contentValues);

    }

    private void query(){

        /*
        SELECT [ALL | DISTINCT] <目标列表达式>[,<目标列表达式>]…
   FROM <表名或视图名>[,<表名或视图名>]…
  [WHERE <条件表达式>]
  [GROUP BY <列名> [HAVING <条件表达式>]]
  [ORDER BY <列名> [ASC|DESC]…]
  SELECT *
  FROM Student
  WHERE Id>10
  GROUP BY Age HAVING AVG(Age) > 20
  ORDER BY Id DESC
        @RequiresPermission.Read @NonNull Uri uri,
            @Nullable String[] projection, ( A list of which columns to return. Passing null will
     *         return all columns, which is inefficient.)
            @Nullable String selection, (A filter declaring which rows to return, formatted as an
     *         SQL WHERE clause (excluding the WHERE itself). Passing null will
     *         return all rows for the given URI.)
            @Nullable String[] selectionArgs, （You may include ?s in selection, which will be
     *         replaced by the values from selectionArgs,）
            @Nullable String sortOrder

         */
        Cursor cursor = resolver.query(uri,new String[]{"name","url"},"_id>?",new String[]{"1"},"_id DESC");
        if (cursor!= null) {
        try {
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            list.add(new User(name,url));
        }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cursor.close();
        }
        }
    }
}
