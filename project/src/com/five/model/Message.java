package com.five.model;

/**
 * Keepalive 中的消息定义
 * @author 
 *
 */
public class Message
{
    /**
     * 留言人昵称
     */
    private String messager;
    
    private String mid ;
    private long date;
    private String content;
    
    public Message(String messager, String mid, long date, String content)
    {
        this.messager = messager;
        this.mid = mid;
        this.date = date;
        this.content = content;
    }
    
    public String getMessager()
    {
        return messager;
    }
    public void setMessager(String messager)
    {
        this.messager = messager;
    }
    public String getMid()
    {
        return mid;
    }
    public void setMid(String mid)
    {
        this.mid = mid;
    }
    public long getDate()
    {
        return date;
    }
    public void setDate(long date)
    {
        this.date = date;
    }
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }
    
    
    
}
