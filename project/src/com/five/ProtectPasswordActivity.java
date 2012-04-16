package com.five;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.DataParser;

public class ProtectPasswordActivity extends Activity implements OnClickListener
{
    /**
     * 密保问题1
     */
    Spinner spinnerQuestion1 = null;

    /**
     * 
     */
    ArrayAdapter<String> adapterQuestion1 = null;

    /**
     * 问题1
     */
    private String[] questionArray1 =
    {
            "where am i borned?", "where is my home?", "how many brothers or sisters i have?",
    };

    /**
     * 密保问题1
     */
    Spinner spinnerQuestion2 = null;

    /**
     * 
     */
    ArrayAdapter<String> adapterQuestion2 = null;

    /**
     * 问题1
     */
    private String[] questionArray2 =
    {
            "what is my job?", "what's my favourite?", "what i hate most?",
    };

    /**
     * 提交密码保护信息按钮
     */
    private Button mButtonOK;

    /**
     * 密码保护问题1
     */
    private EditText mEditTextAnswer1;


    /**
     * 密码保护问题2
     */
    private EditText mEditTextAnswer2;
    private EditText mEditTextAnswer1_repeat;
    private EditText mEditTextAnswer2_repeat;
    
    private EditText passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_protect_password);
        this.init();

    }

    /**
     * 初始化
     */
    private void init()
    {
        //
        mButtonOK = (Button) findViewById(R.id.button_ok);
        mButtonOK.setOnClickListener(this);

        //
        spinnerQuestion1 = (Spinner) findViewById(R.id.spinner_question1);
        adapterQuestion1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.questionArray1);
        adapterQuestion1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuestion1.setAdapter(adapterQuestion1);

        //
        spinnerQuestion2 = (Spinner) findViewById(R.id.spinner_question2);
        adapterQuestion2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.questionArray2);
        adapterQuestion2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuestion2.setAdapter(adapterQuestion2);

        mEditTextAnswer1 = (EditText) findViewById(R.id.et_question1);

        mEditTextAnswer2 = (EditText) findViewById(R.id.et_question2);
        
        mEditTextAnswer1_repeat = (EditText) findViewById(R.id.et_question1_repeat);
        mEditTextAnswer2_repeat = (EditText) findViewById(R.id.et_question2_repeat);
        
        passWord = (EditText) findViewById(R.id.et_input_password);
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
        if (v.equals(mButtonOK))
        {
            // 提交密码保护信息
            //首先比较答案是否一致
            if(!mEditTextAnswer1.getText().toString().equals(mEditTextAnswer1_repeat.getText().toString()))
            {
                Toast.makeText(getBaseContext(), "第一个答案不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!mEditTextAnswer2.getText().toString().equals(mEditTextAnswer2_repeat.getText().toString()))
            {
                Toast.makeText(getBaseContext(), "第二个答案不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("phone", "13488791006");
            hcu.addPostParmeter("password", passWord.getText().toString());
            hcu.addPostParmeter("answer1", mEditTextAnswer1.getText().toString());
            hcu.addPostParmeter("answer2", mEditTextAnswer2.getText().toString());
            hcu.addPostParmeter("question1", questionArray1[spinnerQuestion1.getSelectedItemPosition()]);
            hcu.addPostParmeter("question2", questionArray2[spinnerQuestion2.getSelectedItemPosition()]);
            hcu.post(Url.ProtectedPassword, HttpConnectEvent.HTTP_PROTECT_PASSWORD);
        }

    }

    /**
     * 
     */
    private Handler handler = new HttpHandler(ProtectPasswordActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_PROTECT_PASSWORD:
                {
                    //
                    DataParser.getInstance().parserProtectPassword(jObject);
                }

                    break;
            }
            ProtectPasswordActivity.this.finish();
        }

        @Override
        protected void failed(JSONObject jObject, int event)
        {
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_PROTECT_PASSWORD:
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
