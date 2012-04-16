package com.five;

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
import android.widget.TextView;
import android.widget.Toast;

public class GetPasswordQuestionActivity extends Activity implements OnClickListener
{
    /**
     * 密码保护问题1
     */
    private EditText mEditTextAnswer1;


    /**
     * 密码保护问题2
     */
    private EditText mEditTextAnswer2;

    
    private TextView question1;
    private TextView question2;
    
    private String phone;

    /**
     * 
     */
    private Button mButtonOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_getpassword_question_view_activity);

        //
        mEditTextAnswer1 = (EditText) findViewById(R.id.et_question1);

        mEditTextAnswer2 = (EditText) findViewById(R.id.et_question2);
        


        mButtonOk = (Button) findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
        
        question1 = (TextView) findViewById(R.id.qestion1);
        question2 = (TextView) findViewById(R.id.qestion2);
        Intent intent = this.getIntent();
        String q1 = intent.getStringExtra("question1");
        String q2 = intent.getStringExtra("question2");
        question1.setText(q1);
        question2.setText(q2);
        phone = intent.getStringExtra("phone");
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
            // 获取密码

            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("phone", phone);
            hcu.addGetParmeter("answer1", mEditTextAnswer1.getText().toString());
            hcu.addGetParmeter("answer2", mEditTextAnswer2.getText().toString());
            hcu.get(Url.GetPassword, HttpConnectEvent.HTTP_GET_PASSWORD);

        }

    }

    /**
     * 
     */
    private Handler handler = new HttpHandler(GetPasswordQuestionActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_PASSWORD:
                {
                    //暂时用json来模拟 以后此处会发短信
                    String pw = DataParser.getInstance().parserGetPassword(jObject);
                }

                    break;
            }
            GetPasswordQuestionActivity.this.finish();
        }

        @Override
        protected void failed(JSONObject jObject, int event)
        {
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_PASSWORD_QUESTION:
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
