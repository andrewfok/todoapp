package com.example.andrew_fok.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrew_Fok on 9/13/2016.
 */
public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TODO";
    private static final int DB_VERSION = 1;

    TodoDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1) {
            db.execSQL("CREATE TABLE TODO ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TITLE TEXT, "
                    + "DESCRIPTION TEXT "
                    + ");");
            insertTodo(db, "Buy Milk", "5 gallons, semi-skimmed");
            insertTodo(db, "Walk Dog", "make that fat dog walk 6km");
        }
    }

    private static void insertTodo(SQLiteDatabase db, String todo, String description) {
        ContentValues todoValues = new ContentValues();
        todoValues.put("TITLE", todo);
        todoValues.put("DESCRIPTION", description);
        db.insert("TODO", null, todoValues);
    }
}
