package com.example.mid_term_exam.myclass;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 2018/5/26.
 */

public class HttpConnect {

    public static void getHttp(final String address,final HttpCallBack callBack){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try{

                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }

                    if(callBack!=null){
                        //回调onFinish
                        callBack.onFinish(response.toString());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack!=null){
                        //回调onError
                        callBack.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }

    public static void postHttp(final String address, final HashMap<String, String> data, final HttpCallBack callBack){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try{

                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    //传值
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    for (String key:data.keySet()){
                        out.write(key + "=" + data.get(key) + "&");
                    }
                    out.flush();
                    out.close();

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }

                    if(callBack!=null){
                        //回调onFinish
                        callBack.onFinish(response.toString());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack!=null){
                        //回调onError
                        callBack.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }

            }
        }).start();

    }

    public static void getBitmap(final String address,final HttpCallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap;
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection=(HttpURLConnection) url.openConnection();
                    //connection.connect();
                    connection.setRequestMethod("GET");
                    InputStream inputStream=connection.getInputStream();

                    bitmap= BitmapFactory.decodeStream(inputStream);

                    if(callBack!=null){
                        callBack.onFinish(bitmap);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    if(callBack!=null){
                        //回调onError
                        callBack.onError(e);
                    }
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }


}
