package com.bawei.lvwenjing.daynews.silpingmenu_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.MainActivity;
import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.ManyLoadActivity;


/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class SilpingMenu_left extends Fragment {

    private ImageButton tengxunweibo;
    private ImageButton qq;
    private ImageButton weibo;
    private TextView manyload;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sildingmenu_left, container, false);
        inintView(view);

        return view;
    }

    //王学士   初始化视图
    private void inintView(View view) {
        tengxunweibo = (ImageButton) view.findViewById(R.id.silpingmenu_rigth_tengxun_weibo);
        qq = (ImageButton) view.findViewById(R.id.silpingmenu_rigth_QQ);
        weibo = (ImageButton) view.findViewById(R.id.silpingmenu_rigth_weibo);
        manyload = (TextView) view.findViewById(R.id.silpingmenu_rigth_manyload);
        manyload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ManyLoadActivity.class);
                startActivity(intent);
            }
        });
        tengxunweibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("weibo\" = " + "weibo");
                MainActivity mainActivity1 = (MainActivity) getActivity();
                mainActivity1.loadweixin();

            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("weibo\" = " + "qq");
                MainActivity mainActivity2 = (MainActivity) getActivity();
                mainActivity2.loadQQ();
            }
        });
        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("weibo\" = " + "weibo11");
                MainActivity mainActivity3 = (MainActivity) getActivity();
                mainActivity3.loadSIna();
            }
        });


    }

    //王学士  QQ登录



}
