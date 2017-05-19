package com.bawei.lvwenjing.daynews.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.lvwenjing.daynews.Fragment.ShipinFragment;
import com.bawei.lvwenjing.daynews.Fragment.TuijianFragment;
import com.bawei.lvwenjing.daynews.bean.TabTitle;

import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class IndextAdapter extends FragmentPagerAdapter {

    private List<TabTitle.DataBeanX.DataBean> tabdata;
   // public String [] TITLE = {"推荐","热点","本地","视频","社会","娱乐","科技","汽车","科技","汽车","体育","财经","军事","国际","段子","趣图","健康","美女"} ;

     public IndextAdapter(FragmentManager fm, List<TabTitle.DataBeanX.DataBean> tabdata) {
        super(fm);
        this.tabdata = tabdata;
    }

    @Override
    public Fragment getItem(int position) {

       // if(position == 0){
        String category = tabdata.get(position).getCategory();
        TuijianFragment tuijianFragment =  new TuijianFragment() ;

     
            Bundle bundle = new Bundle();
            bundle.putString("category",category);
            tuijianFragment.setArguments(bundle);
            return tuijianFragment;
        //}
//        else {
//
//            return new ShipinFragment();
//        }
        //名字
        //地址
      //  tabdata.get(0).getName();
       // tabdata.get(0).getCategory();

    }

    @Override
    public int getCount() {
        return tabdata.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        System.out.println("tabdata.get(position).getCategory() = " + tabdata.get(position).getCategory());
        return tabdata.get(position).getName() ;
    }
}
