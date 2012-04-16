package com.five.services;

import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

public class FiveService extends Service implements Runnable
{
    /**
     * TAG标志
     */
    private static final String TAG = "FiveService";
    
    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }
    
    @Override
    public void onDestroy()
    {
        handler.removeCallbacks(this);
        // TODO Auto-generated method stub
        super.onDestroy();
    }
    
    @Override
    public void onStart(Intent intent, int startId)
    {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        handler.postDelayed(this, 6 * 10 * 1000);
    }
    
    @Override
    public void run()
    {
        // 获取信息测试心跳联网 暂时是5秒钟连一次
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("uid", "10004");
        hcu.get(Url.LoginConn, HttpConnectEvent.HTTP_GET_MESSAGE);
        Log.i(TAG, "run===============================");
        
        //
        handler.postDelayed(this, 6 * 10 * 1000);
    }
    
    private Handler handler = new HttpHandler(this, false, false)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            Log.i(TAG, "succeed=====================" + event);
            
            // 数据解析
        }
        
        @Override
        protected void failed(JSONObject jObject, int event)
        {
            Log.i(TAG, "failed===================" + event);
        }
        
        @Override
        protected void error(int event)
        {
            Log.i(TAG, "error====================" + event);
        }
        
    };
    
}
