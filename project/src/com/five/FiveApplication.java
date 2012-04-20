package com.five;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;

import com.five.db.FiveDatabase;

public class FiveApplication extends Application
{
    
    /**
     * MENU图标
     */
    public static Bitmap[] MENU_ICONS = null;
    
    /**
     * 菜单名称
     */
    public static String[] MENU_NAMES = null;
    
    /**
     * 
     */
    public static FiveDatabase mDb;
    
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }
    
    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
        
        // 数据库
        mDb = FiveDatabase.getInstance(this);
        
    }
    
}
