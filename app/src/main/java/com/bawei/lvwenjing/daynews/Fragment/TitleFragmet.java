package com.bawei.lvwenjing.daynews.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.lvwenjing.daynews.Adapters.IndextAdapter;
import com.bawei.lvwenjing.daynews.R;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class TitleFragmet extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.titlefragmet, container, false);

        //获取控件
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.table);
        ViewPager viewPager = (ViewPager)  view.findViewById(R.id.viewpager);
        //获取适配器
        IndextAdapter adapter = new IndextAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
//字体颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.hui), getResources().getColor(R.color.colorAccent));
//指示器颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.hui));
//模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }
}
