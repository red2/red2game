package com.five;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.five.db.FiveDatabase;
import com.five.view.MenuItem;

public class FiveApplication extends Application
{
    
    /**
     * MenuItem数组
     */
    public static MenuItem[] MENU_ITEMS = null;
    
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
        
        int size = 0;
        
        if (MENU_ICONS == null)
        {
            MENU_NAMES = getResources().getStringArray(R.array.menu_name);
            
            size = MENU_NAMES.length;
            
            MENU_ICONS = new Bitmap[size];
            
            MENU_ITEMS = new MenuItem[size];
        }
        
        // 初始化主菜单
        int index = 0;
        
        // 人脉图
        MENU_ICONS[index] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_connection);
        index++;
        
        // 我的城堡
        MENU_ICONS[index] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_my_castle);
        index++;
        
        // 走江湖
        MENU_ICONS[index] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_vagrant);
        index++;
        
        // 闯五朝代
        MENU_ICONS[index] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_five_dynasty);
        index++;
        
        // 活动
        MENU_ICONS[index] = BitmapFactory.decodeResource(getResources(), R.drawable.menu_activity);
        index++;
        
        for (int i = 0; i < size; i++)
        {
            MENU_ITEMS[i] = new MenuItem(i, MENU_ICONS[i], MENU_NAMES[i]);
        }
    }
}
