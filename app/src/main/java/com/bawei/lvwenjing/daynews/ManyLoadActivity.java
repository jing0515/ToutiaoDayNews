package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.lvwenjing.daynews.bean.YeJianEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//王学士，注册界面
public class ManyLoadActivity extends Activity {
    // 默认是日间模式
    private int theme = R.style.AppTheme;

    private Button phoneLoad;
    private Button zhuce;
    private ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
       setContentView(R.layout.activity_zhu_ce);
        initView();
    }

    private void initView() {
        phoneLoad = (Button) findViewById(R.id.zhuce_bt_phoneload);
        zhuce = (Button) findViewById(R.id.zhuce_bt_zhucenow);
        fanhui = (ImageView) findViewById(R.id.zhuce_tv_fanhui);

        phoneLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ManyLoadActivity.this,PhoneLoadActivity.class);
                startActivity(intent);
                finish();
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManyLoadActivity.this,PhoneZhuceActivity.class);
                startActivity(intent);
                finish();
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
  }
        @Subscribe(threadMode =ThreadMode.MAIN ,sticky = true)
        public void onMessageEvent(YeJianEvent event) {
            //View view=new View(this);


//        if(event.isYeJian()){
//            theme = R.style.NightAppTheme ;
//            MainActivity.this.recreate();
//            //夜间模式
//           // view.setBackgroundColor(getResources().getColor(R.color.backgroundColor_night));
//
//        }
//        //白天模式
//        else{
//            theme =  R.style.AppTheme;
//            MainActivity.this.recreate();
//         //   view.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
//        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
