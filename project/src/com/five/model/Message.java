package com.five.model;

/**
 * Keepalive 中的消息定义
 * 
 * @author
 * 
 */
public class Message
{
    public interface MSG_STATUS
    {
        public static final int NORMAL = 0;
        public static final int UNREAD = 1;
        public static final int DELETED = 2;
    }
    
    /**
     * 留言人昵称
     */
    private String messager;
    private long date;
    private String content;
    
    private String from;
    private String to;
    private int status;
    
    /**
     * 数据库里的消息索引ID，用于删除
     */
    private int id;
    
    public Message(String from, String to, String content, long date, String messager, int status)
    {
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
        this.messager = messager;
        this.status = status;
    }
    
    /**
     * @return the messager
     */
    public String getMessager()
    {
        return messager;
    }
    
    /**
     * @param messager
     *            the messager to set
     */
    public void setMessager(String messager)
    {
        this.messager = messager;
    }
    
    /**
     * @return the date
     */
    public long getDate()
    {
        return date;
    }
    
    /**
     * @param date
     *            the date to set
     */
    public void setDate(long date)
    {
        this.date = date;
    }
    
    /**
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * @return the from
     */
    public String getFrom()
    {
        return from;
    }
    
    /**
     * @param from
     *            the from to set
     */
    public void setFrom(String from)
    {
        this.from = from;
    }
    
    /**
     * @return the to
     */
    public String getTo()
    {
        return to;
    }
    
    /**
     * @param to
     *            the to to set
     */
    public void setTo(String to)
    {
        this.to = to;
    }
    
    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * @param id
     *            the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * @return the status
     */
    public int getStatus()
    {
        return status;
    }
    
    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status)
    {
        this.status = status;
    }
}
