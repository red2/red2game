package com.five.http;

public class Url
{
    /**
     * host
     */
    private static String host = "http://192.168.1.4:8080/five-dynasty/v1";
    
    /**
     * 注册
     */
    public static String Regist = host + "/register?";
    /**
     * 获取验证码
     */
    public static String Verification = host + "/verification?";
    /**
     * 获取是否第一次登录
     */
    public static String FirstLogin = host + "/first/login?";
    
    /**
     * 用户名登录
     */
    public static String NameLogin = host + "/login/name?";
    /**
     * 手机号登录
     */
    public static String PhoneLogin = host + "/login/phone?";
    
    /**
     * 登录 get方式 /参数 uid=用户id----修改签名post方式sign=用户签名
     */
    public static String LoginConn = host + "/basic-info?"; 
    
    /**
     * 修改密码
     */
    public static String ModifyUserPassword = host + "/uid/password?";
    
    /**
     * 创建玩家post方式/参数uid,name,type(chi|he|wan|le),sex(male|famale)
     */
    public static String CreateUserConn = host + "/charactor?";
    
    /**
     * 获取玩家装备
     */
    public static String GetEquipment = host + "prop?uid=1&type=basic";
    
    /**
     * 获取指定城堡信息
     */
    public static String GetCastleInfo = host + "/castle/";
    
    /**
     * 获取城堡列表
     */
    public static String GetCastleList = host + "/castles/";
    
    /**
     * 造反
     */
    public static String Rebel = host + "/castle/rebel";
    
    /**
     * 获取候选人列表
     */
    public static String GetCandidates = host + "/castle/candidates";
    
    /**
     * 选举投票
     */
    public static String VoteForAdmin = host + "/castle/vote";
    
    /**
     * 攻城投票
     */
    public static String VoteForAttack = host + "/castle/battle/vote";
    
    /**
     * 获取战斗信息（造反）
     */
    public static String GetBattleInfoWhenRebel = host + "/castle/rebel/info";
    
    /**
     * 获取战斗信息（战斗）
     */
    public static String GetBattleInfo = host + "/castle/rebel/info";
    
    /**
     * 指定攻城目标
     */
    public static String VoteForAim = host + "/castle/battle/aim";
    
    /**
     * 申请加入城堡
     */
    public static String Request_Join_Castle  = host + "/castle/join";
    
    /**
     * 退出城堡
     */
    public static String Quit_Castle = host + "/castle/quit";
    
    /**
     * 申请做堡主
     */
    public static String Apply_Election = host + "/election/apply";
    
    /**
     * 获取成员列表
     */
    public static String Agree_Request = host + "/castle/members";
    
    // ----------------------------------------------------------
    /**
     * 获取比赛对手列表
     */
    public static String GetMatchPlayer = host + "/challenge?";
    
    /**
     * 修改宠物名称
     */
    public static String ModifyPetName = host + "/martyr/name?";
    
    /**
     * 宠物互动信息接口feed、sing、play、learn、abet（吃/喝/玩/乐/挑逗）post方式
     */
    public static String PetInteractive = host + "/martyr/interaction?";
    
    /**
     * 玩家打工
     */
    public static String UserDoWork = host + "/work/start?";
    
    /**
     * 玩家状态
     */
    public static String UserGetMoney = host + "/work/cash?";
    
    /**
     * 魔幻豆挑战
     */
    public static String UserGetChallenge = host + "/challenge?";
    
    
    /**
     * 提交密码保护信息
     */
    public static String ProtectedPassword= host + "/password/protect?";
    
    /**
     * 修改密码
     */
    public static String ModifyPassword = host + "/password/motify";
    
    /**
     * 修改手机号
     */
    public static String ModifyPhonenumber = host + "/phone/motify?";
    
    /**
     * 获取密码
     */
    public static String GetPassword = host + "/password/reget?";
    
    /**
     * 获取密码保护问题
     */
    public static String GetProtectQuestion = host + "/question/reget?";
    
    /**
     * 心跳
     */
    public static String KeepAlive = host + "/status";
}
