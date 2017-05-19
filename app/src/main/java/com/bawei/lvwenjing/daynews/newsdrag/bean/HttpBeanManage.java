package com.bawei.lvwenjing.daynews.newsdrag.bean;

import android.database.SQLException;
import android.util.Log;

import com.bawei.lvwenjing.daynews.newsdrag.dao.HttpBeanDao;
import com.bawei.lvwenjing.daynews.newsdrag.db.SQLHelper;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpBeanManage {
	public static HttpBeanManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<HttpBean.DataBeanX.DataBean> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<HttpBean.DataBeanX.DataBean> defaultOtherChannels;
	private HttpBeanDao channelDao;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;
	static {
		defaultUserChannels = new ArrayList<HttpBean.DataBeanX.DataBean>();
		defaultOtherChannels = new ArrayList<HttpBean.DataBeanX.DataBean>();
		        RequestParams entity=new RequestParams("http://ic.snssdk.com/article/category/get/v2/?iid=2939228904");
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                HttpBean httpBean = gson.fromJson(result, HttpBean.class);
                List<HttpBean.DataBeanX.DataBean> data = httpBean.getData().getData();
                for(HttpBean.DataBeanX.DataBean da:data){
                    if(da.getDefault_add()==1){
						defaultUserChannels.add(da);
                    }
                    else{
						defaultOtherChannels.add(da);
                    }
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
//		defaultUserChannels.add(new ChannelItem(1, "推荐", 1, 1));
//		defaultUserChannels.add(new ChannelItem(2, "热点", 2, 1));
//		defaultUserChannels.add(new ChannelItem(3, "娱乐", 3, 1));
//		defaultUserChannels.add(new ChannelItem(4, "时尚", 4, 1));
//		defaultUserChannels.add(new ChannelItem(5, "科技", 5, 1));
//		defaultUserChannels.add(new ChannelItem(6, "体育", 6, 1));
//		defaultUserChannels.add(new ChannelItem(7, "军事", 7, 1));
//		defaultOtherChannels.add(new ChannelItem(8, "财经", 1, 0));
//		defaultOtherChannels.add(new ChannelItem(9, "汽车", 2, 0));
//		defaultOtherChannels.add(new ChannelItem(10, "房产", 3, 0));
//		defaultOtherChannels.add(new ChannelItem(11, "社会", 4, 0));
//		defaultOtherChannels.add(new ChannelItem(12, "情感", 5, 0));
//		defaultOtherChannels.add(new ChannelItem(13, "女人", 6, 0));
//		defaultOtherChannels.add(new ChannelItem(14, "旅游", 7, 0));
//		defaultOtherChannels.add(new ChannelItem(15, "健康", 8, 0));
//		defaultOtherChannels.add(new ChannelItem(16, "美女", 9, 0));
//		defaultOtherChannels.add(new ChannelItem(17, "游戏", 10, 0));
//		defaultOtherChannels.add(new ChannelItem(18, "数码", 11, 0));
	}

	private HttpBeanManage(SQLHelper paramDBHelper) throws SQLException {
		if (channelDao == null)
			channelDao = new HttpBeanDao(paramDBHelper.getContext());
		// NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));
		return;
	}

	/**
	 * 初始化频道管理类
	 * @param paramDBHelper
	 * @throws SQLException
	 */
	public static HttpBeanManage getManage(SQLHelper dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new HttpBeanManage(dbHelper);
		return channelManage;
	}

	/**
	 * 清除所有的频道
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<HttpBean.DataBeanX.DataBean> getUserChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?",new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty()) {
			userExist = true;
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			List<HttpBean.DataBeanX.DataBean> list = new ArrayList<HttpBean.DataBeanX.DataBean>();
			for (int i = 0; i < count; i++) {
				HttpBean.DataBeanX.DataBean navigate = new HttpBean.DataBeanX.DataBean();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}

	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<HttpBean.DataBeanX.DataBean> getOtherChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?" ,new String[] { "0" });
		List<HttpBean.DataBeanX.DataBean> list = new ArrayList<HttpBean.DataBeanX.DataBean>();
		if (cacheList != null && !((List) cacheList).isEmpty()){
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++) {
				HttpBean.DataBeanX.DataBean navigate= new HttpBean.DataBeanX.DataBean();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		if(userExist){
			return list;
		}
		cacheList = defaultOtherChannels;
		return (List<HttpBean.DataBeanX.DataBean>) cacheList;
	}

	/**
	 * 保存用户频道到数据库
	 * @param userList
	 */
	public void saveUserChannel(List<HttpBean.DataBeanX.DataBean> userList) {
		for (int i = 0; i < userList.size(); i++) {
			HttpBean.DataBeanX.DataBean channelItem = (HttpBean.DataBeanX.DataBean) userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			channelDao.addCache(channelItem);
		}
	}

	/**
	 * 保存其他频道到数据库
	 * @param otherList
	 */
	public void saveOtherChannel(List<HttpBean.DataBeanX.DataBean> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			HttpBean.DataBeanX.DataBean channelItem = (HttpBean.DataBeanX.DataBean) otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			channelDao.addCache(channelItem);
		}
	}

	/**
	 * 初始化数据库内的频道数据
	 */
	private void initDefaultChannel(){
		Log.d("deleteAll", "deleteAll");
		deleteAllChannel();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
