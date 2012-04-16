package com.five.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class DataSharedPreferences
{
    private static final String TAG = "DataSharedPreferences";
    
    /**
     * 用户id
     */
    public static String KEY_USERID = "user_id";
    
    /**
     * 用户id
     */
    private String user_id = "-1";
    
    /**
     * 是否记住密码 自动登录
     */
    public static String KEY_AUTO_LOGIN = "is_auto_login";
    
    private boolean is_auto_login = false;
    
    /**
     * 是否是首次运行
     */
    public static String KEY_FIRST_RUN = "is_first_run";
    
    private Boolean is_first_run = true;
    
    /**
     * DataParser实例
     */
    private static DataSharedPreferences instance;
    
    /**
     * 获取单例模型
     * 
     * @return
     */
    public synchronized static DataSharedPreferences getInstance()
    {
        if (instance == null)
        {
            instance = new DataSharedPreferences();
        }
        return instance;
    }
    
    /**
     * 加载默认设置信息
     * 
     * @param context
     */
    public String getUserId(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(KEY_USERID, "0");
    }
    
    /**
     * 是否是第一次运行
     * 
     * @param context
     */
    public boolean isFirstRun(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(KEY_FIRST_RUN, true);
    }
    
    /**
     * 是否自动登录
     * 
     * @param context
     */
    public boolean isAutoLogin(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(KEY_AUTO_LOGIN, false);
    }
    
    /**
     * 保存数据
     * 
     * @param context
     * @param key
     * @param value
     */
    public void saveSetting(Context context, String key, Object value)
    {
        SharedPreferences.Editor editor = null;
        editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        
        if (key.equals(KEY_USERID))
        {
            // 用户id
            user_id = (String) value;
            editor.putString(key, value.toString());
        }
        if (key.equals(KEY_FIRST_RUN))
        {
            // 是否是首次运行
            is_first_run = (Boolean) value;
            editor.putBoolean(key, is_first_run);
        }
        if (key.equals(KEY_AUTO_LOGIN))
        {
            // 是否记住密码下次自动登录
            is_auto_login = (Boolean) value;
            editor.putBoolean(key, is_auto_login);
        }
        editor.commit();
    }
}
