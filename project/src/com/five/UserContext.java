package com.five;

/**
 * 缓存当前用户上下文，全局
 * @author zhaomiao
 *
 */
public class UserContext
{
    private static UserContext _instance = null;
    private static Object _lock = new Object();
    
    public static UserContext getInstance()
    {
        synchronized (_lock)
        {
            if (null == _instance)
            {
                _instance = new UserContext();
                
            }
         
            return _instance;
        }
    }
    
    /**
     * 当前登录用户id
     */
    private String userid ;

    /**
     * @return the userid
     */
    public String getUserid()
    {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid)
    {
        this.userid = userid;
    }

    
    
    
     
    
}
