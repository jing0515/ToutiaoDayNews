package com.bawei.lvwenjing.daynews;

import android.app.Application;
import android.os.Environment;

import com.bawei.lvwenjing.daynews.newsdrag.db.SQLHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;
import java.io.IOException;


//王学士
public class IApplication extends Application {
    private static IApplication mAppApplication;
    private SQLHelper sqlHelper;

    private   static DbManager.DaoConfig  daoConfig;
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        mAppApplication = this ;
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    //    initDB();
        getDaoConfig();
    }

    //加载数据库   牛琼琼
//    public   static DbManager.DaoConfig initDB(){
//
//        daoConfig = new DbManager.DaoConfig().setDbName("Nqq.db").setDbVersion(1);
//
//        return  daoConfig;
//    }
//

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
    private void initImageLoader() {
        String path = Environment.getExternalStorageDirectory() + "/imageload";
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800)//缓存图片最大的长和宽
                .threadPoolSize(2)//线程池的数量
                .threadPriority(4)
                .memoryCacheSize(2 * 1024 * 1024)//设置内存缓存区大小
                .diskCacheSize(20 * 1024 * 1024)//设置sd卡缓存区大小
                .writeDebugLogs()//打印日志内容
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .build();
        ImageLoader.getInstance().init(config);
    }


}
