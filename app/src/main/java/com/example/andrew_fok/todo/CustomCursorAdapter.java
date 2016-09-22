package com.example.andrew_fok.todo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView item_string = (TextView) view.findViewById(R.id.list_item_string);
        Button share = (Button) view.findViewById(R.id.share_btn);
        Button delete = (Button) view.findViewById(R.id.delete_btn);
        final int pos = cursor.getPosition();
        final String title = cursor.getString(cursor.getColumnIndexOrThrow("TITLE"));
        item_string.setText(title);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"current Pos is "+pos+" "+title,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
