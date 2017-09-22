package com.bawei.lvwenjing.daynews.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitleFragmentBean;
import com.bawei.lvwenjing.daynews.bean.TuiJianBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class TuiJianListViewAdapter extends BaseAdapter {
    Context context;
    List<TuiJianBean.DataBean> list;

    public TuiJianListViewAdapter(Context context, List<TuiJianBean.DataBean> list) {
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


        final ViewHolder mHolder;
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, R.layout.list_news_item, null);
            mHolder = new ViewHolder();
            mHolder.item_title = (TextView) view.findViewById(R.id.item_title);
            mHolder.item_lable = (TextView) view.findViewById(R.id.item_lable);
            mHolder.item_media_name = (TextView) view.findViewById(R.id.item_media_name);
            mHolder.item_comment_count = (TextView) view.findViewById(R.id.item_comment_count);
            mHolder.right_image = (ImageView) view.findViewById(R.id.item_middle_image);
            mHolder.item_image_0 = (ImageView) view.findViewById(R.id.item_image01);
            mHolder.item_image_1 = (ImageView) view.findViewById(R.id.item_image02);
            mHolder.item_image_2 = (ImageView) view.findViewById(R.id.item_image03);
            mHolder.item_image_layout =(LinearLayout) view.findViewById(R.id.item_image_layout);
            mHolder.textViewDel = (TextView) view.findViewById(R.id.del_id);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        //获取position对应的数据
        TuiJianBean.DataBean news = (TuiJianBean.DataBean) getItem(position);


        mHolder.item_title.setText(news.getTitle()+"");
        mHolder.item_lable.setText(news.getSource());
        mHolder.item_comment_count.setText("评论" + news.getComment_count());
        List<TuiJianBean.DataBean.ImageBean> imgUrlList = (List<TuiJianBean.DataBean.ImageBean>) news.getImage_list();
        //List<TuijianBean1.DataBean.ImageBean> imgUrlList = news.getImage_list();
        mHolder.item_comment_count.setVisibility(View.VISIBLE);

        if(imgUrlList !=null && imgUrlList.size() !=0){
            if(imgUrlList.size() == 3){
                mHolder.right_image.setVisibility(View.GONE);
                mHolder.item_image_layout.setVisibility(View.VISIBLE);

//1                final String tag1 = (String) mHolder.item_image_0.getTag(R.id.imageloader_uri);
// 1               final String tag2 = (String) mHolder.item_image_1.getTag(R.id.imageloader_uri);
// 1               final String tag3 = (String) mHolder.item_image_2.getTag(R.id.imageloader_uri);


//                loadImage(imgUrlList.get(0).getUrl(),mHolder.item_image_0,tag1);
//                loadImage(imgUrlList.get(1).getUrl(),mHolder.item_image_1,tag2);
//                loadImage(imgUrlList.get(2).getUrl(),mHolder.item_image_2,tag3);
                loadImage(imgUrlList.get(0).getUrl(),mHolder.item_image_0,"");
                loadImage(imgUrlList.get(1).getUrl(),mHolder.item_image_1,"");
                loadImage(imgUrlList.get(2).getUrl(),mHolder.item_image_2,"");

            }
        }else if (list.get(position).getMiddle_image()!=null){
            mHolder.right_image.setVisibility(View.VISIBLE);
            mHolder.item_image_layout.setVisibility(View.GONE);
        //    final String tag = (String) mHolder.right_image.getTag(R.id.imageloader_uri);

           // loadImage(list.get(position).getMiddle_image().getUrl(),mHolder.right_image,tag);

           loadImage(list.get(position).getDisplay_url(),mHolder.right_image,"");

        }
        mHolder.textViewDel.setVisibility(View.VISIBLE);
        mHolder.textViewDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                View view1 = LayoutInflater.from(context).inflate(R.layout.pop,null,false);
                PopupWindow popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
//				popupWindow.showAsDropDown(mHolder.textViewDel);
                popupWindow.showAtLocation(mHolder.textViewDel, Gravity.LEFT,100,0);
            }
        });
        return view;
//        ViewHolder1  holder1;
//        if(convertView==null){
//            holder1=new ViewHolder1();
//            convertView=View.inflate(context, R.layout.tuijianfragment_listview_adapter1,null);
//            holder1.title= (TextView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_title);
//            holder1.from= (TextView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_from);
//            holder1.iv= (ImageView) convertView.findViewById(R.id.tuijianfragment_listivew_adapter_image);
//            convertView.setTag(holder1);
//        }
//        else{
//            holder1= (ViewHolder1) convertView.getTag();
//        }
//        holder1.title.setText(list.get(position).getTitle());
//        holder1.from.setText(list.get(position).getSource());
//        Glide.with(context).load(list.get(position).getMiddle_image().getUrl()).into(holder1.iv);
//        return convertView;
    }
    class ViewHolder{
//        TextView title;
//        TextView from;
//        ImageView iv;
//title
TextView item_title;
        //新闻源
        TextView item_media_name;
        //类似推广之类的标签
        TextView item_lable;
        //评论数量
        TextView item_comment_count;
        //右边图片
        ImageView right_image;
        //3张图片布局
        LinearLayout item_image_layout; //3张图片时候的布局
        ImageView item_image_0;
        ImageView item_image_1;
        ImageView item_image_2;
        //大图的图片的话布局
        ImageView large_image;
        //评论布局
        RelativeLayout comment_layout;
        TextView comment_content;

        TextView textViewDel ;

    }
    private void loadImage(String path,ImageView imageView,String tag){
//		if(!path.equals(tag)){

     //1  imageView.setTag(R.id.imageloader_uri,path);
       Glide.with(context).load(path).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);
      //   Glide.with(context).load(path).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(imageView);

//		}



    }

}
