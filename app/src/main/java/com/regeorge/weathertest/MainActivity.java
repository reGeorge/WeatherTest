package com.regeorge.weathertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.regeorge.weathertest.adapter.ListViewAdapter;
import com.regeorge.weathertest.database.QuestionDB;
import com.regeorge.weathertest.database.SQLdm;


public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list_question);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

    public void selectDB(){
        SQLiteDatabase select_question = new SQLdm().openDatabase(this);
        Cursor cursor = select_question.query(QuestionDB.TABLE_NAME,null,null,null,null,null,null);
        Log.d("MainActivity", "this: "+this.toString());
        lv.setAdapter(new ListViewAdapter(this,cursor));
//        cursor.moveToPosition(2);
//        Log.d("cursor.content2 :",cursor.getString(cursor.getColumnIndex(QuestionDB.QUESTION)));
    }
}
