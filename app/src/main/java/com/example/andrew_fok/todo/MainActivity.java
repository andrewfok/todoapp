package com.example.andrew_fok.todo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor todoCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView todoList = (ListView)findViewById(R.id.todo_list);

        try {
            SQLiteOpenHelper todoDatabaseHelper = new TodoDatabaseHelper(this);
            db = todoDatabaseHelper.getReadableDatabase();
            todoCursor = db.query("TODO", new String[] {"_id", "TITLE"}, null, null, null, null, null);

            CustomCursorAdapter todoAdapter = new CustomCursorAdapter(this, todoCursor);
//            CursorAdapter todoAdapter = new SimpleCursorAdapter(
//                    MainActivity.this,
//                    android.R.layout.simple_list_item_1,
//                    todoCursor,
//                    new String[]{"TITLE"},
//                    new int[] {android.R.id.text1}, 0);

            todoList.setAdapter(todoAdapter);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DisplayTodoActivity.class);
                intent.putExtra(DisplayTodoActivity.TODO_NO, (int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_todo:
//                Code to run when the Create Order item is clicked
                Intent intent = new Intent(this, CreateTodoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                //Code to run when the settings item is clicked
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
}
