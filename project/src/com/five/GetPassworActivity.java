package com.five;

import java.util.HashMap;

import org.json.JSONObject;

import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.DataParser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetPassworActivity extends Activity implements OnClickListener
{

    Button mButtonOk;
    EditText getPhone;
    private HashMap<String , String> map;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_getpassword_activity);

        //
        mButtonOk = (Button) findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
        getPhone = (EditText) findViewById(R.id.et_phonenumber);
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
        if (v.equals(mButtonOk))
        {
            // 获取密码保护问题 如果没有直接返回密码 有的话跳转到输入密保问题页面
             phone = getPhone.getText().toString();
            if(!LoadingActivity.isNumeric(phone))
            {
                Toast.makeText(getBaseContext(), "输入应为数字", Toast.LENGTH_SHORT).show();
                return;
            }
            long number = Long.parseLong(phone);
            if(number <10000000000l || number > 99999999999l)
            {
                Toast.makeText(getBaseContext(), "输入应为十一位手机号", Toast.LENGTH_SHORT).show();
                return;
            }
            
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("phone", phone);
            hcu.get(Url.GetProtectQuestion, HttpConnectEvent.HTTP_GET_PASSWORD_QUESTION);
        }
    }

    /**
     * 
     */
    private Handler handler = new HttpHandler(GetPassworActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_PASSWORD_QUESTION:
                    map =  DataParser.getInstance().parserGetQuestion(jObject);
                    if(map.get("protect").equals("yes"))
                    {
                        //有密保问题
                        String q1 = map.get("question1");
                        String q2 = map.get("question2");

                        // 如果有密码保护则进入密码保护页面
                        Intent intent = new Intent();
                        intent.putExtra("question1", q1);
                        intent.putExtra("question2", q2);
                        intent.putExtra("phone", phone);
                        intent.setClass(GetPassworActivity.this, GetPasswordQuestionActivity.class);
                        startActivity(intent);
                        
                    }
                    else
                    {
                        //没有密保问题
                        String pw = map.get("password");
                        //目前该密码用json模拟  以后会改成发短信
                    }
                    GetPassworActivity.this.finish();
                    break;
            }
        }

        @Override
        protected void failed(JSONObject jObject, int event)
        {
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_PASSWORD:
                {
                    // 
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
