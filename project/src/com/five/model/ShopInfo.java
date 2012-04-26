package com.five.model;

public class ShopInfo
{
    
    public interface TYPE
    {
        public static final String DEFAULT = "all";
        public static final String CHI = "chi";
        public static final String HE = "he";
        public static final String WAN = "wan";
        public static final String LE = "le";
    }
    
    private int sid;
    private String name;
    private String sdesc;
    private String type;
    private String distrct;
    
    private int distance = -1;
    
    public ShopInfo()
    {
        
    }
    
    public ShopInfo(int sid, String name, String sdesc, String type, int distance)
    {
        this.sid = sid;
        this.name = name;
        this.sdesc = sdesc;
        this.type = type;
        this.distance = distance;
    }
    
    public ShopInfo(int sid, String name, String sdesc, String type, String distrct)
    {
        this.sid = sid;
        this.name = name;
        this.sdesc = sdesc;
        this.type = type;
        this.distrct = distrct;
    }
    
    public int getSid()
    {
        return sid;
    }
    
    public void setSid(int sid)
    {
        this.sid = sid;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSdesc()
    {
        return sdesc;
    }
    
    public void setSdesc(String sdesc)
    {
        this.sdesc = sdesc;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getDistrct()
    {
        return distrct;
    }
    
    public void setDistrct(String distrct)
    {
        this.distrct = distrct;
    }
    
    public int getDistance()
    {
        return distance;
    }
    
    public void setDistance(int distance)
    {
        this.distance = distance;
    }
    
}
