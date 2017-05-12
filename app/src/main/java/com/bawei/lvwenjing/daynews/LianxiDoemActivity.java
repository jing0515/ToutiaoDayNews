package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.Fragment.TitleFragmet;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_left;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_rigth;
import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;


public class LianxiDoemActivity extends Activity {


    //吕文静
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lianxiactivity);
        init();
    }

    //吕文静下载页面
    private void init() {

        ImageView download_fanhui = (ImageView) findViewById(R.id.download_fanhui);
        download_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tuijian_tv = (TextView) findViewById(R.id.download_xiazaiwenjian);
        tuijian_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LianxiDoemActivity.this);
                    builder.setTitle("移动网络离线下载会使用较多流量，是否继续？");
                    builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("取消", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
            }
        });


    }

    //吕文静网络请求
    private void get() {

        RequestParams requestParams = new RequestParams("http://qhb.2dyt.com/Bwei/login");

        x.http().get(requestParams, new Callback.CommonCallback() {

            @Override
            public void onSuccess(Object result) {

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