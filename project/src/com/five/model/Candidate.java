package com.five.model;


@Deprecated
public class Candidate
{
    private String cid  = "";
    private String cname = "";
    private int count = 0;
    private String declaration = "";
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
     * @return the count
     */
    public int getCount()
    {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(int count)
    {
        this.count = count;
    }
    /**
     * @return the declaration
     */
    public String getDeclaration()
    {
        return declaration;
    }
    /**
     * @param declaration the declaration to set
     */
    public void setDeclaration(String declaration)
    {
        this.declaration = declaration;
    }
    
    
    
}
