package com.example.mid_term_exam.myclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Calendar;

/**
 * Created by lenovo on 2018/5/27.
 */
public class DateTimepickerDialog extends AlertDialog {
    private DateTimepicker mDateTimePicker;
    private Calendar mDate = Calendar.getInstance();
    private int Year,Month,Day,Hour,Minute,Second;
    private OnDateTimeSetListener mOnDateTimeSetListener;
    public DateTimepickerDialog(Context context, long date) {
        super(context);
        mDateTimePicker = new DateTimepicker(context);
        setView(mDateTimePicker);//装载刚才建立的布局，把定义好的日期时间布局显示在这个自定义对话框上
        /*
         *实现<span style="font-family:Arial, Helvetica, sans-serif;">DateTimepicker里的</span><span style="font-family:Arial, Helvetica, sans-serif;">接口</span>
         */
        mDateTimePicker.setOnDateTimeChangedListener(new DateTimepicker.OnDateTimeChangedListener() {
            public void onDateTimeChanged(DateTimepicker view,
                                          int year, int month, int day, int hour, int minute, int second) {
                Year = year;
                Month = month;
                Day = day;
                Hour = hour;
                Minute = minute;
                Second = second;
            }
        });
        setTitle("请设置日期和时间");
        Year = mDate.get(Calendar.YEAR);
        Month = mDate.get(Calendar.MONTH)+1;
        Day = mDate.get(Calendar.DAY_OF_MONTH);
        Hour = mDate.get(Calendar.HOUR_OF_DAY);
        Minute = mDate.get(Calendar.MINUTE);
        Second = mDate.get(Calendar.SECOND);
        setButton(DialogInterface.BUTTON_POSITIVE,"确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                if (mOnDateTimeSetListener != null) {
                    mOnDateTimeSetListener.OnDateTimeSet(dialog,Year,Month,Day,Hour,Minute);
                }
            }
        });
        setButton(DialogInterface.BUTTON_NEGATIVE,"取消", (OnClickListener) null);
        setCanceledOnTouchOutside(false);//点击对话框外无法关闭对话框
    }
    /*
     *
     *接口回调
     */
    public interface OnDateTimeSetListener {
        void OnDateTimeSet(DialogInterface dialog, int year,int month,int day,int hour,int minute);
    }


    /*
     *对外公开方法让Activity实现
     */
    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }
}