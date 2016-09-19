package com.example.andrew_fok.todo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrew Fok on 20/09/2016.
 */
public class CustomCursorAdapter extends CursorAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;

    public CustomCursorAdapter (Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_listview, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView item_string = (TextView) view.findViewById(R.id.list_item_string);
        String title = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        item_string.setText(title);
    }
}
