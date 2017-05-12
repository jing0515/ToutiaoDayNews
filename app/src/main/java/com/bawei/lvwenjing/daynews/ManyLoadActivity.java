package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//王学士，注册界面
public class ManyLoadActivity extends Activity {

    private Button phoneLoad;
    private Button zhuce;
    private ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
