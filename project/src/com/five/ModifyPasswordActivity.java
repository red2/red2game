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
import android.widget.Toast;

public class ModifyPasswordActivity extends Activity implements OnClickListener
{
    /**
     * 原密码
     */
    private EditText mEditTextOriginalPassword;

    /**
     * 新密码
     */
    private EditText mEditTextNewPassword;

    /**
     * 重复密码
     */
    private EditText mEditTextRePassword;

    /**
     * 修改密码按钮
     */
    private Button mButtnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_modify_password);

        this.init();
    }

    /**
     * 初始化
     */
    private void init()
    {
        //
        mEditTextOriginalPassword = (EditText) findViewById(R.id.et_original_password);
        //mEditTextOriginalPassword.setOnClickListener(this);

        //
        mEditTextNewPassword = (EditText) findViewById(R.id.et_new_password);
       // mEditTextNewPassword.setOnClickListener(this);

        //
        mEditTextRePassword = (EditText) findViewById(R.id.et_repeat_password);
       // mEditTextRePassword.setOnClickListener(this);

        //
        mButtnOk = (Button) findViewById(R.id.button_ok);
        mButtnOk.setOnClickListener(this);
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
        if (v.equals(mButtnOk))
        {
            // 提交密码修改
            //判断两次输入是否一致
            if(!mEditTextNewPassword.getText().toString().equals(mEditTextRePassword.getText().toString()))
            {
                Toast.makeText(getBaseContext(), "密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("phone", "13488791006");
            hcu.addPostParmeter("opassword", mEditTextOriginalPassword.getText().toString());
            hcu.addPostParmeter("npassword", mEditTextNewPassword.getText().toString());
            hcu.post(Url.ModifyPassword, HttpConnectEvent.HTTP_MODIFY_PASSWORD);

        }
    }

    /**
     * 
     */
    private Handler handler = new HttpHandler(ModifyPasswordActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_MODIFY_PASSWORD:
                {
                    //
                    DataParser.getInstance().parserModifyPassword(jObject);
                }

                    break;
            }
            ModifyPasswordActivity.this.finish();
        }

        @Override
        protected void failed(JSONObject jObject, int event)
        {
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_MODIFY_PASSWORD:
                {

                }
                    break;
            }
        }

        @Override
        protected void error(int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_MODIFY_PASSWORD:
                {

                }
                    break;
            }
        }

    };

}
