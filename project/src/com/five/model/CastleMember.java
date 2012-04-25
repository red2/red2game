package com.five.model;

@Deprecated
public class CastleMember
{
    private String name;
    private String id;
    private String type;
    private String sex;
    private short level;
    private String levelup;
    private int exp;
    private String element;
    private int money;
    private String sign;
    
    private String mid;
    private String mname;
    private String mtype;
    
    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * @param type
     *            the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    /**
     * @return the sex
     */
    public String getSex()
    {
        return sex;
    }
    
    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex)
    {
        this.sex = sex;
    }
    
    /**
     * @return the level
     */
    public short getLevel()
    {
        return level;
    }
    
    /**
     * @param level
     *            the level to set
     */
    public void setLevel(short level)
    {
        this.level = level;
    }
    
    /**
     * @return the levelup
     */
    public String getLevelup()
    {
        return levelup;
    }
    
    /**
     * @param levelup
     *            the levelup to set
     */
    public void setLevelup(String levelup)
    {
        this.levelup = levelup;
    }
    
    /**
     * @return the exp
     */
    public int getExp()
    {
        return exp;
    }
    
    /**
     * @param exp
     *            the exp to set
     */
    public void setExp(int exp)
    {
        this.exp = exp;
    }
    
    /**
     * @return the element
     */
    public String getElement()
    {
        return element;
    }
    
    /**
     * @param element
     *            the element to set
     */
    public void setElement(String element)
    {
        this.element = element;
    }
    
    /**
     * @return the money
     */
    public int getMoney()
    {
        return money;
    }
    
    /**
     * @param money
     *            the money to set
     */
    public void setMoney(int money)
    {
        this.money = money;
    }
    
    /**
     * @return the sign
     */
    public String getSign()
    {
        return sign;
    }
    
    /**
     * @param sign
     *            the sign to set
     */
    public void setSign(String sign)
    {
        this.sign = sign;
    }
    
    /**
     * @return the mid
     */
    public String getMid()
    {
        return mid;
    }
    
    /**
     * @param mid
     *            the mid to set
     */
    public void setMid(String mid)
    {
        this.mid = mid;
    }
    
    /**
     * @return the mname
     */
    public String getMname()
    {
        return mname;
    }
    
    /**
     * @param mname
     *            the mname to set
     */
    public void setMname(String mname)
    {
        this.mname = mname;
    }
    
    /**
     * @return the mtype
     */
    public String getMtype()
    {
        return mtype;
    }
    
    /**
     * @param mtype
     *            the mtype to set
     */
    public void setMtype(String mtype)
    {
        this.mtype = mtype;
    }
    
    /**
     * @param name
     *            the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @param id
     *            the id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    public CastleMember(String name, String id)
    {
        this.name = name;
        this.id = id;
    }
    
    public CastleMember()
    {
        
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getId()
    {
        return this.id;
    }
    
}
