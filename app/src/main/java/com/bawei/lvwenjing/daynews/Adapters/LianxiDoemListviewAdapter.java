package com.bawei.lvwenjing.daynews.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitle;
import com.bawei.lvwenjing.daynews.bean.TabTitleFragmentBean;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class LianxiDoemListviewAdapter extends BaseAdapter {
    Context context;
    List<TabTitle.DataBeanX.DataBean> list;

    public LianxiDoemListviewAdapter(Context context, List<TabTitle.DataBeanX.DataBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
           ViewHolder1  holder1=null;
        if(convertView==null){
            holder1=new ViewHolder1();
            convertView=View.inflate(context, R.layout.lianxidoem_listview_adapter,null);
            holder1.title= (TextView) convertView.findViewById(R.id.lianxidoem_listview_adapter_name);
            holder1.cb= (CheckBox) convertView.findViewById(R.id.lianxidoem_listview_adapter_cb);
            convertView.setTag(holder1);
        }
        else{
            holder1= (ViewHolder1) convertView.getTag();

        }
        holder1.title.setText(list.get(position).getName());


        if(list.get(position).getDefault_add()==1){

            holder1.cb.setChecked(true);
        }
        else{

            holder1.cb.setChecked(false);
        }

        final ViewHolder1 finalHolder = holder1;
        holder1.cb.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(finalHolder.cb.isChecked()){
                    list.get(position).setDefault_add(1);
                }
                else{
                    list.get(position).setDefault_add(0);
                }
            }
        });


        return convertView;
    }
    class ViewHolder1{
        TextView title;
     CheckBox cb;

    }
}
