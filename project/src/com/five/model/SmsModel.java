package com.five.model;

public class SmsModel
{
    public static int FROM = 0;

    public static int TO = 1;
    

    /**
     * 信息内容
     */
    private String m_strText;

    /**
     * 名称
     */
    private String m_strName;

    /**
     * 时间
     */
    private String m_strDate;

    /**
     * 类型
     */
    private int m_intType;

    public String getM_strText()
    {
        return m_strText;
    }

    public void setM_strText(String text)
    {
        m_strText = text;
    }

    public String getM_strName()
    {
        return m_strName;
    }

    public void setM_strName(String name)
    {
        m_strName = name;
    }

    public String getM_strDate()
    {
        return m_strDate;
    }

    public void setM_strDate(String date)
    {
        m_strDate = date;
    }

    public int getM_intType()
    {
        return m_intType;
    }

    public void setM_intType(int type)
    {
        m_intType = type;
    }

    public SmsModel()
    {

    }

}
