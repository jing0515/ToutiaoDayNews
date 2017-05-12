package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class PhoneZhuceActivity extends Activity {
    private EditText phone;
    private EditText psw;
    private Button zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_zhuce);
        initView();


    }

    private void initView() {
        ImageView gengduoiv = (ImageView) findViewById(R.id.gengduo_iv);
        gengduoiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        phone = (EditText) findViewById(R.id.phonezhuce_et_phone);
        psw = (EditText) findViewById(R.id.phonezhuce_et_psw);
        zhuce = (Button) findViewById(R.id.phonezhuce_bt_load);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path0 = "http://qhb.2dyt.com/Bwei/register";
                String ph = phone.getText().toString().trim();
                String psw1 = psw.getText().toString().trim();
                zhuceget(path0, ph, psw1, "1503d");
            }
        });


    }

    private void zhuceget(String path, String phone, String psw, String postkey) {


        RequestParams requestParams = new RequestParams(path);
        requestParams.addQueryStringParameter("postkey", postkey);
        requestParams.addQueryStringParameter("phone", phone);
        requestParams.addQueryStringParameter("password", psw);

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {

                System.out.println("result = " + result);
                Toast.makeText(PhoneZhuceActivity.this, result, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
}
