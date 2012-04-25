package com.five.model;

import java.util.ArrayList;

@Deprecated
public class BattleInfo
{
    private String aname = "";
    private String dname = "";
    private int anumber = 0;
    private int amilitary = 0;
    private int dnumber = 0;
    private String dmilitary = "";
    private String Beast = "";
    private int BeastHealth = 0;
    
    private ArrayList<Info> Infos = null;
    
    public void addInfo(String camp, String desc,  String state)
    {
        if(Infos == null)
        {
            Infos  = new ArrayList<Info>();
        }
        Infos.add(new Info(camp, desc, state));
    }
    
    public ArrayList<Info> getInfoList()
    {
        return Infos;        
    }
    
    public Info getInfo(int index)
    {
        if(Infos != null)
        {
            return Infos.get(index);
        }
        return null;
    }
    
    /**
     * @return the aname
     */
    public String getAname()
    {
        return aname;
    }
    
    /**
     * @param aname
     *            the aname to set
     */
    public void setAname(String aname)
    {
        this.aname = aname;
    }
    
    /**
     * @return the dname
     */
    public String getDname()
    {
        return dname;
    }
    
    /**
     * @param dname
     *            the dname to set
     */
    public void setDname(String dname)
    {
        this.dname = dname;
    }
    
    /**
     * @return the anumber
     */
    public int getAnumber()
    {
        return anumber;
    }
    
    /**
     * @param anumber
     *            the anumber to set
     */
    public void setAnumber(int anumber)
    {
        this.anumber = anumber;
    }
    
    /**
     * @return the amilitary
     */
    public int getAmilitary()
    {
        return amilitary;
    }
    
    /**
     * @param amilitary
     *            the amilitary to set
     */
    public void setAmilitary(int amilitary)
    {
        this.amilitary = amilitary;
    }
    
    /**
     * @return the dnumber
     */
    public int getDnumber()
    {
        return dnumber;
    }
    
    /**
     * @param dnumber
     *            the dnumber to set
     */
    public void setDnumber(int dnumber)
    {
        this.dnumber = dnumber;
    }
    
    /**
     * @return the dmilitary
     */
    public String getDmilitary()
    {
        return dmilitary;
    }
    
    /**
     * @param dmilitary
     *            the dmilitary to set
     */
    public void setDmilitary(String dmilitary)
    {
        this.dmilitary = dmilitary;
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
     * @return the beastHealth
     */
    public int getBeastHealth()
    {
        return BeastHealth;
    }
    
    /**
     * @param beastHealth
     *            the beastHealth to set
     */
    public void setBeastHealth(int beastHealth)
    {
        BeastHealth = beastHealth;
    }
    
    
    public class Info
    {
        private String camp = "";
        private String desc = "";
        private String state = "";
        
        public Info(String camp, String desc, String state)
        {
            this.camp = camp;
            this.desc = desc;
            this.state = state;
        }
        
        /**
         * @return the camp
         */
        public String getCamp()
        {
            return camp;
        }
        
        /**
         * @param camp
         *            the camp to set
         */
        public void setCamp(String camp)
        {
            this.camp = camp;
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
         * @return the state
         */
        public String getState()
        {
            return state;
        }
        
        /**
         * @param state
         *            the state to set
         */
        public void setState(String state)
        {
            this.state = state;
        }
    }
    
}
