package com.five.db;

import android.provider.BaseColumns;

public class DataTable
{
    
    public interface TABLE_GAMER extends BaseColumns
    {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "gamer";
        
        /**
         * id
         */
        public static final String _ID = "_id";
        
        /**
         * id
         */
        public static final String UID = "uid";
        
        /**
         * 昵称
         */
        public static final String NAME = "name";
        
        /**
         * 角色
         */
        public static final String TYPE = "type";
        
        /**
         * 性别
         */
        public static final String SEX = "sex";
        
        /**
         * 级别
         */
        public static final String LEVEL = "level";
        
        /**
         * 升级所需历练
         */
        public static final String LEVELUP = "levelup";
        
        /**
         * 当前经验历练
         */
        public static final String EXP = "exp";
        
        /**
         * 五行属性
         */
        public static final String ELEMENT = "element";
        
        /**
         * 金钱
         */
        public static final String MONEY = "money";
        
        /**
         * 签名
         */
        public static final String SIGN = "sign";
        
        public static final String MID = "mid";
        
        /**
         * 创建数据库信息表
         */
        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + "  integer primary key autoincrement , " + UID + " text, " + NAME + " text," + TYPE + " text," + SEX
                + " text," + LEVEL + " text," + LEVELUP + " text," + EXP + " text," + ELEMENT + " text," + MONEY + " text, " + SIGN + " text," + MID + " INTEGER DEFAULT 0 " + ")";
        
    }
    
    /**
     * 登录用户区别于其他玩家，单独建表
     * 
     * @author
     * 
     */
    public interface TABLE_USERINFO extends BaseColumns
    {
        /**
         * 表名
         */
        public static final String TABLE_NAME = "userinfo";
        
        /**
         * id
         */
        public static final String _ID = "_id";
        
        /**
         * id
         */
        public static final String UID = "uid";
        
        /**
         * 昵称
         */
        public static final String NAME = "name";
        
        /**
         * 角色
         */
        public static final String TYPE = "type";
        
        /**
         * 性别
         */
        public static final String SEX = "sex";
        
        /**
         * 级别
         */
        public static final String LEVEL = "level";
        
        /**
         * 升级所需历练
         */
        public static final String LEVELUP = "levelup";
        
        /**
         * 当前经验历练
         */
        public static final String EXP = "exp";
        
        /**
         * 五行属性
         */
        public static final String ELEMENT = "element";
        
        /**
         * 金钱
         */
        public static final String MONEY = "money";
        
        /**
         * 签名
         */
        public static final String SIGN = "sign";
        
        public static final String MID = "mid";
        
        /**
         * 创建数据库信息表
         */
        public static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + "  integer primary key autoincrement , " + UID + " text, " + NAME + " text," + TYPE + " text," + SEX
                + " text," + LEVEL + " text," + LEVELUP + " text," + EXP + " text," + ELEMENT + " text," + MONEY + " text, " + SIGN + " text," + MID + " INTEGER DEFAULT 0 " + ")";
    }
    
    /**
     * 消息表
     * 
     * @author
     * 
     */
    public interface TABLE_MESSAGE extends BaseColumns
    {
        public static final String TABLE_NAME = "messages";
        public static final String FROM = "from";
        public static final String TO = "to";
        public static final String CONTENT = "content";
        public static final String DATE = "date";
        
        /**
         * NORMAL = 0;
         * UNREAD = 1; 
         * DELETED = 2;
         */
        public static final String STATUS = "status";
        
        /**
         * 发出的和收到的消息都存储对方UID，标注一个聊天序列
         */
        public static final String THREADID = "threadid";
        
        /**
         * 对方昵称 如果不在联系人表的话，才有意义，不然没必要
         */
        public static final String MESSAGER = "messager";
        
        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FROM + " TEXT," + TO + " TEXT," + CONTENT
                + " TEXT," + DATE + " TEXT," + MESSAGER + " TEXT," + THREADID + " TEXT " + STATUS + " INTEGER DEFAULT 1" +  ")";
        
    }
    
    /**
     * 宠物信息
     * 
     * @author 
     * 
     */
    public interface TABLE_MARTYR extends BaseColumns
    {
        public static final String TABLE_NAME = "martyr";
        public static final String NAME = "name";
        public static final String MID = "mid";
        public static final String TYPE = "type";
        
        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + MID + " TEXT," + NAME + " TEXT," + TYPE
                + " TYPE " + ")";
    }
    
}