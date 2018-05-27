package com.example.mid_term_exam;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mid_term_exam.QAViewPage.OtherFragment;
import com.example.mid_term_exam.myclass.BaseActivity;
import com.example.mid_term_exam.myclass.DateTimepickerDialog;
import com.example.mid_term_exam.myclass.HttpCallBack;
import com.example.mid_term_exam.myclass.HttpConnect;
import com.example.mid_term_exam.myclass.MyApplication;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class help_activity extends BaseActivity {

    Boolean next = false;
    Context context = this;
    int select = 9,rewardselect=9;
    List<String> tags = new ArrayList<>();
    List<Integer> rewards = new ArrayList<>();
    String mytag=null;
    Boolean can = false;
    int myreward=5;
    Boolean noname = false;
    TextView textView;
    int myYear=0, myMonth, myDay, myHour, myMinute;
    Calendar calendar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        //返回
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //标题
        final EditText title = findViewById(R.id.title);
        final TextView titlecount = findViewById(R.id.titlecount);
        final int max = 20;
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() <= max) {
                    titlecount.setText(max - editable.length() + "");
                    next = true;
                } else {
                    next = false;
                    titlecount.setText(editable.length() + "");
                    titlecount.setTextColor(Color.parseColor("#FF0000"));
                }

            }
        });

        //描述
        final EditText description = findViewById(R.id.description);
        final TextView count = findViewById(R.id.descriptioncount);
        final int maxnum = 300;
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() <= maxnum) {
                    count.setText(maxnum - editable.length() + "");
                    next = true;
                } else {
                    count.setText(editable.length() + "");
                    count.setTextColor(Color.parseColor("#FF0000"));
                }
            }
        });


        //添加话题
        final ImageView tag = findViewById(R.id.tag);
        tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tags.add("大物");
                tags.add("英语");
                tags.add("线代");
                tags.add("高数");
                tags.add("几何");
                tags.add("思修");

                //弹出底部dialog
                final LayoutInflater factory = LayoutInflater.from(context);
                View myView = factory.inflate(R.layout.tagselect, null);
                final Dialog dialog = new AlertDialog.Builder(context)
                        .setView(myView).create();
                dialog.show();
                //设置点击Dialog外部任意区域关闭Dialog
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);


                //选择话题
                final EditText othertag = myView.findViewById(R.id.othertag);
                final Button a1, a2, a3, a4, a5, a0;
                a0 = myView.findViewById(R.id.dawu);
                a1 = myView.findViewById(R.id.yingyu);
                a2 = myView.findViewById(R.id.xiandai);
                a3 = myView.findViewById(R.id.gaoshu);
                a4 = myView.findViewById(R.id.jihe);
                a5 = myView.findViewById(R.id.sixiu);
                final List<Button> buttons = new ArrayList<>();
                buttons.add(a0);
                buttons.add(a1);
                buttons.add(a2);
                buttons.add(a3);
                buttons.add(a4);
                buttons.add(a5);

                //再次打开
                if(mytag==null){

                }else if (mytag.equals("大物")){
                    a0.setBackgroundResource(R.drawable.whilecircle);
                    a0.setTextColor(Color.parseColor("#ffffff"));
                    select = 0;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag.equals("英语")){
                    a1.setBackgroundResource(R.drawable.whilecircle);
                    a1.setTextColor(Color.parseColor("#ffffff"));
                    select = 1;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag.equals("线代")){
                    a2.setBackgroundResource(R.drawable.whilecircle);
                    a2.setTextColor(Color.parseColor("#ffffff"));
                    select = 2;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag.equals("高数")){
                    a3.setBackgroundResource(R.drawable.whilecircle);
                    a3.setTextColor(Color.parseColor("#ffffff"));
                    select = 3;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag.equals("几何")){
                    a4.setBackgroundResource(R.drawable.whilecircle);
                    a4.setTextColor(Color.parseColor("#ffffff"));
                    select = 4;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag.equals("思修")){
                    a5.setBackgroundResource(R.drawable.whilecircle);
                    a5.setTextColor(Color.parseColor("#ffffff"));
                    select = 5;
                    //设置edittext,不可编辑
                    othertag.setText("#" + tags.get(select) + "#");
                    othertag.setTextColor(Color.parseColor("#7195fa"));
                    othertag.setFocusable(false);
                    othertag.setFocusableInTouchMode(false);
                }else if(mytag!=null){
                    othertag.setText(mytag);
                }

                a0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (select == 0) {
                            a0.setBackgroundResource(R.drawable.circlebutton);
                            a0.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a0.setBackgroundResource(R.drawable.whilecircle);
                            a0.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 0;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a0.setBackgroundResource(R.drawable.whilecircle);
                            a0.setTextColor(Color.parseColor("#ffffff"));
                            select = 0;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }
                    }
                });

                a1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (select == 1) {
                            a1.setBackgroundResource(R.drawable.circlebutton);
                            a1.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a1.setBackgroundResource(R.drawable.whilecircle);
                            a1.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 1;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a1.setBackgroundResource(R.drawable.whilecircle);
                            a1.setTextColor(Color.parseColor("#ffffff"));
                            select = 1;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }

                    }
                });

                a2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (select == 2) {
                            a2.setBackgroundResource(R.drawable.circlebutton);
                            a2.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a2.setBackgroundResource(R.drawable.whilecircle);
                            a2.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 2;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a2.setBackgroundResource(R.drawable.whilecircle);
                            a2.setTextColor(Color.parseColor("#ffffff"));
                            select = 2;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }
                    }
                });

                a3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (select == 3) {
                            a3.setBackgroundResource(R.drawable.circlebutton);
                            a3.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a3.setBackgroundResource(R.drawable.whilecircle);
                            a3.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 3;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a3.setBackgroundResource(R.drawable.whilecircle);
                            a3.setTextColor(Color.parseColor("#ffffff"));
                            select = 3;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }

                    }
                });

                a4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (select == 4) {
                            a4.setBackgroundResource(R.drawable.circlebutton);
                            a4.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a4.setBackgroundResource(R.drawable.whilecircle);
                            a4.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 4;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a4.setBackgroundResource(R.drawable.whilecircle);
                            a4.setTextColor(Color.parseColor("#ffffff"));
                            select = 4;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }

                    }
                });

                a5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (select == 5) {
                            a5.setBackgroundResource(R.drawable.circlebutton);
                            a5.setTextColor(Color.parseColor("#7195fa"));
                            //取消edittext
                            othertag.setFocusable(true);
                            othertag.setFocusableInTouchMode(true);
                            othertag.requestFocus();
                            othertag.setText("");
                            select = 9;
                        } else if (select != 9) {
                            a5.setBackgroundResource(R.drawable.whilecircle);
                            a5.setTextColor(Color.parseColor("#ffffff"));
                            buttons.get(select).setTextColor(Color.parseColor("#7195fa"));
                            buttons.get(select).setBackgroundResource(R.drawable.circlebutton);
                            select = 5;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        } else if (select == 9) {
                            a5.setBackgroundResource(R.drawable.whilecircle);
                            a5.setTextColor(Color.parseColor("#ffffff"));
                            select = 5;
                            //设置edittext,不可编辑
                            othertag.setText("#" + tags.get(select) + "#");
                            othertag.setTextColor(Color.parseColor("#7195fa"));
                            othertag.setFocusable(false);
                            othertag.setFocusableInTouchMode(false);
                        }

                    }
                });
                //确定
                TextView next = myView.findViewById(R.id.yes);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (select != 9) {
                            mytag = tags.get(select);
                            select = 9;
                            dialog.dismiss();
                        } else if (othertag.getText().toString() != null) {
                            mytag = othertag.getText().toString();
                            select = 9;
                            dialog.dismiss();
                        }
                    }
                });

            }
        });

        //是否匿名
        final Button button = findViewById(R.id.noname);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noname) {
                    button.setBackgroundResource(R.drawable.circlebutton);
                    noname = false;
                } else {
                    button.setBackgroundResource(R.drawable.whilecircle);
                    noname = true;
                }
            }
        });

        //下一步
        final TextView go = findViewById(R.id.next);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //时间和积分
                if (next) {
                    //弹出底部dialog
                    final LayoutInflater factory = LayoutInflater.from(context);
                    final View myView = factory.inflate(R.layout.timeandreward, null);
                    final Dialog dialog = new AlertDialog.Builder(context)
                            .setView(myView).create();
                    dialog.show();
                    //设置点击Dialog外部任意区域关闭Dialog
                    dialog.setCanceledOnTouchOutside(true);
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.BOTTOM);
                    WindowManager.LayoutParams lp = window.getAttributes();
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(lp);

                    //获取并设置当前时间
                    calendar = Calendar.getInstance();
                    calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
                    String year, month, day, hour, minute;
                    year = String.valueOf(calendar.get(Calendar.YEAR));
                    month = String.valueOf(calendar.get(Calendar.MONTH)+1);
                    day = String.valueOf(calendar.get(Calendar.DATE));
                    if (calendar.get(Calendar.AM_PM) == 0)
                        hour = String.valueOf(calendar.get(Calendar.HOUR));
                    else{
                        hour = String.valueOf(calendar.get(Calendar.HOUR) + 12);
                    }
                    minute = String.valueOf(calendar.get(Calendar.MINUTE));


                    //返回
                    TextView back = myView.findViewById(R.id.back);
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    //发布
                    TextView publish = myView.findViewById(R.id.publish);
                    publish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!can||myYear==0){
                                Toast.makeText(context,"请设置时间和积分",Toast.LENGTH_SHORT).show();
                            }else{
                                HashMap<String,String> map = new HashMap<>();
                                SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                                Intent intent = getIntent();
                                List<String> kinds = new ArrayList<>();
                                kinds.add("排位");
                                kinds.add("学习");
                                kinds.add("生活");
                                kinds.add("情感");
                                kinds.add("其他");
                                int i=0;
                                if(noname){
                                    i=1;
                                }
                                //处理时间
                                String year=myYear+"";
                                String month;
                                if(myMonth<10){
                                    month="0"+myMonth;
                                }else{
                                    month=myMonth+"";
                                }
                                String day;
                                if(myDay<10){
                                    day="0"+myDay;
                                }else{
                                    day=myDay+"";
                                }
                                String hour;
                                if(myHour<10){
                                    hour="0"+myHour;
                                }else{
                                    hour=myHour+"";
                                }
                                String minute;
                                if(myMinute<10){
                                    minute="0"+myMinute;
                                }else{
                                    minute=myMinute+"";
                                }


                                map.put("stuNum",sharedPreferences.getString("stuNum",""));
                                map.put("idNum",sharedPreferences.getString("idNum",""));
                                map.put("title",title.getText().toString());
                                map.put("description",description.getText().toString());
                                map.put("is_anonymous",i+"");
                                map.put("kind",kinds.get(intent.getIntExtra("kind",3)));
                                map.put("tags",mytag);
                                map.put("reward",myreward+"");
                                map.put("disappear_time",year+"-"+month+"-"+day+" "+hour+":"+minute+":"+"20");

                                HttpConnect.postHttp("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/add", map, new HttpCallBack() {
                                    @Override
                                    public void onFinish(String response) {
                                        try{
                                            JSONObject jsonObject = new JSONObject(response);
                                            if(jsonObject.getInt("status")==200){
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(context,"求助成功",Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                });
                                            }
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onFinish(Bitmap bitmap) {

                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }
                                });
                            }
                        }
                    });

                    //设置时间
                    final TextView time = myView.findViewById(R.id.time);
                    time.setText(year + "年" + month + "月" + day + "日  " + hour + "时  " + minute + "分");
                    time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            DateTimepickerDialog dateTimepickerDialog = new DateTimepickerDialog(context, System.currentTimeMillis());
                            dateTimepickerDialog.setOnDateTimeSetListener(new DateTimepickerDialog.OnDateTimeSetListener() {
                                @Override
                                public void OnDateTimeSet(DialogInterface dialog, int year, int month, int day, int hour, int minute) {
                                    myYear = year;
                                    myMonth = month;
                                    myDay = day;
                                    myHour = hour;
                                    myMinute = minute;
                                    time.setText(myYear + "年" + myMonth + "月" + myDay + "日  " + myHour + "时  " + myMinute + "分");
                                }
                            });
                            dateTimepickerDialog.show();

                        }
                    });

                    //设置奖励
                    final TextView reward = myView.findViewById(R.id.reward);
                    reward.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            //弹出底部dialog
                            final LayoutInflater factory = LayoutInflater.from(context);
                            View myView = factory.inflate(R.layout.reward_select, null);
                            final Dialog dialog = new AlertDialog.Builder(context)
                                    .setView(myView).create();
                            dialog.show();
                            //设置点击Dialog外部任意区域关闭Dialog
                            dialog.setCanceledOnTouchOutside(true);
                            dialog.show();
                            Window window = dialog.getWindow();
                            window.setGravity(Gravity.BOTTOM);
                            WindowManager.LayoutParams lp = window.getAttributes();
                            lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            window.setAttributes(lp);

                            //取消
                            TextView back = myView.findViewById(R.id.back);
                            back.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });

                            //选择分数
                            final TextView rewardnum = myView.findViewById(R.id.rewardnum);
                            final Button reward1,reward2,reward3,reward5,reward10;
                            reward1 = myView.findViewById(R.id.reward1);
                            reward2 = myView.findViewById(R.id.reward2);
                            reward3 = myView.findViewById(R.id.reward3);
                            reward5 = myView.findViewById(R.id.reward5);
                            reward10 = myView.findViewById(R.id.reward10);
                            final List<Button> buttons = new ArrayList<>();
                            buttons.add(reward1);
                            buttons.add(reward2);
                            buttons.add(reward3);
                            buttons.add(reward5);
                            buttons.add(reward10);
                            rewards.add(1);
                            rewards.add(2);
                            rewards.add(3);
                            rewards.add(5);
                            rewards.add(10);

                            reward1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if(rewardselect==0){
                                        reward1.setBackgroundResource(R.drawable.circlebutton);
                                        reward1.setTextColor(Color.parseColor("#7195fa"));
                                        rewardnum.setText("请选择积分");
                                        rewardselect=9;
                                    }else if(rewardselect!=9){
                                        reward1.setBackgroundResource(R.drawable.whilecircle);
                                        reward1.setTextColor(Color.parseColor("#ffffff"));
                                        buttons.get(rewardselect).setTextColor(Color.parseColor("#7195fa"));
                                        buttons.get(rewardselect).setBackgroundResource(R.drawable.circlebutton);
                                        rewardselect=0;
                                        int  i=rewards.get(rewardselect);
                                        rewardnum.setText(i+"积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }else if(rewardselect==9){
                                        reward1.setBackgroundResource(R.drawable.whilecircle);
                                        reward1.setTextColor(Color.parseColor("#ffffff"));
                                        rewardselect=0;
                                        String i = rewards.get(rewardselect)+"积分";
                                        rewardnum.setText(String.valueOf(i));
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }

                                }
                            });

                            reward2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(rewardselect==1){
                                        reward2.setBackgroundResource(R.drawable.circlebutton);
                                        reward2.setTextColor(Color.parseColor("#7195fa"));
                                        rewardnum.setText("请选择积分");
                                        rewardselect=9;
                                    }else if(rewardselect!=9){
                                        reward2.setBackgroundResource(R.drawable.whilecircle);
                                        reward2.setTextColor(Color.parseColor("#ffffff"));
                                        buttons.get(rewardselect).setTextColor(Color.parseColor("#7195fa"));
                                        buttons.get(rewardselect).setBackgroundResource(R.drawable.circlebutton);
                                        rewardselect=1;
                                        rewardnum.setText(rewards.get(rewardselect)+"积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }else if(rewardselect==9){
                                        reward2.setBackgroundResource(R.drawable.whilecircle);
                                        reward2.setTextColor(Color.parseColor("#ffffff"));
                                        rewardselect=1;
                                        rewardnum.setText(rewards.get(rewardselect) + "积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }
                                }
                            });

                            reward3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(rewardselect==2){
                                        reward3.setBackgroundResource(R.drawable.circlebutton);
                                        reward3.setTextColor(Color.parseColor("#7195fa"));
                                        rewardnum.setText("请选择积分");
                                        rewardselect=9;
                                    }else if(rewardselect!=9){
                                        reward3.setBackgroundResource(R.drawable.whilecircle);
                                        reward3.setTextColor(Color.parseColor("#ffffff"));
                                        buttons.get(rewardselect).setTextColor(Color.parseColor("#7195fa"));
                                        buttons.get(rewardselect).setBackgroundResource(R.drawable.circlebutton);
                                        rewardselect=2;
                                        rewardnum.setText(rewards.get(rewardselect)+"积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }else if(rewardselect==9){
                                        reward3.setBackgroundResource(R.drawable.whilecircle);
                                        reward3.setTextColor(Color.parseColor("#ffffff"));
                                        rewardselect=2;
                                        rewardnum.setText(rewards.get(rewardselect) + "积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }
                                }
                            });

                            reward5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(rewardselect==3){
                                        reward5.setBackgroundResource(R.drawable.circlebutton);
                                        reward5.setTextColor(Color.parseColor("#7195fa"));
                                        rewardnum.setText("请选择积分");
                                        rewardselect=9;
                                    }else if(rewardselect!=9){
                                        reward5.setBackgroundResource(R.drawable.whilecircle);
                                        reward5.setTextColor(Color.parseColor("#ffffff"));
                                        buttons.get(rewardselect).setTextColor(Color.parseColor("#7195fa"));
                                        buttons.get(rewardselect).setBackgroundResource(R.drawable.circlebutton);
                                        rewardselect=3;
                                        rewardnum.setText(rewards.get(rewardselect)+"积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }else if(rewardselect==9){
                                        reward5.setBackgroundResource(R.drawable.whilecircle);
                                        reward5.setTextColor(Color.parseColor("#ffffff"));
                                        rewardselect=3;
                                        rewardnum.setText(rewards.get(rewardselect) + "积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }
                                }
                            });

                            reward10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(rewardselect==4){
                                        reward10.setBackgroundResource(R.drawable.circlebutton);
                                        reward10.setTextColor(Color.parseColor("#7195fa"));
                                        rewardnum.setText("请选择积分");
                                        rewardselect=9;
                                    }else if(rewardselect!=9){
                                        reward10.setBackgroundResource(R.drawable.whilecircle);
                                        reward10.setTextColor(Color.parseColor("#ffffff"));
                                        buttons.get(rewardselect).setTextColor(Color.parseColor("#7195fa"));
                                        buttons.get(rewardselect).setBackgroundResource(R.drawable.circlebutton);
                                        rewardselect=4;
                                        rewardnum.setText(rewards.get(rewardselect)+"积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }else if(rewardselect==9){
                                        reward10.setBackgroundResource(R.drawable.whilecircle);
                                        reward10.setTextColor(Color.parseColor("#ffffff"));
                                        rewardselect=4;
                                        rewardnum.setText(rewards.get(rewardselect) + "积分");
                                        rewardnum.setTextColor(Color.parseColor("#7195fa"));
                                    }
                                }
                            });

                            //确定
                            TextView finish = myView.findViewById(R.id.finish);
                            finish.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(rewardselect!=9) {
                                        myreward = rewards.get(rewardselect);
                                        can=true;
                                        reward.setText(myreward+"积分");
                                        dialog.dismiss();
                                    }else{
                                        Toast.makeText(context,"请选择时间和积分",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });

                }

            }
        });

    }
}
