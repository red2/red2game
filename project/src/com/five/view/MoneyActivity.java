package com.five.view;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.five.FiveDynasty;
import com.five.LoadingActivity;
import com.five.R;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

/**
 * 赚钱页面
 * 
 * @author a
 * 
 */
public class MoneyActivity extends Activity implements OnClickListener
{
    private static String TAG = "MoneyActivity";
    
    private Button mButtonBack;
    
    private Button mButtonWork;
    
    private Button mButtonGetMoney;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_view);
        
        //
        mButtonBack = (Button) findViewById(R.id.bt_back);
        mButtonBack.setOnClickListener(this);
        
        //
        mButtonWork = (Button) findViewById(R.id.bt_work);
        mButtonWork.setOnClickListener(this);
        
        //
        mButtonGetMoney = (Button) findViewById(R.id.bt_money);
        mButtonGetMoney.setOnClickListener(this);
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack))
        {
            finish();
        }
        if (v.equals(mButtonGetMoney))
        {
            // 收钱-联网获取
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("uid", "10004");
            hcu.post(Url.UserDoWork, HttpConnectEvent.HTTP_GET_MONEY);
        }
        if (v.equals(mButtonWork))
        {
            // 干活联网提交服务器状态
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("uid", "10004");
            hcu.addPostParmeter("jid", "打工类型");
            hcu.addPostParmeter("time", "打工时间");
            hcu.post(Url.UserDoWork, HttpConnectEvent.HTTP_USER_WORK);
        }
    }
    
    private Handler handler = new HttpHandler(MoneyActivity.this)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
        }
        
        @Override
        protected void failed(JSONObject jObject, int event)
        {
        }
        
        @Override
        protected void error(int event)
        {
        }
        
    };
    
}
