package com.five.http;

public class HttpConnectEvent
{
    /**
     * 登陆
     */
    public static final byte HTTP_LOGIN = 1;
    
    /**
     * 注册
     */
    public static final byte HTTP_REGIST = 2;
    
    /**
     * 注册
     */
    public static final byte HTTP_CREATE_USER = 3;
    
    /**
     * 获取各种消息
     */
    public static final byte HTTP_GET_MESSAGE = 4;
    
    /**
     * 获取城堡信息
     */
    public static final byte HTTP_GET_CASTLE_INFO = 5;
    
    /**
     * 获取城堡列表
     */
    public static final byte HTTP_GET_CASTLE_LIST = 6;
    
    /**
     * 获取比赛人月列表
     */
    public static final byte HTTP_GET_MATCH_PLAYER = 7;
    
    /**
     * 修改宠物名字
     */
    public static final byte HTTP_MODIFY_PET_NAME = 8;
    
    /**
     * 宠物互动
     */
    public static final byte HTTP_PET_INTERACTIVE = 9;
    
    /**
     * 修改用户密码
     */
    public static final byte HTTP_MODIFY_USER_PASSWORD = 10;
    
    /**
     * 修改用户签名
     */
    public static final byte HTTP_MODIFY_USER_SING = 11;
    
    /**
     * 打工
     */
    public static final byte HTTP_USER_WORK = 11;
    
    /**
     * 收钱
     */
    public static final byte HTTP_GET_MONEY = 12;
    
    /**
     * 获取猜拳对手的列表
     */
    public static final byte HTTP_GET_MORA_PLAYER = 13;
    
    /**
     * 获取21点魔幻都挑战结构
     */
    public static final byte HTTP_GET_CHALLENGE = 14;
    
    /**
     * 造反
     */
    public static final byte HTTP_REBEL = 20;
    
    /**
     * 获取候选人列表
     */
    public static final byte HTTP_GET_CANDIDATES = 21;
    
    /**
     * 选举投票 
     */
    public static final byte HTTP_VOTE_FOR_ADMIN  = 22;
    
    /**
     * 攻城投票
     */
    public static final byte HTTP_VOTE_FOR_ATTACK = 23;
    
    /**
     * 指定目标城堡 
     */
    public static final byte HTTP_AIM_CASTLE = 24;
    
    /**
     * 获取战斗信息 （战斗）
     */
    public static final byte HTTP_GET_BATTLE_INFO = 25;
    
    /**
     * 获取战斗信息 （造反）
     */
    public static final byte HTTP_GET_BATTLE_INFO_REBEL = 26;
    
    /**
     * 加入城堡
     */
    public static final byte HTTP_REQUEST_JOIN_CASTLE = 27;
    
    /**
     * 退出城堡
     */
    public static final byte HTTP_QUIT_CASTLE = 28;
    
    /**
     * 申请做堡主
     */
    public static final byte HTTP_APPLY_ELECTION = 29;
    
    /**
     * 参选人修改宣言
     */
    public static final byte HTTP_MODIRY_DECLARATION = 30;
    
    /**
     * 获取成员列表
     */
    public static final byte HTTP_GET_MEMBERS = 31;

    /**
     * 提交密码保护信息
     */
    public static final byte HTTP_PROTECT_PASSWORD = 32;

    /**
     * 修改密码
     */
    public static final byte HTTP_MODIFY_PASSWORD = 33;

    /**
     * 修改手机号
     */
    public static final byte HTTP_MODIFY_PHONENUMBER = 34;

    /**
     * 获取密码
     */
    public static final byte HTTP_GET_PASSWORD = 35;
    
    /**
     * 获取密码保护问题
     */
    public static final byte HTTP_GET_PASSWORD_QUESTION = 36;
    
    /**
     * 获取验证码
     */
    public static final byte HTTP_GET_Verfication = 37;
    /**
     * 获取第一次登录
     */
    public static final byte HTTP_First_Login = 38;
    /**
     * 电话登录
     */
    public static final byte HTTP_Phone_Login = 39;
    /**
     * 昵称登录
     */
    public static final byte HTTP_Name_Login = 40;

}
