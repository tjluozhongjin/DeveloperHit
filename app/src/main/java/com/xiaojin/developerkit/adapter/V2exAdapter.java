package com.xiaojin.developerkit.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaojin.developerkit.Model.V2exModel;
import com.xiaojin.developerkit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class V2exAdapter extends BaseAdapter {

    public static String getStrTime(String cc_time) {

        String re_StrTime = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        long lcc_time = Long.valueOf(cc_time);

        re_StrTime = sdf.format(new Date(lcc_time * 1000L));

        return re_StrTime;

    }

    private List<V2exModel> newsList;
    private LayoutInflater mInflater;
    private Context context;
    public V2exAdapter(ArrayList<V2exModel> newsList, Context context){
        this.newsList=newsList;
        this.context=context;
        mInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 创建产品
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V2exAdapter.Holder holder=null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.v2ex_item,null);
            holder=new V2exAdapter.Holder(convertView);
            convertView.setTag(holder);
        }
        holder = (V2exAdapter.Holder) convertView.getTag();

        V2exModel news = newsList.get(position);
        TextView tv_topic_title=holder.tv_topic_title;
        ImageView iv_topic_face=holder.iv_topic_face;

        TextView tv_topic_name=holder.tv_topic_name;
        tv_topic_name.setText(news.getMember().getUsername());
        TextView tv_topic_tips=holder.tv_topic_tips;

        String data = getStrTime(news.getLast_touched());
        //System.out.println(data);
        tv_topic_tips.setText(data);

        TextView tv_topic_comment=holder.tv_topic_comment;
        tv_topic_comment.setText(news.getReplies());
        TextView tv_topic_node=holder.tv_topic_node;
        tv_topic_node.setText(news.getNode().getTitle());

        Drawable icon = convertView.getResources().getDrawable(R.mipmap.ic_comment);
        icon.setBounds(0,0,25,25);
        tv_topic_comment.setCompoundDrawables(icon, null,null,null);

        // 设置标题
        tv_topic_title.setText(news.getTitle());
        // 记得判空，
        // 设置图片
        if (news.getImage()==null){
            iv_topic_face.setVisibility(View.GONE);
        }else {
            iv_topic_face.setVisibility(View.VISIBLE);
            //用Glide根据URL加载图片
            Glide.with(context).load(news.getImage()).into(iv_topic_face);
        }
        return convertView;
    }

    // 产品
    private class Holder {

        ImageView iv_topic_face;
        TextView tv_topic_name;
        TextView tv_topic_tips;
        TextView tv_topic_comment;
        TextView tv_topic_node;
        TextView tv_topic_title;



        public  Holder(View view){

            iv_topic_face= (ImageView) view.findViewById(R.id.iv_topic_face);
            tv_topic_title= (TextView) view.findViewById(R.id.tv_topic_title);

            tv_topic_name= (TextView) view.findViewById(R.id.tv_topic_name);
            tv_topic_tips= (TextView) view.findViewById(R.id.tv_topic_tips);
            tv_topic_comment= (TextView) view.findViewById(R.id.tv_topic_comment);
            tv_topic_node= (TextView) view.findViewById(R.id.tv_topic_node);

        }
    }

}