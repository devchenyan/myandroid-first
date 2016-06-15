package com.example.echo.databasetest;

import android.content.ContentValues;
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

        Button createDatabase = (Button) findViewById(R.id.create_database);
        if (createDatabase != null) {
            createDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbHelper.getWritableDatabase();
                }
            });
        }

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
    }
}
