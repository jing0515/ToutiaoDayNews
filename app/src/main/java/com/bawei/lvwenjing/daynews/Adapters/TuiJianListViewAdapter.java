package com.bawei.lvwenjing.daynews.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitleFragmentBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TuiJianListViewAdapter extends BaseAdapter {
    Context context;
    List<TabTitleFragmentBean.DataBean> list;

    public TuiJianListViewAdapter(Context context, List<TabTitleFragmentBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder1  holder1;
        if(convertView==null){
            holder1=new ViewHolder1();
            convertView=View.inflate(context, R.layout.tuijianfragment_listview_adapter1,null);
            holder1.title= (TextView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_title);
            holder1.from= (TextView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_from);
            holder1.iv= (ImageView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_image);
            convertView.setTag(holder1);
        }
        else{
            holder1= (ViewHolder1) convertView.getTag();
        }
        holder1.title.setText(list.get(position).getTitle());
        holder1.from.setText(list.get(position).getSource());
        Glide.with(context).load(list.get(position).getMiddle_image().getUrl()).into(holder1.iv);


        return convertView;
    }
    class ViewHolder1{
        TextView title;
        TextView from;
        ImageView iv;

    }
}
