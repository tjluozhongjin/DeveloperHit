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
import com.xiaojin.developerkit.Model.GankModel;
import com.xiaojin.developerkit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luozhongjin on 04/11/2017.
 */

public class GankAdapter extends BaseAdapter {

    private List<GankModel.Result> newsList;

    private LayoutInflater mInflater;

    private Context context;

    public GankAdapter(ArrayList<GankModel.Result> newsList, Context context){
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
        GankAdapter.Holder holder=null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.gank_item,null);
            holder=new GankAdapter.Holder(convertView);
            convertView.setTag(holder);
        }
        holder = (GankAdapter.Holder) convertView.getTag();

        GankModel.Result news = newsList.get(position);

        ImageView iv_tech_icon=holder.iv_tech_icon;

        TextView tv_tech_title=holder.tv_tech_title;
        tv_tech_title.setText(news.getTitle());


        TextView tv_tech_author = holder.tv_tech_author;
        Drawable tv_tech_author_icon = convertView.getResources().getDrawable(R.mipmap.ic_author);
        tv_tech_author_icon.setBounds(0,0,48,40);
        tv_tech_author.setCompoundDrawables(tv_tech_author_icon,null,null,null);
        tv_tech_author.setText(news.getWho());

        TextView tv_tech_time=holder.tv_tech_time;
        Drawable tv_tech_time_icon = convertView.getResources().getDrawable(R.mipmap.ic_time);
        tv_tech_time_icon.setBounds(0,0,44,44);
        tv_tech_time.setCompoundDrawables(tv_tech_time_icon,null,null,null);
        tv_tech_time.setText(news.getPublishedAt().substring(0,10));


        if(news.getType().equals("Android")){
            iv_tech_icon.setImageResource(R.mipmap.ic_android);
        }else if (news.getType().equals("iOS")){
            iv_tech_icon.setImageResource(R.mipmap.ic_ios);
        }else if (news.getType().equals("web")){
            iv_tech_icon.setImageResource(R.mipmap.ic_web);
        }else{
            iv_tech_icon.setImageResource(R.mipmap.ic_web);
        }

//        TextView common_tag = holder.common_tag;
//        common_tag.setText("Android");



//        TextView common_via = holder.common_via;
//
//        common_via.setText("via: xiaojin");

//        ImageView common_label = holder.common_label;

//        common_label.setImageResource(R.mipmap.ic_launcher);

        // 设置标题
        // 记得判空，
        // 设置图片
//        if (news.getImage()==null){
//            ic_android.setVisibility(View.GONE);
////              ic_android.setImageResource(R.mipmap.ic_launcher);
////            Glide.with(context).load("https://picsum.photos/300/200/?image=" + (position+471)).into(ivnews);
//        } else if(news.getImage()!=null){
//            ic_android.setVisibility(View.VISIBLE);
//            //用Glide根据URL加载图片
//            ic_android.setVisibility(View.GONE);
////            Glide.with(context).load(news.getImage()).into(ic_android);
//        }
        return convertView;
    }

    // 产品
    private class Holder {


        ImageView iv_tech_icon;

        TextView tv_tech_title;

        TextView tv_tech_author;
        TextView tv_tech_time;
//
//        ImageView common_label;


        public  Holder(View view){
            iv_tech_icon= (ImageView) view.findViewById(R.id.iv_tech_icon);
            tv_tech_title= (TextView) view.findViewById(R.id.tv_tech_title);
            tv_tech_author = (TextView)view.findViewById(R.id.tv_tech_author);
//            tv_tech_time= (ImageView) view.findViewById(R.id.tv_tech_time);
            tv_tech_time = (TextView)view.findViewById(R.id.tv_tech_time);
        }
    }

}