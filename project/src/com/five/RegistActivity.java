package com.five;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.DataParser;

public class RegistActivity extends Activity implements OnClickListener
{

    /**
     * 注册按钮
     */
    private Button mButtonRegist;

    /**
     * 手机号输入框
     */
    private EditText mEditTextPhonenumber;
    //获取到的验证码
    public String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_regist_activity);
        this.init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        mEditTextPhonenumber = (EditText) findViewById(R.id.et_phonenumber);
        mButtonRegist = (Button) findViewById(R.id.button_regist);
        mButtonRegist.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
            {
                this.finish();
            }

                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonRegist))
        {
            // 联网注册
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("phone", "13488791007");
            hcu.get(Url.Verification, HttpConnectEvent.HTTP_GET_Verfication);

        }

    }

    /**
     * 
     */
    private Handler handler = new HttpHandler(RegistActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_Verfication:
                {
                    verify =  DataParser.getInstance().parserVerificationData(jObject);
                    
                    // 关闭注册界面进入登录界面
                    RegistActivity.this.finish();
                }

                    break;
            }
        }

        @Override
        protected void failed(JSONObject jObject, int event)
        {
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_REGIST:
                {
                    // 注册失败

                    // 关闭注册界面进入登录界面
                    RegistActivity.this.finish();
                }
                    break;
            }
        }

        @Override
        protected void error(int event)
        {
        }

    };

}
