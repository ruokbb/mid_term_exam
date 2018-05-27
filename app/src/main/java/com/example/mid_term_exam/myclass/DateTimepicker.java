package com.example.mid_term_exam.myclass;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.example.mid_term_exam.R;

import java.util.Calendar;

/**
 * Created by lenovo on 2018/5/27.
 */

public class DateTimepicker extends FrameLayout {

    private final NumberPicker mYearSpinner;
    private final NumberPicker mMonthSpinner;
    private final NumberPicker mDaySpinner;
    private final NumberPicker mHourSpinner;
    private final NumberPicker mMinuteSpinner;
    private final NumberPicker mSecondSpinner;
    private Calendar mDate;
    private int mYear, mMonth, mDay, mHour, mMinute, mSecond;
    private OnDateTimeChangedListener mOnDateTimeChangedListener;

    public DateTimepicker(Context context) {
        super(context);
        /*
         *
         */
        mDate = Calendar.getInstance();
        mYear = mDate.get(Calendar.YEAR);
        mMonth = mDate.get(Calendar.MONTH) + 1;
        mHour = mDate.get(Calendar.HOUR_OF_DAY);
        mMinute = mDate.get(Calendar.MINUTE);
        mSecond = mDate.get(Calendar.SECOND);
        /**
         *
         */
        inflate(context, R.layout.time_select, this);
        /**
         *
         */
        mYearSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_year);
        mYearSpinner.setMaxValue(2100);
        mYearSpinner.setMinValue(1900);
        mYearSpinner.setValue(mYear);
        mYearSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);//设置NumberPicker不可编辑
        mYearSpinner.setOnValueChangedListener(mOnYearChangedListener);//注册<span style="font-family:Arial, Helvetica, sans-serif;">NumberPicker值变化时的</span><span style="font-family:Arial, Helvetica, sans-serif;">监听事件</span>

        mMonthSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_month);
        mMonthSpinner.setMaxValue(12);
        mMonthSpinner.setMinValue(1);
        mMonthSpinner.setValue(mMonth);
        mMonthSpinner.setFormatter(formatter);//格式化显示数字，个位数前添加0
        mMonthSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mMonthSpinner.setOnValueChangedListener(mOnMonthChangedListener);

        mDaySpinner = (NumberPicker) this.findViewById(R.id.np_datetime_day);
        judgeMonth();//判断是否闰年，从而设置2月份的天数
        mDay = mDate.get(Calendar.DAY_OF_MONTH);
        mDaySpinner.setValue(mDay);
        mDaySpinner.setFormatter(formatter);
        mDaySpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDaySpinner.setOnValueChangedListener(mOnDayChangedListener);

        mHourSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_hour);
        mHourSpinner.setMaxValue(23);
        mHourSpinner.setMinValue(0);
        mHourSpinner.setValue(mHour);
        mHourSpinner.setFormatter(formatter);
        mHourSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);

        mMinuteSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_minute);
        mMinuteSpinner.setMaxValue(59);
        mMinuteSpinner.setMinValue(0);
        mMinuteSpinner.setValue(mMinute);
        mMinuteSpinner.setFormatter(formatter);
        mMinuteSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mMinuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);

        mSecondSpinner = (NumberPicker) this.findViewById(R.id.np_datetime_second);
        mSecondSpinner.setMaxValue(59);
        mSecondSpinner.setMinValue(0);
        mSecondSpinner.setValue(mSecond);
        mSecondSpinner.setFormatter(formatter);
        mSecondSpinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mSecondSpinner.setOnValueChangedListener(mOnSecondChangedListener);
    }

    /**
     *
     *
     */
    private NumberPicker.OnValueChangeListener mOnYearChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mYear = mYearSpinner.getValue();
            judgeYear();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnMonthChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMonth = mMonthSpinner.getValue();
            judgeMonth();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnDayChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDay = mDaySpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnHourChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mHour = mHourSpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMinute = mMinuteSpinner.getValue();
            onDateTimeChanged();
        }
    };

    private NumberPicker.OnValueChangeListener mOnSecondChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mSecond = mSecondSpinner.getValue();
            onDateTimeChanged();
        }
    };
    //数字格式化，<10的数字前自动加0
    private NumberPicker.Formatter formatter = new NumberPicker.Formatter(){
        @Override
        public String format(int value) {
            String Str = String.valueOf(value);
            if (value < 10) {
                Str = "0" + Str;
            }
            return Str;
        }
    };

    /*
     *接口回调 参数是当前的View 年月日时分秒
     */
    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimepicker view, int year, int month, int day, int hour, int minute, int second);
    }
    /*
     *对外的公开方法
     */
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }

    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            mOnDateTimeChangedListener.onDateTimeChanged(this, mYear, mMonth, mDay, mHour, mMinute, mSecond);
        }
    }

    private void judgeYear(){
        if(mMonth == 2)
        {
            if(mYear%4 == 0)
            {
                if(mDaySpinner.getMaxValue() != 29)
                {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(29);
                }
            }
            else
            {
                if(mDaySpinner.getMaxValue() != 28)
                {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(28);
                }
            }
        }
        mDay = mDaySpinner.getValue();
    }

    private void judgeMonth() {
        if(mMonth == 2)
        {
            if(mYear%4 == 0)
            {
                if(mDaySpinner.getMaxValue() != 29)
                {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(29);
                }
            }
            else
            {
                if(mDaySpinner.getMaxValue() != 28)
                {
                    mDaySpinner.setDisplayedValues(null);
                    mDaySpinner.setMinValue(1);
                    mDaySpinner.setMaxValue(28);
                }
            }
        }
        else
        {
            switch(mMonth){
                case 4:
                case 6:
                case 9:
                case 11:
                    if(mDaySpinner.getMaxValue() != 30)
                    {
                        mDaySpinner.setDisplayedValues(null);
                        mDaySpinner.setMinValue(1);
                        mDaySpinner.setMaxValue(30);
                    }
                    break;
                default:
                    if(mDaySpinner.getMaxValue() != 31)
                    {
                        mDaySpinner.setDisplayedValues(null);
                        mDaySpinner.setMinValue(1);
                        mDaySpinner.setMaxValue(31);
                    }
            }
        }
        mDay = mDaySpinner.getValue();

    }

}