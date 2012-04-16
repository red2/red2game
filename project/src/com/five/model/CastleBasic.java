package com.five.model;

/**
 * Add this for the castle list.
 * we also can reuse {@link CastleInfo}, but maybe cause error for this just has 4 members;
 * @author zhaomiao
 *
 */
public class CastleBasic
{
    private String cid = "";
    
    private String cname = "";
    
    private String Location = "";
    
    private String level = "";
    
    private int population = 0;
    private long money = 0L;
    private String desc = "";
    private String beast = "";

    /**
     * @return the population
     */
    public int getPopulation()
    {
        return population;
    }

    /**
     * @param population the population to set
     */
    public void setPopulation(int population)
    {
        this.population = population;
    }

    /**
     * @return the money
     */
    public long getMoney()
    {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(long money)
    {
        this.money = money;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the beast
     */
    public String getBeast()
    {
        return beast;
    }

    /**
     * @param beast the beast to set
     */
    public void setBeast(String beast)
    {
        this.beast = beast;
    }

    /**
     * @return the cid
     */
    public String getCid()
    {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(String cid)
    {
        this.cid = cid;
    }

    /**
     * @return the cname
     */
    public String getCname()
    {
        return cname;
    }

    /**
     * @param cname the cname to set
     */
    public void setCname(String cname)
    {
        this.cname = cname;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return Location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location)
    {
        Location = location;
    }

    /**
     * @return the level
     */
    public String getLevel()
    {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level)
    {
        this.level = level;
    }
    
}
