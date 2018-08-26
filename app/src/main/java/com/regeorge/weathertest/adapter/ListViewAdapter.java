package com.regeorge.weathertest.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.regeorge.weathertest.DetailActivity;
import com.regeorge.weathertest.R;
import com.regeorge.weathertest.database.QuestionDB;


public class ListViewAdapter extends BaseAdapter{

    private TextView text_question;

    private Context context;
    private Cursor cursor;

    public ListViewAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
        Log.d("adapter.constructor","adapter makesuccess");
    }

    @Override
    public int getCount() {
        Log.d("adapter.getCount",cursor.getCount()+"");
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("adapter.getView postion",position+"");
        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,null);
        text_question = (TextView) convertView.findViewById(R.id.item_question);
        cursor.moveToPosition(position);
        String content = cursor.getString(cursor.getColumnIndex(QuestionDB.QUESTION));
        text_question.setText(content);
        Log.d("adapter.getView item"+position+" 内容为",content);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DetailActivity.class);
                Log.d("ListViewAdapter", "onClick: context"+context.toString());
                i.putExtra(QuestionDB.ID,position);
                context.startActivity(i);
            }
        });
        return convertView;
    }
}
