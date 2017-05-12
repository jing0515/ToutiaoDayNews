package com.bawei.lvwenjing.daynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.bean.YeJianEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class TuijianFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tuijianfragment, container, false);
        EventBus.getDefault().register(this);
        return view;
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
}
