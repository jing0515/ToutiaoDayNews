package com.bawei.lvwenjing.daynews;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;


/**
 * Created by Administrator on 2017/5/10.
 */
//王学士
public class IApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//zehshi
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

    }
}
