package com.bawei.lvwenjing.daynews.silpingmenu_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.LianxiDoemActivity;
import com.bawei.lvwenjing.daynews.MainActivity;
import com.bawei.lvwenjing.daynews.R;
import com.bawei.lvwenjing.daynews.ManyLoadActivity;


/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class SilpingMenu_left extends Fragment {

    private ImageView tengxunweibo;
    private ImageView qq;
    private ImageView weibo;
    private TextView manyload;
    private RadioButton lixianrb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sildingmenu_left, container, false);
        inintView(view);

        return view;
    }

    //王学士   初始化视图
    private void inintView(View view) {
        lixianrb = (RadioButton) view.findViewById(R.id.slipngmeun_left_lixian_rb);
        tengxunweibo = (ImageView) view.findViewById(R.id.silpingmenu_rigth_tengxun_weibo);
        qq = (ImageView) view.findViewById(R.id.silpingmenu_rigth_QQ);
        weibo = (ImageView) view.findViewById(R.id.silpingmenu_rigth_weibo);
        manyload = (TextView) view.findViewById(R.id.silpingmenu_rigth_manyload);
        lixianrb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LianxiDoemActivity.class));
            }
        });
        manyload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManyLoadActivity.class);
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
