package com.bawei.lvwenjing.daynews.silpingmenu_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bawei.lvwenjing.daynews.MainActivity;
import com.bawei.lvwenjing.daynews.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class SilpingMenu_left extends Fragment {

    private ImageButton tengxunweibo;
    private ImageButton qq;
    private ImageButton weibo;

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
        tengxunweibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().loadweixin();
            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().loadQQ();
            }
        });
        weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().loadSIna();
            }
        });


    }

    //王学士  QQ登录



}
