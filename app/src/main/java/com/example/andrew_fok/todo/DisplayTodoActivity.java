package com.example.andrew_fok.todo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayTodoActivity extends Activity {

    public static final String TODO_NO = "todoNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_todo);

        int todoNo = (Integer) getIntent().getExtras().get(TODO_NO);

        try {
            SQLiteOpenHelper todoDatabaseHelper = new TodoDatabaseHelper(this);
            SQLiteDatabase db = todoDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query("TODO",
                    new String[] {"TITLE", "DESCRIPTION"},
                    "_id = ?",
                    new String[] {Integer.toString(todoNo)},
                    null, null, null);

            if(cursor.moveToFirst()) {
                String titleText = cursor.getString(0);
                String descriptionText = cursor.getString(1);

                TextView title = (TextView)findViewById(R.id.title);
                title.setText(titleText);

                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);
            }

            cursor.close();
            db.close();

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
