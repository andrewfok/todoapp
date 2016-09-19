package com.example.andrew_fok.todo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTodoActivity extends Activity {

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);
    }

    public void onSubmitClicked(View view) {
        TextView _description = (TextView)findViewById(R.id.description_field);
        TextView _title = (TextView)findViewById(R.id.title_field);
        ContentValues todoValues = new ContentValues();
        todoValues.put("TITLE", _title.getText().toString());
        todoValues.put("DESCRIPTION", _description.getText().toString());

        SQLiteOpenHelper todoDatabaseHelper = new TodoDatabaseHelper(CreateTodoActivity.this);
        try {
            db = todoDatabaseHelper.getWritableDatabase();
            db.insert("TODO", null, todoValues);
            db.close();

            Toast toast = Toast.makeText(this, "New Todo item is saved", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(CreateTodoActivity.this, MainActivity.class);
            startActivity(intent);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
