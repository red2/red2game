package com.five.model;

/**
 * Keepalive 中的挑战信息定义
 * 
 * @author 
 *
 */
public class Reservation
{
    private String challenger;
    private String cid;
    private long date;
    
    public Reservation(String challenger, String cid, long date)
    {
        this.challenger = challenger;
        this.cid = cid;
        this.date = date;
    }
    
    public String getChallenger()
    {
        return challenger;
    }
    public void setChallenger(String challenger)
    {
        this.challenger = challenger;
    }
    public String getCid()
    {
        return cid;
    }
    public void setCid(String cid)
    {
        this.cid = cid;
    }
    public long getDate()
    {
        return date;
    }
    public void setDate(long date)
    {
        this.date = date;
    }
    
}
