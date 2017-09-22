package com.bawei.lvwenjing.daynews;

import android.graphics.PixelFormat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bawei.lvwenjing.daynews.Fragment.TitleFragmet;
import com.bawei.lvwenjing.daynews.bean.YeJianEvent;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_left;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_rigth;
import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;


public class MainActivity extends SlidingFragmentActivity  {
    private View view;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否订阅
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        setContentView(R.layout.activity_main);
        TitleFragmet titleFragmet = new TitleFragmet();
        getSupportFragmentManager().beginTransaction().replace(R.id.title_fragment,titleFragmet).commit();
        initGrayBackgroud();
        initLeftRight();
    }


    private void initLeftRight() {
        Fragment leftFragment = new SilpingMenu_left();
        setBehindContentView(R.layout.left);

        getSupportFragmentManager().beginTransaction().replace(R.id.left, leftFragment).commit();

        SlidingMenu slidingMenu = getSlidingMenu();
        // 设置slidingmenu滑动的方式
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingmenu边界的阴影图片
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);

        //设置右边（二级）侧滑菜单
        SilpingMenu_rigth rightMenuFragment = new SilpingMenu_rigth();


        slidingMenu.setSecondaryMenu(R.layout.right);

        getSupportFragmentManager().beginTransaction().replace(R.id.right, rightMenuFragment).commit();
    }
    public void loadQQ() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                System.out.println("onStart" + share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                System.out.println("onComplete" + share_media);
                String uid = map.get("uid");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                System.out.println("iconurl = " + iconurl);
                System.out.println("uid = " + uid);
                System.out.println("name = " + name);
                System.out.println("gender = " + gender);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                System.out.println("onError" + share_media);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                System.out.println("onCancel" + share_media);
            }
        });
    }

    //王学士  微信登录
    public void loadweixin() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                System.out.println("onStart" + share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                System.out.println("onComplete" + share_media);
                String uid = map.get("uid");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                System.out.println("iconurl = " + iconurl);
                System.out.println("uid = " + uid);
                System.out.println("name = " + name);
                System.out.println("gender = " + gender);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                System.out.println("onError" + share_media);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                System.out.println("onCancel" + share_media);
            }
        });
    }

    //王学士  新浪登录
    public void loadSIna() {
        UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                System.out.println("onStart" + share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                System.out.println("onComplete" + share_media);
                String uid = map.get("uid");
                String name = map.get("name");
                String gender = map.get("gender");
                String iconurl = map.get("iconurl");
                System.out.println("iconurl = " + iconurl);
                System.out.println("uid = " + uid);
                System.out.println("name = " + name);
                System.out.println("gender = " + gender);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                System.out.println("onError" + share_media);
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                System.out.println("onCancel" + share_media);
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(YeJianEvent event) {
        View view=new View(this);
        if(event.isYeJian()){
            //夜间模式
            view.setBackgroundColor(getResources().getColor(R.color.backgroundColor_night));

        }
        //白天模式
        else{

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);


    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

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
    // 日 夜切换
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent(YeJianEvent event) {
        System.out.println("isChecked = " + event.isYeJian());

        if (event.isYeJian()) {
            // true 夜
            windowManager.addView(view, layoutParams);

        } else {
            // 日
         windowManager.removeView(view);


        }
    }
}
