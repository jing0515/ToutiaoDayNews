package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.bean.YeJianEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//王学士，注册界面
public class ManyLoadActivity extends Activity {
    // 默认是日间模式
    private int theme = R.style.AppTheme;

    private Button phoneLoad;
    private Button zhuce;
    private ImageView fanhui;

    private View view;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGrayBackgroud();
        EventBus.getDefault().register(this);
       setContentView(R.layout.activity_zhu_ce);
        initView();
    }

    private void initView() {
        phoneLoad = (Button) findViewById(R.id.zhuce_bt_phoneload);
        zhuce = (Button) findViewById(R.id.zhuce_bt_zhucenow);
        fanhui = (ImageView) findViewById(R.id.zhuce_tv_fanhui);

        phoneLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ManyLoadActivity.this,PhoneLoadActivity.class);
                startActivity(intent);
                finish();
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManyLoadActivity.this,PhoneZhuceActivity.class);
                startActivity(intent);
                finish();
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
  }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        windowManager.removeViewImmediate(view);
    }
    // 日 夜切换
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMainActivityEvent(YeJianEvent event) {
        System.out.println("manyloadActity = " + event.isYeJian());

        if (event.isYeJian()) {
            // true 夜
            windowManager.addView(view, layoutParams);

        } else {
            // 日
            windowManager.removeViewImmediate(view);

        }
    }
    //日夜
    public void initGrayBackgroud() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

//        应用程序窗口。 WindowManager.LayoutParams.TYPE_APPLICATION
//        所有程序窗口的“基地”窗口，其他应用程序窗口都显示在它上面。
//        普通应用功能程序窗口。token必须设置为Activity的token，以指出该窗口属谁。

        //无焦点 无触摸事件    支撑透明


//        后面的view获得焦点
        layoutParams = new WindowManager.LayoutParams
                (WindowManager.LayoutParams.TYPE_APPLICATION,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        PixelFormat.TRANSPARENT);
        view = new View(this);

        view.setBackgroundResource(R.color.color_window);

    }

}
