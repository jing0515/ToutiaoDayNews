package com.bawei.lvwenjing.daynews;

import android.app.Application;
import android.os.Environment;

import com.bawei.lvwenjing.daynews.newsdrag.db.SQLHelper;
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
    private static IApplication mAppApplication;
    private SQLHelper sqlHelper;

    private   static DbManager.DaoConfig  daoConfig;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this ;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        initDB();
        getDaoConfig();
    }

    //加载数据库   牛琼琼
    public   static DbManager.DaoConfig initDB(){

        daoConfig = new DbManager.DaoConfig().setDbName("Nqq.db").setDbVersion(1);

        return  daoConfig;
    }


    public  static DbManager.DaoConfig daoConfig1;
    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig1==null){
            daoConfig1=new DbManager.DaoConfig()
                    .setDbVersion(1)
                    .setDbName("tt")//设置数据库的名字
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig1;
    }

    /** 获取Application */
    public static IApplication getApp() {
        return mAppApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    /** 摧毁应用进程时候调用 */
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
    }

    public void clearAppCache() {
    }


}
