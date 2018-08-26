package com.regeorge.weathertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.regeorge.weathertest.adapter.MyPagerAdapter;
import com.regeorge.weathertest.database.QuestionDB;
import com.regeorge.weathertest.database.SQLdm;

/**
 * Created by reGeorge on 2018/8/25.
 */

public class DetailActivity extends AppCompatActivity{
    private ViewPager vp;
    private SQLiteDatabase select_question;
    private int postion;
    private Cursor cursor;
    private MyPagerAdapter myPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_detail);
        Log.d("DetailActivity", "onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVp();
    }

    private void setVp() {
        //TODO 获取postion并查到数据库中的值传给adpter，在Adapter中设置正确的text
        postion = this.getIntent().getIntExtra(QuestionDB.ID,0);
        select_question = new SQLdm().openDatabase(this);
        cursor = select_question.query(QuestionDB.TABLE_NAME,null,null,null,null,null,null);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_detail,null);
        vp = (ViewPager) view.findViewById(R.id.vp_detail);
        myPagerAdapter = new MyPagerAdapter(this,cursor);
        vp.setAdapter(myPagerAdapter);
        Log.d("DetailActivity.postion",postion+"");
        vp.setCurrentItem(postion);
    }
}
