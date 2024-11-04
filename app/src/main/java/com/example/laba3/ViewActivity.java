package com.example.laba3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        TextView textView = findViewById(R.id.text_view);
        StringBuilder stringBuilder = new StringBuilder();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String fio = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FIO));
                String time = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME));
                stringBuilder.append("ID: ").append(id).append(", ФИО: ").append(fio).append(", Время добавления: ").append(time).append("n");
            } while (cursor.moveToNext());
        }
        cursor.close();

        textView.setText(stringBuilder.toString());
    }
}