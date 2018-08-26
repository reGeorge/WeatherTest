package com.regeorge.weathertest.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.regeorge.weathertest.R;
import com.regeorge.weathertest.database.QuestionDB;

/**
 * Created by reGeorge on 2018/8/25.
 */

public class MyPagerAdapter extends PagerAdapter {

    private Context mContext;
    private Cursor mCursor;

    public MyPagerAdapter(Context context,Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    @Override
    public int getCount() {
        Log.d("pageradapter.getcount count",mCursor.getCount()+"");
        return mCursor.getCount();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager,null);
        bindData(view,position);
        container.addView(view);
        Log.d("pageradapter.instantiateItem view" ,view.toString());
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        Log.d("pageradapter.isViewFromObject " ,(view == object)+"");
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    private void bindData(View view, int position) {
        TextView tv_question = (TextView) view.findViewById(R.id.vp_tv_question);
        TextView tv_answer = (TextView) view.findViewById(R.id.vp_tv_answer);
        RadioButton rb_A = (RadioButton) view.findViewById(R.id.vp_rb_A);
        RadioButton rb_B = (RadioButton) view.findViewById(R.id.vp_rb_B);
        RadioButton rb_C = (RadioButton) view.findViewById(R.id.vp_rb_C);
        RadioButton rb_D = (RadioButton) view.findViewById(R.id.vp_rb_D);
        mCursor.moveToPosition(position);
        String str_question = mCursor.getString(mCursor.getColumnIndex(QuestionDB.QUESTION));
        String str_answer_a = mCursor.getString(mCursor.getColumnIndex(QuestionDB.ANSWER_A));
        String str_answer_b = mCursor.getString(mCursor.getColumnIndex(QuestionDB.ANSWER_B));
        String str_answer_c = mCursor.getString(mCursor.getColumnIndex(QuestionDB.ANSWER_C));
        String str_answer_d = mCursor.getString(mCursor.getColumnIndex(QuestionDB.ANSWER_D));
        String str_answer_correct = mCursor.getString(mCursor.getColumnIndex(QuestionDB.ANSWER_CORRECT));
        tv_question.setText(str_question);
        tv_answer.setText(str_answer_correct);
        rb_A.setText(str_answer_a);
        rb_B.setText(str_answer_b);
        rb_C.setText(str_answer_c);
        rb_D.setText(str_answer_d);
    }
}
