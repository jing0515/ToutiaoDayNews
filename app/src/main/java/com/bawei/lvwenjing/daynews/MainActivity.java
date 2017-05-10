package com.bawei.lvwenjing.daynews;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.bawei.lvwenjing.daynews.Fragment.TitleFragmet;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_left;
import com.bawei.lvwenjing.daynews.silpingmenu_fragment.SilpingMenu_rigth;
import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLeftRight();
        getSupportFragmentManager().beginTransaction().replace(R.id.title_fragment, new TitleFragmet()).commit();

        a();
        //这是注解王学士
        //设么情况
    }


public void a(){}

    private void initLeftRight() {
        Fragment leftFragment = new SilpingMenu_left();
        setBehindContentView(R.layout.sildingmenu_left);

        getSupportFragmentManager().beginTransaction().replace(R.id.title_fragment, leftFragment).commit();

        SlidingMenu slidingMenu = getSlidingMenu();
        // 设置slidingmenu滑动的方式
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingmenu边界的阴影图片
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);

        //设置右边（二级）侧滑菜单
        SilpingMenu_rigth rightMenuFragment = new SilpingMenu_rigth();


        slidingMenu.setSecondaryMenu(R.layout.sildingmenu_rigth);

        getSupportFragmentManager().beginTransaction().replace(R.id.title_fragment, rightMenuFragment).commit();
    }

}
