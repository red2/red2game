package com.five.model;

import java.util.ArrayList;

/**
 * Just copy from the document, the var type need to be fixed, like 'money' maybe long is better;
 * @author Michael
 *
 */
@Deprecated
public class CastleInfo
{    
    private String cid = "";
    private String cname = "";
    private String Location = "";
    private String level = "";
    private String Population = "";
    private String Money = "";
    private String War_Money = "";
    private String desc = "";
    private String Treature = "";
    private String Beast = "";
    private String status;
    private String endDate;
    private CastleMember Lord = null;
    private ArrayList<CastleMember> senator = new ArrayList<CastleMember>();
    
    // State
    private boolean mIsJoin = false;
    private int mCastleState = 0;
    private boolean mIsRelevant = false;
    private int mPosition = 0;
    private String mAim = "";
    
    /**
     * @return the cid
     */
    public String getCid()
    {
        return cid;
    }
    
    /**
     * @param cid
     *            the cid to set
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
     * @param cname
     *            the cname to set
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
     * @param location
     *            the location to set
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
     * @param level
     *            the level to set
     */
    public void setLevel(String level)
    {
        this.level = level;
    }
    
    /**
     * @return the population
     */
    public String getPopulation()
    {
        return Population;
    }
    
    /**
     * @param population
     *            the population to set
     */
    public void setPopulation(String population)
    {
        Population = population;
    }
    
    /**
     * @return the money
     */
    public String getMoney()
    {
        return Money;
    }
    
    /**
     * @param money
     *            the money to set
     */
    public void setMoney(String money)
    {
        Money = money;
    }
    
    /**
     * @return the war_Money
     */
    public String getWar_Money()
    {
        return War_Money;
    }
    
    /**
     * @param war_Money
     *            the war_Money to set
     */
    public void setWar_Money(String war_Money)
    {
        War_Money = war_Money;
    }
    
    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }
    
    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    /**
     * @return the treature
     */
    public String getTreature()
    {
        return Treature;
    }
    
    /**
     * @param treature
     *            the treature to set
     */
    public void setTreature(String treature)
    {
        Treature = treature;
    }
    
    /**
     * @return the beast
     */
    public String getBeast()
    {
        return Beast;
    }
    
    /**
     * @param beast
     *            the beast to set
     */
    public void setBeast(String beast)
    {
        Beast = beast;
    }
    
    /**
     * @return the load
     */
    public CastleMember getLord()
    {
        return Lord;
    }
    
    /**
     * @param load
     *            the load to set
     */
    public void setLord(CastleMember lord)
    {
        Lord = lord;
    }
    
    /**
     * @return the senator
     */
    public ArrayList<CastleMember> getSenator()
    {
        return senator;
    }
    
    /**
     * @param senator
     *            the senator to set
     */
    public void setSenator(ArrayList<CastleMember> senator)
    {
        this.senator = senator;
    }
    
 // State
    public void setIsJoin(boolean v)
    {
        mIsJoin = v;
    }
    
    public boolean isJoin()
    {
        return mIsJoin;
    }
    
    public void setCastleState(int state)
    {
        mCastleState = state;
    }
    
    public int getCastleState()
    {
        return mCastleState;
    }
    
    public void setIsRelevant(boolean v)
    {
        mIsRelevant = v;        
    }
    
    public boolean isRelevant()
    {
        return mIsRelevant;
    }
    
    public void setPosition(int position)
    {
        mPosition = position;
    }
    
    public int getPosition()
    {
        return mPosition;
    }
    
    public void setAimCastle(String aim)
    {
        mAim = aim;
    }
    
    public String getAimCastle()
    {
        return mAim;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return the endDate
     */
    public String getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    
}
