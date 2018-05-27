package com.example.mid_term_exam.QAViewPage;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mid_term_exam.R;
import com.example.mid_term_exam.myclass.HttpCallBack;
import com.example.mid_term_exam.myclass.HttpConnect;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lenovo on 2018/5/26.
 */

public class OtherFragment extends android.support.v4.app.Fragment {

    List<Question> questions = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    int i=2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.qa_other_fragment,container,false);

        swipeRefreshLayout= view.findViewById(R.id.allswipe);
        recyclerView = view.findViewById(R.id.allrecycle);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        //初始化加载
        HashMap<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("size","6");
        map.put("kind","其他");
        HttpConnect.postHttp("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList", map, new HttpCallBack() {
            @Override
            public void onFinish(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);

                    if(jsonObject.getInt("status")==200){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject a = jsonArray.getJSONObject(i);
                            Question question = new Question();
                            question.setTitle(a.getString("title"));
                            question.setDescription(a.getString("description"));
                            question.setKind(a.getString("kind"));
                            question.setId(a.getInt("id"));
                            question.setTags(a.getString("tags"));
                            question.setReward(a.getInt("reward"));
                            question.setDisappear_at(a.getString("disappear_at"));
                            question.setNickname(a.getString("nickname"));
                            questions.add(question);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MyAdapter myAdapter = new MyAdapter(questions);
                                recyclerView.setAdapter(myAdapter);
                            }
                        });
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

        myAdapter = new MyAdapter(questions);
        recyclerView.setAdapter(myAdapter);

        //下拉更新
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //更新的数据
                questions.clear();
                HashMap<String,String> map = new HashMap<>();
                map.put("page","1");
                map.put("size","6");
                map.put("kind","其他");
                HttpConnect.postHttp("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList", map, new HttpCallBack() {
                    @Override
                    public void onFinish(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);

                            if(jsonObject.getInt("status")==200){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject a = jsonArray.getJSONObject(i);
                                    Question question = new Question();
                                    question.setTitle(a.getString("title"));
                                    question.setDescription(a.getString("description"));
                                    question.setKind(a.getString("kind"));
                                    question.setTags(a.getString("tags"));
                                    question.setReward(a.getInt("reward"));
                                    question.setId(a.getInt("id"));
                                    question.setDisappear_at(a.getString("disappear_at"));
                                    question.setNickname(a.getString("nickname"));
                                    questions.add(question);
                                }
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        recyclerView.getAdapter().notifyDataSetChanged();
                                        i=2;
                                        swipeRefreshLayout.setRefreshing(false);
                                    }
                                });
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

//                myAdapter = new MyAdapter(questions);
//                recyclerView.setAdapter(myAdapter);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });


        //下滑到底部加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView1, int newState) {
                super.onScrollStateChanged(recyclerView1, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
                    if(lastVisiblePosition >= linearLayoutManager.getItemCount() - 1){

                        HashMap<String,String> map = new HashMap<>();
                        map.put("page",i+"");
                        map.put("size","6");
                        map.put("kind","其他");

                        HttpConnect.postHttp("https://wx.idsbllp.cn/springtest/cyxbsMobile/index.php/QA/Question/getQuestionList", map, new HttpCallBack() {
                            @Override
                            public void onFinish(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);

                                    if(jsonObject.getInt("status")==200){
                                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                                        for(int i=0;i<jsonArray.length();i++){
                                            JSONObject a = jsonArray.getJSONObject(i);
                                            Question question = new Question();
                                            question.setTitle(a.getString("title"));
                                            question.setDescription(a.getString("description"));
                                            question.setKind(a.getString("kind"));
                                            question.setTags(a.getString("tags"));
                                            question.setId(a.getInt("id"));
                                            question.setReward(a.getInt("reward"));
                                            question.setDisappear_at(a.getString("disappear_at"));
                                            question.setNickname(a.getString("nickname"));
                                            questions.add(question);
                                        }
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.d("刷新","成功");
                                                i++;
                                                //数据增加
                                                recyclerView.getAdapter().notifyItemRangeChanged((i-1)*6,6);

                                                Log.d("刷新","在次成功");
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
            }
        });

        return view;
    }

}
