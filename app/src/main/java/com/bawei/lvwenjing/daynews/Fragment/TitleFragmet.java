package com.bawei.lvwenjing.daynews.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.lvwenjing.daynews.Adapters.IndextAdapter;
import com.bawei.lvwenjing.daynews.IApplication;
import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitle;
import com.bawei.lvwenjing.daynews.bean.YeJianEvent;

import com.bawei.lvwenjing.daynews.newsdrag.ChannelActivity;
import com.bawei.lvwenjing.daynews.newsdrag.bean.HttpBean;
import com.bawei.lvwenjing.daynews.newsdrag.bean.HttpBeanManage;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class TitleFragmet extends Fragment {
     private List<HttpBean.DataBeanX.DataBean> userChannelList;
    private View view;
    private Gson gson;
    private List<TabTitle.DataBeanX.DataBean> tabdata=new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView ivjia;
    private IndextAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.titlefragmet, container, false);
        ivjia = (ImageView) view.findViewById(R.id.titlefragment_iv);
        EventBus.getDefault().register(this);
        ivjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调转 tab选项；
               startActivity(new Intent(getActivity(), ChannelActivity.class));
            }
        });

        gson = new Gson();

        //获取控件
        tabLayout = (TabLayout) view.findViewById(R.id.table);
        viewPager = (ViewPager)  view.findViewById(R.id.viewpager);
        //获取适配器
         getTitle();
         userChannelList = ((ArrayList<HttpBean.DataBeanX.DataBean>) HttpBeanManage.getManage(IApplication.getApp().getSQLHelper()).getUserChannel());
        System.out.println("userChannelList = " + userChannelList.size());

        adapter = new IndextAdapter(getChildFragmentManager(),userChannelList,tabdata);

        viewPager.setAdapter(adapter);

        //绑定aa
        tabLayout.setupWithViewPager(viewPager);
//字体颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.hui), getResources().getColor(R.color.colorAccent));
//指示器颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.hui));
//模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      
    }


    public void getTitle() {
        String pathTitle="http://ic.snssdk.com/article/category/get/v2/?iid=2939228904";
        RequestParams entity=new RequestParams(pathTitle);
        entity.setConnectTimeout(5000);
        entity.setReadTimeout(5000);


        x.http().get(entity,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TabTitle tabTitle = gson.fromJson(result, TabTitle.class);

                tabdata.addAll(tabTitle.getData().getData());

                adapter = new IndextAdapter(getChildFragmentManager(),userChannelList,tabdata);

                viewPager.setAdapter(adapter);

              //  IndextAdapter adapter = new IndextAdapter(getChildFragmentManager(),tabdata);

             //   viewPager.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {



            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent(String a) {

                userChannelList.clear();
                ArrayList<HttpBean.DataBeanX.DataBean> userChannel = (ArrayList<HttpBean.DataBeanX.DataBean>) HttpBeanManage.getManage(IApplication.getApp().getSQLHelper()).getUserChannel();
                userChannelList.addAll(userChannel);
                adapter.notifyDataSetChanged();

    }
}
