package com.xiaojin.developerkit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiaojin.developerkit.Model.ZhuHuModel;
import com.xiaojin.developerkit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/26.
 */
public class ZhiHuAdapter extends BaseAdapter {

    private List<ZhuHuModel.Question> newsList;

    private LayoutInflater mInflater;

    private Context context;

    public ZhiHuAdapter(ArrayList<ZhuHuModel.Question> newsList, Context context){
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
        ZhiHuAdapter.Holder holder=null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.zhihu_item,null);
            holder=new ZhiHuAdapter.Holder(convertView);
            convertView.setTag(holder);
        }
        holder = (ZhiHuAdapter.Holder) convertView.getTag();

        ZhuHuModel.Question news = newsList.get(position);

        TextView item_title=holder.item_title;
        ImageView item_img=holder.item_img;
        TextView item_info=holder.item_info;

        // 设置标题
        item_title.setText(news.getTitle());
        item_info.setText(news.getUpvotes()+"人赞 · " + news.getComment_count()+"条评论 · " + news.getUser().getName());
        // 记得判空，
        // 设置图片
        if (news.getImage()==null){
//            ivnews.setVisibility(View.GONE);
//            ivnews.setImageResource(R.mipmap.ic_launcher);

        }else {
            item_img.setVisibility(View.VISIBLE);
            //用Glide根据URL加载图片
            Glide.with(context).load(news.getImage()).into(item_img);
        }
        return convertView;
    }

    // 产品
    private class Holder {

        ImageView item_img;

        TextView item_title;
        TextView item_info;

        public  Holder(View view){
            item_img= (ImageView) view.findViewById(R.id.item_img);
            item_title= (TextView) view.findViewById(R.id.item_title);
            item_info= (TextView) view.findViewById(R.id.item_info);
        }
    }

}
