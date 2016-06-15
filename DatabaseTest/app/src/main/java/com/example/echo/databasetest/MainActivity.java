package com.example.echo.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

//        创建
        Button createDatabase = (Button) findViewById(R.id.create_database);
        if (createDatabase != null) {
            createDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.getWritableDatabase();
                }
            });
        }

//        添加
        Button addData = (Button) findViewById(R.id.add_data);
        if (addData != null) {
            addData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
//                    装第一组数据
                    values.put("name", "The First");
                    values.put("author", "Kiyola");
                    values.put("pages", 455);
                    values.put("price", 16.96);
//                    插入
                    db.insert("Book", null, values);
                    values.clear();

//                    装第二组数据
                    values.put("name", "The Second");
                    values.put("author", "Michael");
                    values.put("pages", 355);
                    values.put("price", 15.46);

                    db.insert("Book", null, values);
                }
            });
        }

//        更新
        Button updateData = (Button) findViewById(R.id.update_data);
        if (updateData != null) {
            updateData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("price", 34.11);
                    db.update("Book", values, "name=?", new String[]{"The First"});
                }
            });
        }

//        删除
        Button deleteData = (Button) findViewById(R.id.delete_data);
        if (deleteData != null) {
            deleteData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.delete("Book", "pages > ?", new String[]{"500"});
                }
            });
        }

//        查询
        Button queryButton = (Button) findViewById(R.id.query_data);
        if (queryButton != null) {
            queryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();

//                    查询所有数据
                    Cursor cursor = db.query("Book", null, null, null, null, null, null);
                    if (cursor.moveToFirst()) {
                        do {
//                            遍历cursor对象,取出数据
                            String name = cursor.getString(cursor.getColumnIndex("name"));
                            String author = cursor.getString(cursor.getColumnIndex("author"));
                            int page = cursor.getInt(cursor.getColumnIndex("page"));
                            double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
            });
        }

//        事务
        Button replaceData = (Button) findViewById(R.id.replace_data);
        if (replaceData != null) {
            replaceData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
//                    开启事务
                    db.beginTransaction();

                    try {
                        db.delete("Book", null, null);
                        if (true) {
    //                        在这里抛出一个异常,让任务失败
                            throw new NullPointerException();
                        }
                        ContentValues values = new ContentValues();
                        values.put("name", "The New");
                        values.put("author", "Jack");
                        values.put("pages", 475);
                        values.put("price", 23.46);
                        db.insert("Book", null, values);
//                        事务已经执行成功
                        db.setTransactionSuccessful();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
//                        结束事务
                        db.endTransaction();
                    }
                }
            });
        }
    }
}
