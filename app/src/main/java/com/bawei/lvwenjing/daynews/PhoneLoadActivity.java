package com.bawei.lvwenjing.daynews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

//王学士 手机号登录
public class PhoneLoadActivity extends Activity {
    private EditText phone;
    private EditText psw;
    private Button load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_load);
        initView();

    }
    private void initView() {
        phone = (EditText) findViewById(R.id.phoneload_et_phone);
        psw = (EditText) findViewById(R.id.phoneload_et_psw);
        load = (Button) findViewById(R.id.phoneload_bt_load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path="http://qhb.2dyt.com/Bwei/login";
                String ph = phone.getText().toString().trim();
                String psw1 = psw.getText().toString().trim();
                loadget(path,ph,psw1,"1503d");
            }
        });
    }

    private void loadget(String path,String username,String psw,String postkey){
        RequestParams requestParams = new RequestParams(path);
        requestParams.addQueryStringParameter("postkey",postkey);
        requestParams.addQueryStringParameter("username",username);
        requestParams.addQueryStringParameter("password",psw);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("result = " + result);
                Toast.makeText(PhoneLoadActivity.this,result,Toast.LENGTH_SHORT).show();
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
