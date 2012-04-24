package com.five.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;

import com.five.FiveApplication;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

public class KeepAliveManager
{
    private static KeepAliveManager _instance = null;
    private static Object _lock = new Object();
    private static Context context;
    
    public static KeepAliveManager getInstance(Context ctx)
    {
        synchronized (_lock)
        {
            if (_instance == null)
            {
                context = ctx;
                _instance = new KeepAliveManager();
            }
            
            return _instance;
        }
    }
    
    public static final int KA_TYPE_DEFAULT = 0;
    
    private HashMap<Integer, Timer> mTasks = new HashMap<Integer, Timer>();
    
    /**
     * 
     * @param type
     * @param frequency
     */
    public void startKeepAlive(int type, int frequency/* seconds */)
    {
        // Remove the first task, always use the new one;
        cancelKeepAlive(type);
        
        if (type == KA_TYPE_DEFAULT)
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new KeepAliveTask(), 1000 * frequency, 1000 * frequency);
            mTasks.put(KA_TYPE_DEFAULT, timer);
        }
    }
    
    public void cancelKeepAlive(int type)
    {
        if (mTasks.containsKey(type))
        {
            mTasks.get(type).cancel();
            mTasks.remove(type);
        }
    }
    
    public void cancelAllTask(){
        Iterator<Integer> it = mTasks.keySet().iterator();
        while(it.hasNext()){
            mTasks.get(it.next()).cancel();
        }
        mTasks.clear();
    }
    
    private Handler handler = new HttpHandler(context, false)
    {
        
        protected void succeed(org.json.JSONObject jObject, int event)
        {
            
            if (event == HttpConnectEvent.HTTP_KEEP_ALIVE_DEFAULT)
            {
                DataParser.getInstance().parserKeepAlive(jObject);
            }
        };
        
        protected void failed(org.json.JSONObject jObject, int event)
        {
            
            if (event == HttpConnectEvent.HTTP_KEEP_ALIVE_DEFAULT)
            {
                
            }
        };
        
        protected void error(int event)
        {
            
            if (event == HttpConnectEvent.HTTP_KEEP_ALIVE_DEFAULT)
            {
                
            }
        };
    };
    
    class KeepAliveTask extends TimerTask
    {
        
        @Override
        public void run()
        {
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            // hcu.addGetParmeter("phone", phone);
            hcu.get(Url.KeepAlive, HttpConnectEvent.HTTP_KEEP_ALIVE_DEFAULT);
            
        }
        
    }
    
}
