package com.bawei.lvwenjing.daynews.newsdrag.dao;

import android.content.ContentValues;

import com.bawei.lvwenjing.daynews.newsdrag.bean.HttpBean;

import java.util.List;
import java.util.Map;

public interface HttpBeanDaoInface {
    public boolean addCache(HttpBean.DataBeanX.DataBean item);

    public boolean deleteCache(String whereClause, String[] whereArgs);

    public boolean updateCache(ContentValues values, String whereClause,
                               String[] whereArgs);

    public Map<String, String> viewCache(String selection,
                                         String[] selectionArgs);

    public List<Map<String, String>> listCache(String selection,
                                               String[] selectionArgs);

    public void clearFeedTable();

}
