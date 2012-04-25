package com.five.model;

import android.graphics.Bitmap;

public class UserInfo
{
    
    public UserInfo()
    {
    }
    /**
     * 头像
     */
    private Bitmap m_imgHead;

    /**
     * 用户id-char(20)
     */
    private String m_strUid;
    
    /**
     * 玩家昵称-varchar(32)
     */
    private String m_strName;
    
    /**
     * 玩家角色 enum(chi,he,wan,le)
     */
    private int m_iRole;
    
    /**
     * 玩家性别
     */
    private int m_iSex;
    
    /**
     * 玩家级别
     */
    private int m_iLevel;
    
    /**
     * 升级须需要历练
     */
    private int m_iLevelup;
    
    public int getM_iLevelup()
    {
        return m_iLevelup;
    }

    public void setM_iLevelup(int levelup)
    {
        m_iLevelup = levelup;
    }

    /**
     * 玩家历练
     */
    private int m_iExp;
    
    /**
     * 游戏排名
     */
    private int m_iRank;
    
    /**
     * 五行属性 enum(jin,mu,shui,huo,tu)
     */
    private int m_iElement;
    
    /**
     * 玩家金钱
     */
    private String m_strMoney;
    
    /**
     * 玩家签名 varchar(64)
     */
    private String m_strSignature;
    
    /**
     * 玩家积分
     */
    private String m_strIntegral;
    
    /**
     * 未读消息条数
     */
    private int m_iMsg;
    
    public String getM_strUid()
    {
        return m_strUid;
    }
    
    public void setM_strUid(String m_strUid)
    {
        this.m_strUid = m_strUid;
    }
    
    public String getM_strName()
    {
        return m_strName;
    }
    
    public void setM_strName(String m_strName)
    {
        this.m_strName = m_strName;
    }
    
    public int getM_iRole()
    {
        return m_iRole;
    }
    
    public void setM_iRole(int m_iRole)
    {
        this.m_iRole = m_iRole;
    }
    
    public int getM_iSex()
    {
        return m_iSex;
    }

    public void setM_iSex(int sex)
    {
        m_iSex = sex;
    }

    public int getM_iLevel()
    {
        return m_iLevel;
    }
    
    public void setM_iLevel(int m_iLevel)
    {
        this.m_iLevel = m_iLevel;
    }
    
    public int getM_iExp()
    {
        return m_iExp;
    }
    
    public void setM_iExp(int m_iExp)
    {
        this.m_iExp = m_iExp;
    }
    
    public int getM_iRank()
    {
        return m_iRank;
    }
    
    public void setM_iRank(int m_iRank)
    {
        this.m_iRank = m_iRank;
    }
    
    public int getM_iElement()
    {
        return m_iElement;
    }
    
    public void setM_iElement(int m_iElement)
    {
        this.m_iElement = m_iElement;
    }
    
    public String getM_strMoney()
    {
        return m_strMoney;
    }
    
    public void setM_strMoney(String m_strMoney)
    {
        this.m_strMoney = m_strMoney;
    }
    
    public String getM_strSignature()
    {
        return m_strSignature;
    }
    
    public void setM_strSignature(String m_strSignature)
    {
        this.m_strSignature = m_strSignature;
    }
    
    public String getM_strIntegral()
    {
        return m_strIntegral;
    }
    
    public void setM_strIntegral(String m_strIntegral)
    {
        this.m_strIntegral = m_strIntegral;
    }
    
    public int getM_iMsg()
    {
        return m_iMsg;
    }
    
    public void setM_iMsg(int m_iMsg)
    {
        this.m_iMsg = m_iMsg;
    }

    public Bitmap getM_imgHead()
    {
        return m_imgHead;
    }

    public void setM_imgHead(Bitmap head)
    {
        m_imgHead = head;
    }

}
