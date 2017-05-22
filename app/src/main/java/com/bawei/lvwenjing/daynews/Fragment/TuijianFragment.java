package com.bawei.lvwenjing.daynews.Fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lvwenjing.daynews.Adapters.TuiJianListViewAdapter;
import com.bawei.lvwenjing.daynews.CtriyActivity;
import com.bawei.lvwenjing.daynews.IApplication;
import com.bawei.lvwenjing.daynews.MainActivity;
import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitleFragmentBean;
import com.bawei.lvwenjing.daynews.bean.TuiJianBean;
import com.bawei.lvwenjing.daynews.bean.YeJianEvent;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/9.fgadfgdfgadfgd
 */

public class TuijianFragment extends Fragment {

    private View view;
    private SpringView springView;
    private ListView listView;
    private String path0 = "http://ic.snssdk.com/2/article/v25/stream/?category=";
    private String category;
    private Gson gson;
    private TuiJianListViewAdapter tuiJianListViewAdapter;
    private String page0 = "&min_behot_time=";
    private List<TuiJianBean.DataBean> listData=new ArrayList<>();
    // private ArrayList<HttpBean.DataBeanX.DataBean> userChannelList;
    private int pageInt = 1494642113;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int what = msg.what;
//            List<TabTitleFragmentBean.DataBean> listData1 = (List<TabTitleFragmentBean.DataBean>) msg.obj;
//
//        }
//    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tuijianfragment, container, false);


        initView(view);
     
        Bundle arguments = getArguments();
        category = arguments.getString("category");
        gson = new Gson();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        boolean wifi = isWifi();
        if(wifi){
        //有网络
        getShuju(path0 + category + page0 + pageInt, 0);
        }
        else {

            try {
                List<TuiJianBean.DataBean> all = x.getDb(IApplication.daoConfig1).findAll(TuiJianBean.DataBean.class);
                listData.addAll(all);
                tuiJianListViewAdapter = new TuiJianListViewAdapter(getActivity(), listData);
                listView.setAdapter(tuiJianListViewAdapter);
                System.out.println("all.size() = " + all.size());
            } catch (DbException e) {
                e.printStackTrace();
            }

        }

        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);


        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageInt -= 10000000;
                getShuju(path0 + category + page0 + pageInt, 1);
                springView.onFinishFreshAndLoad();
            }


            @Override
            public void onLoadmore() {
                pageInt += 10000000;
                getShuju(path0 + category + page0 + pageInt, 2);
                springView.onFinishFreshAndLoad();

            }
        });

    }

    private void initView(View view) {
        springView = (SpringView) view.findViewById(R.id.tuijianfragment_springview);
        listView = (ListView) view.findViewById(R.id.tuijianfragment_listivew);


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(YeJianEvent event) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getShuju(String path, final int type) {

        RequestParams entity = new RequestParams(path);
        x.http().get(entity, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                if (type == 0) {

                    TuiJianBean tuiJianBean = gson.fromJson(result,TuiJianBean.class);
                    List<TuiJianBean.DataBean> data = tuiJianBean.getData();

                 //   System.out.println("data.size()+data.get(0).getTitle() = " + data.size()+data.get(0).getTitle());
                    listData.addAll(data);
                    tuiJianListViewAdapter = new TuiJianListViewAdapter(getActivity(), listData);
                    listView.setAdapter(tuiJianListViewAdapter);
                    //进行判断
                    if (category.equals("news_local")) {
                        TextView tv = new TextView(getActivity());
                        tv.setText("请选择城市");
                        tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), CtriyActivity.class));
                            }
                        });
                        listView.addHeaderView(tv);
                    }


                } else if (type == 1) {
                    System.out.println("111111111");
                    TuiJianBean tuiJianBean = gson.fromJson(result, TuiJianBean.class);
                    List<TuiJianBean.DataBean> data1 = tuiJianBean.getData();
                    listData.clear();
                    listData.addAll(data1);
                    tuiJianListViewAdapter.notifyDataSetChanged();

                } else if (type == 2) {
                    TuiJianBean tuiJianBean = gson.fromJson(result, TuiJianBean.class);
                    List<TuiJianBean.DataBean> data1 = tuiJianBean.getData();
                    listData.addAll(listData.size(), data1);
                    tuiJianListViewAdapter.notifyDataSetChanged();


                }

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
    public boolean isWifi() {

        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//获取可用的网络信息
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//若可用网络不为空，并且已经连接
        if (networkInfo != null && networkInfo.isConnected()) {
//判断可用网络的类型，是否为wifi
            return  true;
        }
        return false;
    }
}
