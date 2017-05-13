package com.bawei.lvwenjing.daynews.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bawei.lvwenjing.daynews.Adapters.TuiJianListViewAdapter;
import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.TabTitleFragmentBean;
import com.bawei.lvwenjing.daynews.bean.YeJianEvent;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class TuijianFragment extends Fragment {

    private View view;
    private SpringView springView;
    private ListView listView;
    private String path0="http://ic.snssdk.com/2/article/v25/stream/?category=";
    private String category;
    private Gson gson;
    private TuiJianListViewAdapter tuiJianListViewAdapter;
    private String page0="&min_behot_time=";
    private List<TabTitleFragmentBean.DataBean> listData;
    private int pageInt=1494642113;
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            List<TabTitleFragmentBean.DataBean> listData1 = (List<TabTitleFragmentBean.DataBean>) msg.obj;

        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tuijianfragment, container, false);
     //   EventBus.getDefault().register(this);
        initView(view);
        Bundle arguments = getArguments();
        category = arguments.getString("category");
        gson = new Gson();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getShuju(path0+category+page0+pageInt,0);
        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                pageInt-=10000000;
                getShuju(path0+category+page0+pageInt,1);
                springView.onFinishFreshAndLoad();
            }



            @Override
            public void onLoadmore() {
                pageInt+=10000000;
                getShuju(path0+category+page0+pageInt,2);
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

        if(event.isYeJian()){
            //夜间模式
            view.setBackgroundColor(getResources().getColor(R.color.backgroundColor_night));

        }
        //白天模式
        else{
            view.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getShuju(String path, final int type) {

        RequestParams entity=new RequestParams(path);
        x.http().get(entity, new Callback.CommonCallback<String>() {




            @Override
            public void onSuccess(String result) {
         if(type==0){

                TabTitleFragmentBean tabTitleFragmentBean = gson.fromJson(result, TabTitleFragmentBean.class);
                listData = tabTitleFragmentBean.getData();
                tuiJianListViewAdapter = new TuiJianListViewAdapter(getActivity(), listData);
                 listView.setAdapter(tuiJianListViewAdapter);
         }
          else if(type==1){
             System.out.println("111111111");
                  TabTitleFragmentBean tabTitleFragmentBean = gson.fromJson(result, TabTitleFragmentBean.class);
                  List<TabTitleFragmentBean.DataBean> listData1 = tabTitleFragmentBean.getData();
//             Message msg=Message.obtain();
//             msg.obj=listData1;
//             handler.sendMessage(msg);
             listData.clear();
             listData.addAll(listData1);
             tuiJianListViewAdapter.notifyDataSetChanged();

               }
       else if(type==2){
             System.out.println("2222222222");
    TabTitleFragmentBean tabTitleFragmentBean = gson.fromJson(result, TabTitleFragmentBean.class);
    List<TabTitleFragmentBean.DataBean> listData1 = tabTitleFragmentBean.getData();

    listData.addAll(listData.size(),listData1);
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
}
