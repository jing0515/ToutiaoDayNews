package com.bawei.lvwenjing.daynews;

import android.app.Application;
import android.os.Environment;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;


/**
 * Created by Administrator on 2017/5/10.aa
 */
//王学士
public class IApplication extends Application {
    DbManager.DaoConfig daoConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");

    }

    //吕文静建立数据库
    public void getDaoConfig() {
        File file = new File(Environment.getExternalStorageDirectory().getPath());
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("tujian.db")
                    .setDbDir(file)
                    .setDbVersion(1)
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
    }
}
