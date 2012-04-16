package com.five.view;

import android.graphics.Bitmap;

public class MenuItem
{
    /**
     * 图标
     */
    Bitmap m_bIcon;
    
    /**
     * id
     */
    public int m_intMenuId;
    
    /**
     * 名称
     */
    public String m_strName;
    
    /**
     * 构造
     * 
     * @param bmpId
     * @param menuId
     */
    public MenuItem()
    {
    }
    
    /**
     * 构造
     * 
     * @param menuId
     * @param mBitmap
     * @param name
     */
    public MenuItem(int id, Bitmap bitmap, String name)
    {
        this.m_intMenuId = id;
        this.m_bIcon = bitmap;
        this.m_strName = name;
    }
    
}
