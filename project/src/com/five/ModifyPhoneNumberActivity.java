package com.five;

import org.json.JSONObject;

import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.DataParser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifyPhoneNumberActivity extends Activity implements OnClickListener
{

    /**
     * 密码
     */
    private EditText mEditTextPassword;

    /**
     * 手机号
     */
    private EditText mEditTextPhoneNumber;
    
    /**
     * 提交手机号按钮
     */
    private Button mButtonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_modify_phonenumber);
        
        this.init();
        
    }
    
    private void init()
    {
        //
        mEditTextPassword = (EditText) findViewById(R.id.et_input_password);
        
        //
        mEditTextPhoneNumber = (EditText) findViewById(R.id.et_input_phonenumber);
        
        //
        mButtonOk = (Button) findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
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
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonOk))
        {
         // 提交手机号修改
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("phone", "13488791001");
            hcu.addGetParmeter("password", "123456");
            hcu.get(Url.ModifyPhonenumber, HttpConnectEvent.HTTP_MODIFY_PHONENUMBER);
        }
        
    }
    
    /**
     * 
     */
    private Handler handler = new HttpHandler(ModifyPhoneNumberActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_MODIFY_PHONENUMBER:
                {
                    //
                    DataParser.getInstance().parserModifyPhonenumber(jObject);
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
                case HttpConnectEvent.HTTP_MODIFY_PHONENUMBER:
                {
                    
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
