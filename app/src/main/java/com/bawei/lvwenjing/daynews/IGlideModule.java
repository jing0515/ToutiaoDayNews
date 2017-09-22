package com.bawei.lvwenjing.daynews;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;
import java.io.IOException;

/**
 * Created by muhanxi on 17/5/12.
 */

public class IGlideModule implements GlideModule {


    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {

        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheLocation = new File(Environment.getExternalStorageDirectory()+"/news");

//                if(cacheLocation.exists()){}
//                else{
//                    try {
//                        cacheLocation.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
                boolean mkdirs = cacheLocation.mkdirs();
                System.out.println("mkdirs 创建文件= " + mkdirs);
                return DiskLruCacheWrapper.get(cacheLocation,1024*1024*100);
            }
        }) ;

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
