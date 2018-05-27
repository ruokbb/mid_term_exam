package com.example.mid_term_exam;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mid_term_exam.myclass.BaseActivity;
import com.example.mid_term_exam.myclass.HttpCallBack;
import com.example.mid_term_exam.myclass.HttpConnect;

import org.json.JSONObject;

import java.util.HashMap;

public class login_activity extends BaseActivity {

    HashMap<String,String> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button back = findViewById(R.id.finish);
        Button login = findViewById(R.id.login);
        final EditText username = findViewById(R.id.number);
        final EditText psw = findViewById(R.id.six);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //登陆逻辑
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.put("stuNum",username.getText().toString());
                map.put("idNum",psw.getText().toString());
                HttpConnect.postHttp("https://wx.idsbllp.cn/api/verify", map, new HttpCallBack() {
                    @Override
                    public void onFinish(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getInt("status")==200){
                                 final  JSONObject data = jsonObject.getJSONObject("data");
                                 //存入数据
                                SharedPreferences.Editor editor = getSharedPreferences("login", Context.MODE_PRIVATE).edit();
                                editor.putBoolean("isornot",true);
                                editor.putString("stuNum",data.getString("stuNum"));
                                editor.putString("name",data.getString("name"));
                                editor.putString("college",data.getString("college"));
                                editor.putString("class",data.getString("class"));
                                editor.putString("classNum",data.getString("classNum"));
                                editor.putString("gender",data.getString("gender"));
                                editor.putString("major",data.getString("major"));
                                editor.putString("grade",data.getString("grade"));
                                editor.putString("idNum",data.getString("idNum"));
                                editor.apply();
                                finish();
                            }
                        }catch (Exception e){

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
        });

    }
}
