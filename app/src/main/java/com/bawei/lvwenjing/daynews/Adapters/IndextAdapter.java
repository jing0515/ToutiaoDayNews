package com.bawei.lvwenjing.daynews.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bawei.lvwenjing.daynews.Fragment.TuijianFragment;
import com.bawei.lvwenjing.daynews.bean.TabTitle;
import com.bawei.lvwenjing.daynews.newsdrag.bean.HttpBean;

import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/9.
 */

public class IndextAdapter extends FragmentPagerAdapter {
   private List<HttpBean.DataBeanX.DataBean> tabdata;
//   private List<TabTitle.DataBeanX.DataBean> tabdata;
 List<TabTitle.DataBeanX.DataBean> tabdatatitle;
     public IndextAdapter(FragmentManager fm, List<HttpBean.DataBeanX.DataBean> tabdata,List<TabTitle.DataBeanX.DataBean> tabdatatitle) {
        super(fm);
        this.tabdata = tabdata;
         this.tabdatatitle=tabdatatitle;
    }

    @Override
    public Fragment getItem(int position) {
        String category = null;
        String name = tabdata.get(position).getName();
        TuijianFragment tuijianFragment =  new TuijianFragment() ;
        for (TabTitle.DataBeanX.DataBean da:tabdatatitle){
            if(name.equals(da.getName())){
                category=da.getCategory();
            }
        }



            Bundle bundle = new Bundle();

            bundle.putString("category",category);
            tuijianFragment.setArguments(bundle);
            return tuijianFragment;
    }

    @Override
    public int getCount() {
        return tabdata==null?0:tabdata.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {


        return tabdata.get(position).getName() ;
    }
}
