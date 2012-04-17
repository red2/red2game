package com.five.model;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class SmsListModel
{
    /**
     * 发件人姓名
     */
    private String m_strName;
    
    /**
     * 未读条数
     */
    private String m_strNumber;
    
    /**
     * 时间
     */
    private String m_strDate;
    
    /**
     * 头像
     */
    private Bitmap m_imgHead;
    
    /**
     * 所有信息
     */
    private ArrayList<SmsModel> m_listSms;
    
    /**
     * 是否在线
     */
    private boolean isOnline;
    
    public String getM_strName()
    {
        return m_strName;
    }
    
    public void setM_strName(String m_strName)
    {
        this.m_strName = m_strName;
    }
    
    public ArrayList<SmsModel> getM_listSms()
    {
        return m_listSms;
    }

    public void setM_listSms(ArrayList<SmsModel> m_listSms)
    {
        this.m_listSms = m_listSms;
    }

    public String getM_strNumber()
    {
        return m_strNumber;
    }
    
    public void setM_strNumber(String m_strNumber)
    {
        this.m_strNumber = m_strNumber;
    }
    
    public String getM_strDate()
    {
        return m_strDate;
    }
    
    public void setM_strDate(String m_strDate)
    {
        this.m_strDate = m_strDate;
    }
    
    public Bitmap getM_imgHead()
    {
        return m_imgHead;
    }
    
    public void setM_imgHead(Bitmap m_imgHead)
    {
        this.m_imgHead = m_imgHead;
    }
}
