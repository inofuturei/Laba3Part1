package com.example.laba3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        dbHelper.clearAndInsertSampleData(db);

        Button buttonView = findViewById(R.id.button2);
        Button buttonAdd = findViewById(R.id.button);
        Button buttonUpdate = findViewById(R.id.button3);

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME + " (" + DatabaseHelper.COLUMN_FIO + ") VALUES ('Новый Студент')");
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME + " SET " + DatabaseHelper.COLUMN_FIO + " = 'Иванов Иван Иванович' WHERE " + DatabaseHelper.COLUMN_ID + " = (SELECT MAX(" + DatabaseHelper.COLUMN_ID + ") FROM " + DatabaseHelper.TABLE_NAME + ")");
            }
        });
    }
}