package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

//王学士，注册界面
public class ManyLoadActivity extends Activity {

    private Button phoneLoad;
    private Button zhuce;
    private TextView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_zhu_ce);
        initView();
    }

    private void initView() {
        phoneLoad = (Button) findViewById(R.id.zhuce_bt_phoneload);
        zhuce = (Button) findViewById(R.id.zhuce_bt_zhucenow);
        fanhui = (TextView) findViewById(R.id.zhuce_tv_fanhui);





    }
}
