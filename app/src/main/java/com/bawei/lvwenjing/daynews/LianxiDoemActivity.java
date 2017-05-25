package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.lvwenjing.daynews.Adapters.IndextAdapter;
import com.bawei.lvwenjing.daynews.Adapters.LianxiDoemListviewAdapter;
import com.bawei.lvwenjing.daynews.Fragment.TitleFragmet;
import com.bawei.lvwenjing.daynews.bean.TabTitle;
import com.bawei.lvwenjing.daynews.bean.TuiJianBean;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_left;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_rigth;
import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class LianxiDoemActivity extends Activity {

    private LianxiDoemListviewAdapter lianxiDoemListviewAdapter;
    private ListView listView;
    private Gson gson = new Gson();
    private List<TabTitle.DataBeanX.DataBean> tabdata = new ArrayList<>();
    String path0 = "http://ic.snssdk.com/2/article/v25/stream/?category=";
    //吕文静
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lianxiactivity);
        init();
        get();
    }

    //吕文静下载页面
    private void init() {
        listView = (ListView) findViewById(R.id.download_listview);
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
                            getData();
                            Toast.makeText(LianxiDoemActivity.this, "好了", Toast.LENGTH_SHORT).show();
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
        String pathTitle = "http://ic.snssdk.com/article/category/get/v2/?iid=2939228904";
        RequestParams entity = new RequestParams(pathTitle);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                TabTitle tabTitle = gson.fromJson(result, TabTitle.class);
                tabdata.addAll(tabTitle.getData().getData());
                lianxiDoemListviewAdapter = new LianxiDoemListviewAdapter(LianxiDoemActivity.this, tabdata);
                listView.setAdapter(lianxiDoemListviewAdapter);

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

    //牛琼琼  和获取数据加载入数据库
    private void getData() {
        for (TabTitle.DataBeanX.DataBean bean:tabdata) {
            int default_add = bean.getDefault_add();
            if (default_add == 1) {
                String category = bean.getCategory();
                String path = path0 + category;
                get1(path);
            }
        }

    }
//王学士 离线下载
    private void get1(String path11) {

        RequestParams entity = new RequestParams(path11);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();

                TuiJianBean tt = gson.fromJson(result, TuiJianBean.class);
                try {
                    x.getDb(IApplication.daoConfig1).delete(TuiJianBean.DataBean.class);

                    for (TuiJianBean.DataBean bean:tt.getData()){
                        x.getDb(IApplication.getDaoConfig()).save(bean);
                    }

                    System.out.println("\"保存\" = " + "保存");

                } catch (DbException e) {
                    e.printStackTrace();
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