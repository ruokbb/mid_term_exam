package com.example.mid_term_exam.QAViewPage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mid_term_exam.MainLayoutActivity;
import com.example.mid_term_exam.R;
import com.example.mid_term_exam.RoundImageView;
import com.example.mid_term_exam.myclass.BaseActivity;
import com.example.mid_term_exam.myclass.HttpCallBack;
import com.example.mid_term_exam.myclass.HttpConnect;
import com.example.mid_term_exam.myclass.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/5/26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

     private List<Question> questions = new ArrayList<>();

    public MyAdapter(List<Question> labels){
       this.questions=labels;
    }

    static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,name,time,reward,tag;
        ImageView nickp;
        ImageView gender;

        public ViewHolder(View view){
            super(view);

            title=view.findViewById(R.id.title);
            description=view.findViewById(R.id.description);
            name=view.findViewById(R.id.name);
            time=view.findViewById(R.id.time);
            reward=view.findViewById(R.id.reward);
            tag=view.findViewById(R.id.tag);
            nickp = view.findViewById(R.id.nickp);
            gender = view.findViewById(R.id.gender);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        final Question question = questions.get(position);
        holder.title.setText(question.getTitle());
        holder.description.setText(question.getDescription());
        holder.name.setText(question.getNickname());
        holder.time.setText(question.getDisappear_at()+"消失");
        holder.reward.setText(question.getReward()+"积分");
        holder.tag.setText("#"+question.getTags()+"#");
        if(question.getGender().equals("男")){
            Log.d("性别","男性");
            holder.gender.setImageResource(R.mipmap.boy);
        }else if(question.getGender().equals("女")){
            Log.d("性别","女性");
            holder.gender.setImageResource(R.mipmap.girl);
        }


    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


}

