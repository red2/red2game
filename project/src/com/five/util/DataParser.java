package com.five.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.five.model.BattleInfo;
import com.five.model.Candidate;
import com.five.model.CandidateInfo;
import com.five.model.CastleBasic;
import com.five.model.CastleInfo;
import com.five.model.CastleMember;
import com.five.model.Gamer;
import com.five.model.Martyr;
import com.five.model.Message;
import com.five.model.Reservation;

public class DataParser
{
    private static final String TAG = "DataParser";
    /**
     * DataParser实例
     */
    private static DataParser instance;
    
    /**
     * 获取实例
     * 
     * @return DataParser实例
     */
    public synchronized static DataParser getInstance()
    {
        if (instance == null)
        {
            instance = new DataParser();
        }
        return instance;
    }
    
    /**
     * 解析用户信息/数据格式如下{"status":"200","item":{"uid":"10004","name":"wushengbing",
     * "type"
     * :"le","sex":"female","element":"earth","sign":null,"exp":0,"money":100
     * ,"level":1,"levelup":100}}
     */
    public void ParserUserInfo(JSONObject data)
    {
        JSONObject userInfo;
        try
        {
            if (data.has("item"))
            {
                userInfo = data.getJSONObject("item");
                if (userInfo != null)
                {
                    userInfo.getString(ConstValue.USER_INFO_UID);
                    userInfo.getString(ConstValue.USER_INFO_NAME);
                    userInfo.getString(ConstValue.USER_INFO_TYPE);
                    userInfo.getString(ConstValue.USER_INFO_SEX);
                    userInfo.getString(ConstValue.USER_INFO_ELEMENT);
                    userInfo.getString(ConstValue.USER_INFO_SIGN);
                    userInfo.getString(ConstValue.USER_INFO_EXP);
                    userInfo.getString(ConstValue.USER_INFO_MONEY);
                    userInfo.getString(ConstValue.USER_INFO_LEVEL);
                    userInfo.getString(ConstValue.USER_INFO_LEVELUP);
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 解析宠物信息
     * 
     * @param data
     */
    public void ParserPetInfo(JSONObject data)
    {
    }
    
    /**
     * 解析宠物活动
     * 
     * @param data
     */
    public void ParserPetInteractive(JSONObject data)
    {
    }
    
    /**
     * 打工
     * 
     * @param data
     */
    public void ParserDoWork(JSONObject data)
    {
        
    }
    
    /**
     * 收钱
     * 
     * @param data
     */
    public void ParserGetMoney(JSONObject data)
    {
    }
    
    /**
     * 修改宠物名称
     * 
     * @param data
     */
    public void ParserModifyPetname(JSONObject data)
    {
        
    }
    
    /**
     * 解析对手列表及对战信息
     * 
     * @param data
     */
    public void ParserChallngeInfo(JSONObject data)
    {
        JSONArray playerList;
        
        try
        {
            if (data.has(ConstValue.CHALLENGE_CHANCE))
            {
                data.get(ConstValue.CHALLENGE_CHANCE);
            }
            if (data.has(ConstValue.CHALLENGE_DATE))
            {
                data.get(ConstValue.CHALLENGE_DATE);
            }
            if (data.has(ConstValue.CHALLENGE_LIST))
            {
                playerList = data.getJSONArray(ConstValue.CHALLENGE_LIST);
                if (playerList != null && playerList.length() > 0)
                {
                    for (int i = 0; i < playerList.length(); i++)
                    {
                        JSONObject obj = playerList.getJSONObject(i);
                        if (obj != null)
                        {
                            obj.get(ConstValue.CHALLENGE_UID);
                            obj.get(ConstValue.CHALLENGE_NAME);
                            obj.get(ConstValue.CHALLENGE_ITYPE);
                            obj.get(ConstValue.CHALLENGE_DYNASTY);
                            obj.get(ConstValue.CHALLENGE_SEX);
                            obj.get(ConstValue.CHALLENGE_LEVEL);
                            obj.get(ConstValue.CHALLENGE_HEALTH);
                            obj.get(ConstValue.CHALLENGE_CHI);
                            obj.get(ConstValue.CHALLENGE_HE);
                            obj.get(ConstValue.CHALLENGE_WAN);
                            obj.get(ConstValue.CHALLENGE_LE);
                        }
                    }
                }
            }
            
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @param object
     * @return
     */
    public CastleInfo parserCastleInfo(JSONObject object)
    {
        CastleInfo info = new CastleInfo();
        JSONObject castle;
        try
        {
            castle = object.getJSONObject(ConstValue.CASTLE_INFO_CASTLE);
            if (castle != null)
            {
                if (castle.has(ConstValue.CASTLE_INFO_ID) && !castle.isNull(ConstValue.CASTLE_INFO_ID))
                {
                    info.setCid(castle.getString(ConstValue.CASTLE_INFO_ID));
                }
                else
                {
                    Log.d(TAG, "No cid found. wrong value from server!");
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_NAME) && !castle.isNull(ConstValue.CASTLE_INFO_NAME))
                {
                    info.setCname(castle.getString(ConstValue.CASTLE_INFO_NAME));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_LOCATION) && !castle.isNull(ConstValue.CASTLE_INFO_LOCATION))
                {
                    info.setLocation(castle.getString(ConstValue.CASTLE_INFO_LOCATION));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_LEVEL) && !castle.isNull(ConstValue.CASTLE_INFO_LEVEL))
                {
                    info.setLevel(castle.getString(ConstValue.CASTLE_INFO_LEVEL));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_POPULATION) && !castle.isNull(ConstValue.CASTLE_INFO_POPULATION))
                {
                    info.setPopulation(castle.getString(ConstValue.CASTLE_INFO_POPULATION));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_MONEY) && !castle.isNull(ConstValue.CASTLE_INFO_MONEY))
                {
                    info.setMoney(castle.getString(ConstValue.CASTLE_INFO_MONEY));
                }
                
                /*
                 * if (castle.has(ConstValue.CASTLE_INFO_WAR_MONEY) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_WAR_MONEY)) {
                 * info.setWar_Money
                 * (castle.getString(ConstValue.CASTLE_INFO_WAR_MONEY)); }
                 */
                
                if (castle.has(ConstValue.CASTLE_INFO_DESC) && !castle.isNull(ConstValue.CASTLE_INFO_DESC))
                {
                    info.setDesc(castle.getString(ConstValue.CASTLE_INFO_DESC));
                }
                
                /*
                 * if (castle.has(ConstValue.CASTLE_INFO_TREATURE) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_TREATURE)) {
                 * info.setTreature
                 * (castle.getString(ConstValue.CASTLE_INFO_TREATURE)); }
                 */
                
                if (castle.has(ConstValue.CASTLE_INFO_BEAST) && !castle.isNull(ConstValue.CASTLE_INFO_BEAST))
                {
                    info.setBeast(castle.getString(ConstValue.CASTLE_INFO_BEAST));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_STATUS) && !castle.isNull(ConstValue.CASTLE_INFO_STATUS))
                {
                    info.setStatus(castle.getString(ConstValue.CASTLE_INFO_STATUS));
                }
                
                if (castle.has(ConstValue.CASTLE_INFO_ENDDATE) && !castle.isNull(ConstValue.CASTLE_INFO_ENDDATE))
                {
                    info.setEndDate(castle.getString(ConstValue.CASTLE_INFO_ENDDATE));
                }
                
                /*
                 * if (castle.has(ConstValue.CASTLE_INFO_MAIN_MEMBERS) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_MAIN_MEMBERS)) {
                 * JSONArray members =
                 * castle.getJSONArray(ConstValue.CASTLE_INFO_MAIN_MEMBERS);
                 * ArrayList<CastleMember> senators = new
                 * ArrayList<CastleMember>(); for (int i = 0; i <
                 * members.length(); i++) { JSONObject obj =
                 * members.getJSONObject(i);
                 * 
                 * if (obj.has(ConstValue.CASTLE_INFO_MEMBERS_LORD)) {
                 * JSONObject lord =
                 * obj.getJSONObject(ConstValue.CASTLE_INFO_MEMBERS_LORD);
                 * 
                 * String n =
                 * lord.getString(ConstValue.CASTLE_INFO_MEMBERS_NAME); String
                 * id = lord.getString(ConstValue.CASTLE_INFO_MEMBERS_ID);
                 * info.setLord(new CastleMember(n, id)); } else { JSONObject
                 * senator =
                 * obj.getJSONObject(ConstValue.CASTLE_INFO_MEMBERS_SENATOR);
                 * String n =
                 * senator.getString(ConstValue.CASTLE_INFO_MEMBERS_NAME);
                 * String id =
                 * senator.getString(ConstValue.CASTLE_INFO_MEMBERS_ID);
                 * senators.add(new CastleMember(n, id)); } }
                 * 
                 * info.setSenator(senators); }
                 * 
                 * // State if (castle.has(ConstValue.CASTLE_INFO_STATE_JOIN) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_STATE_JOIN)) {
                 * info.setIsJoin
                 * (Boolean.parseBoolean(castle.getString(ConstValue
                 * .CASTLE_INFO_STATE_JOIN)));
                 * 
                 * }
                 * 
                 * if (castle.has(ConstValue.CASTLE_INFO_STATE_CASTLE_STATE) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_STATE_CASTLE_STATE)) {
                 * info
                 * .setCastleState(Integer.parseInt(castle.getString(ConstValue
                 * .CASTLE_INFO_STATE_CASTLE_STATE))); }
                 * 
                 * if (castle.has(ConstValue.CASTLE_INFO_STATE_RELEVANT) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_STATE_RELEVANT)) {
                 * info.
                 * setIsRelevant(Boolean.parseBoolean(castle.getString(ConstValue
                 * .CASTLE_INFO_STATE_RELEVANT))); }
                 * 
                 * if (castle.has(ConstValue.CASTLE_INFO_STATE_POSITION) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_STATE_POSITION)) {
                 * info.
                 * setPosition(Integer.parseInt(castle.getString(ConstValue.
                 * CASTLE_INFO_STATE_POSITION))); }
                 * 
                 * if (castle.has(ConstValue.CASTLE_INFO_STATE_AIM) &&
                 * !castle.isNull(ConstValue.CASTLE_INFO_STATE_AIM)) {
                 * info.setAimCastle
                 * (castle.getString(ConstValue.CASTLE_INFO_STATE_AIM)); }
                 */
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return info;
        
    }
    
    public ArrayList<CastleBasic> parserCastleList(JSONObject obj)
    {
        ArrayList<CastleBasic> list = new ArrayList<CastleBasic>();
        if (obj.has(ConstValue.CASTLE_LIST_CASTLES) && !obj.isNull(ConstValue.CASTLE_LIST_CASTLES))
        {
            try
            {
                JSONArray arr = obj.getJSONArray(ConstValue.CASTLE_LIST_CASTLES);
                for (int i = 0; i < arr.length(); i++)
                {
                    CastleBasic basic = new CastleBasic();
                    JSONObject castle = arr.getJSONObject(i);
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_CID) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_CID))
                    {
                        basic.setCid(castle.getString(ConstValue.CASTLE_LIST_CASTLE_CID));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_CNAME) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_CNAME))
                    {
                        basic.setCname(castle.getString(ConstValue.CASTLE_LIST_CASTLE_CNAME));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_LOCATION) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_LOCATION))
                    {
                        basic.setLocation(castle.getString(ConstValue.CASTLE_LIST_CASTLE_LOCATION));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_LEVEL) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_LEVEL))
                    {
                        basic.setLevel(castle.getString(ConstValue.CASTLE_LIST_CASTLE_LEVEL));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_POPULATION) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_POPULATION))
                    {
                        basic.setPopulation(castle.getInt(ConstValue.CASTLE_LIST_CASTLE_POPULATION));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_MONEY) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_MONEY))
                    {
                        basic.setMoney(castle.getLong(ConstValue.CASTLE_LIST_CASTLE_MONEY));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_DESC) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_DESC))
                    {
                        basic.setDesc(castle.getString(ConstValue.CASTLE_LIST_CASTLE_DESC));
                    }
                    
                    if (castle.has(ConstValue.CASTLE_LIST_CASTLE_BEAST) && !castle.isNull(ConstValue.CASTLE_LIST_CASTLE_BEAST))
                    {
                        basic.setBeast(castle.getString(ConstValue.CASTLE_LIST_CASTLE_BEAST));
                    }
                    
                    list.add(basic);
                }
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        return list;
        
    }
    
    @Deprecated
    public ArrayList<Candidate> parserCandidates(JSONObject obj)
    {
        ArrayList<Candidate> result = new ArrayList<Candidate>();
        if (obj.has(ConstValue.CANDIDATES_CANDIDATES) && !obj.isNull(ConstValue.CANDIDATES_CANDIDATES))
        {
            JSONArray candiadates;
            try
            {
                candiadates = obj.getJSONArray(ConstValue.CANDIDATES_CANDIDATES);
                for (int i = 0; i < candiadates.length(); i++)
                {
                    Candidate candi = new Candidate();
                    JSONObject objCandi = candiadates.getJSONObject(i);
                    candi.setCid(objCandi.getString(ConstValue.CANDIDATES_CANDIDATE_CID));
                    candi.setCname(objCandi.getString(ConstValue.CANDIDATES_CANDIDATE_CNAME));
                    candi.setCount(objCandi.getInt(ConstValue.CANDIDATES_CANDIDATE_COUNT));
                    candi.setDeclaration(objCandi.getString(ConstValue.CANDIDATES_CANDIDATE_DECLARATION));
                    
                    result.add(candi);
                }
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }
        
        return result;
    }
    
    public BattleInfo parserBattleInfo(JSONObject obj)
    {
        BattleInfo battleInfo = new BattleInfo();
        if (obj.has(ConstValue.BATTLEINFO_WAR) && !obj.isNull(ConstValue.BATTLEINFO_WAR))
        {
            try
            {
                JSONObject battle = obj.getJSONObject(ConstValue.BATTLEINFO_WAR);
                battleInfo.setAname(battle.getString(ConstValue.BATTLEINFO_WAR_ANAME));
                battleInfo.setDname(battle.getString(ConstValue.BATTLEINFO_WAR_DNAME));
                battleInfo.setAnumber(battle.getInt(ConstValue.BATTLEINFO_WAR_ANUMBER));
                battleInfo.setAmilitary(battle.getInt(ConstValue.BATTLEINFO_WAR_AMILITARY));
                battleInfo.setDnumber(battle.getInt(ConstValue.BATTLEINFO_WAR_DNUMBER));
                battleInfo.setDmilitary(battle.getString(ConstValue.BATTLEINFO_WAR_DMILITARY));
                battleInfo.setBeast(battle.getString(ConstValue.BATTLEINFO_WAR_BEAST));
                battleInfo.setBeastHealth(battle.getInt(ConstValue.BATTLEINFO_WAR_BEAST_HEALTH));
                
                if (obj.has(ConstValue.BATTLEINFO_INFO) && !obj.isNull(ConstValue.BATTLEINFO_INFO))
                {
                    JSONArray infos = obj.getJSONArray(ConstValue.BATTLEINFO_INFO);
                    for (int i = 0; i < infos.length(); i++)
                    {
                        JSONObject objInfo = infos.getJSONObject(i);
                        String camp = objInfo.getString(ConstValue.BATTLEINFO_INFO_CAMP);
                        String desc = objInfo.getString(ConstValue.BATTLEINFO_INFO_DESC);
                        String state = objInfo.getString(ConstValue.BATTLEINFO_INFO_STATE);
                        battleInfo.addInfo(camp, desc, state);
                    }
                }
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return battleInfo;
    }
    
    /**
     * 申请做堡主的请求结果 true: 申请成功； false: 资格不够
     * 
     * @param obj
     * @return
     */
    public boolean parserApplyElection(JSONObject obj)
    {
        boolean bRet = false;
        if (obj.has(ConstValue.APPLY_ELECTION_QUALIFICATION) && !obj.isNull(ConstValue.APPLY_ELECTION_QUALIFICATION))
        {
            try
            {
                bRet = obj.getBoolean(ConstValue.APPLY_ELECTION_QUALIFICATION);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return bRet;
    }
    
    /**
     * 解析获取候选人列表
     * 
     * @param obj
     * @return
     */
    public ArrayList<CandidateInfo> parserCandidateInfo(JSONObject obj)
    {
        ArrayList<CandidateInfo> result = new ArrayList<CandidateInfo>();
        if (obj.has(ConstValue.CANDIDATE_INFO_ITEMS) && !obj.isNull(ConstValue.CANDIDATE_INFO_ITEMS))
        {
            JSONArray items;
            try
            {
                items = obj.getJSONArray(ConstValue.CANDIDATE_INFO_ITEMS);
                for (int i = 0; i < items.length(); i++)
                {
                    CandidateInfo candidate = new CandidateInfo();
                    JSONObject jobj = items.getJSONObject(i);
                    candidate.setVotes(jobj.getInt(ConstValue.CANDIDATE_INFO_VOTES));
                    candidate.setDeclaration(jobj.getString(ConstValue.CANDIDATE_INFO_DECLARATION));
                    candidate.setUid(jobj.getString(ConstValue.CANDIDATE_INFO_UID));
                    candidate.setName(jobj.getString(ConstValue.CANDIDATE_INFO_NAME));
                    candidate.setType(jobj.getString(ConstValue.CANDIDATE_INFO_TYPE));
                    candidate.setSex(jobj.getString(ConstValue.CANDIDATE_INFO_SEX));
                    candidate.setDevote(jobj.getInt(ConstValue.CANDIDATE_INFO_DEVOTE));
                    
                    result.add(candidate);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            
        }
        
        return result;
    }
    
    /**
     * 获取成员列表
     * 
     * @param obj
     * @return
     */
    public ArrayList<CastleMember> parserCastleMembers(JSONObject obj)
    {
        ArrayList<CastleMember> result = new ArrayList<CastleMember>();
        
        if (obj.has(ConstValue.CASTLE_MEMBER_ITEMS) && !obj.isNull(ConstValue.CASTLE_MEMBER_ITEMS))
        {
            try
            {
                JSONArray items = obj.getJSONArray(ConstValue.CASTLE_MEMBER_ITEMS);
                for (int i = 0; i < items.length(); i++)
                {
                    JSONObject jobj = items.getJSONObject(i);
                    String uid = jobj.getString(ConstValue.CASTLE_MEMBER_UID);
                    String name = jobj.getString(ConstValue.CASTLE_MEMBER_NAME);
                    String type = jobj.getString(ConstValue.CASTLE_MEMBER_TYPE);
                    String sex = jobj.getString(ConstValue.CASTLE_MEMBER_SEX);
                    short level = (short) jobj.getInt(ConstValue.CASTLE_MEMBER_LEVEL);
                    String levelup = jobj.getString(ConstValue.CASTLE_MEMBER_LEVELUP);
                    int exp = jobj.getInt(ConstValue.CASTLE_MEMBER_EXP);
                    String element = jobj.getString(ConstValue.CASTLE_MEMBER_ELEMENT);
                    int money = jobj.getInt(ConstValue.CASTLE_MEMBER_MONEY);
                    String sign = jobj.getString(ConstValue.CASTLE_MEMBER_SIGN);
                    String mid = jobj.getString(ConstValue.CASTLE_MEMBER_MID);
                    String mname = jobj.getString(ConstValue.CASTLE_MEMBER_MNAME);
                    String mtype = jobj.getString(ConstValue.CASTLE_MEMBER_MTYPE);
                    CastleMember member = new CastleMember();
                    member.setId(uid);
                    member.setName(name);
                    member.setType(type);
                    member.setSex(sex);
                    member.setLevel(level);
                    member.setLevelup(levelup);
                    member.setExp(exp);
                    member.setElement(element);
                    member.setMoney(money);
                    member.setSign(sign);
                    member.setMid(mid);
                    member.setMname(mname);
                    member.setMtype(mtype);
                    
                    result.add(member);
                    
                }
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    /**
     * 注册
     * 
     * @param obj
     * @return
     */
    public String parserRegistDate(JSONObject obj)
    {
        String uid = null;
        try
        {
            uid = obj.getString("uid");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uid;
    }
    
    /**
     * 获取验证码
     * 
     * @param obj
     * @return
     */
    public String parserVerificationDate(JSONObject obj)
    {
        // 解析注册信息
        String verify = null;
        try
        {
            verify = obj.getString("verify");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return verify;
    }
    
    /**
     * 提交密码保护信息
     * 
     * @param obj
     * @return
     */
    public void parserProtectPassword(JSONObject obj)
    {
        // 解析密码保护信息
    }
    
    /**
     * 提修改密码
     * 
     * @param obj
     * @return
     */
    public void parserModifyPassword(JSONObject obj)
    {
        // 解析修改密码
    }
    
    /**
     * 提修改密码
     * 
     * @param obj
     * @return
     */
    public void parserModifyPhonenumber(JSONObject obj)
    {
        // 解析修改手机号
    }
    
    /**
     * 提修改密码
     * 
     * @param obj
     * @return
     */
    public String parserGetPassword(JSONObject obj)
    {
        // 获取密码
        String pw = null;
        try
        {
            pw = obj.getString("password");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pw;
    }
    
    /**
     * 判断是否第一次登录
     * 
     * @param obj
     * @return
     */
    public String parserFirstLoginDate(JSONObject jObject)
    {
        // TODO Auto-generated method stub
        String first = null;
        try
        {
            first = jObject.getString("first");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return first;
    }
    
    public String parserPhoneLoginDate(JSONObject jObject)
    {
        // TODO Auto-generated method stub
        String uid = null;
        try
        {
            JSONObject gamer = jObject.getJSONObject("gamer");
            uid = gamer.getString("uid");
            JSONObject martyr = jObject.getJSONObject("martyr");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uid;
    }
    
    public String parserNameLoginDate(JSONObject jObject)
    {
        // TODO Auto-generated method stub
        String uid = null;
        try
        {
            JSONObject gamer = jObject.getJSONObject("gamer");
            uid = gamer.getString("uid");
            JSONObject martyr = jObject.getJSONObject("martyr");
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return uid;
    }
    
    public HashMap<String, String> parserGetQuestion(JSONObject jObject)
    {
        // TODO Auto-generated method stub
        HashMap<String, String> map = new HashMap<String, String>();
        try
        {
            String protect = jObject.getString("protect");
            if (protect.equals("yes"))
            {
                String q1 = jObject.getString("question1");
                String q2 = jObject.getString("question2");
                map.put("protect", protect);
                map.put("question1", q1);
                map.put("question2", q2);
            }
            else
            {
                String pw = jObject.getString("password");
                map.put("protect", protect);
                map.put("password", pw);
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
    
    public void parserKeepAlive(JSONObject jObject)
    {
        int status = -1;
        try
        {
            if (jObject.has("status") && !jObject.isNull("status"))
            {
                status = jObject.getInt("status");
                if (status / 100 == 2)
                {
                    // means success
                    parserMessages(jObject);
                    
                }
                else
                {
                    // now maybe 400
                    String messsge = jObject.getString("message");
                    
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public ArrayList<Message> parserMessages(JSONObject jObject)
    {
        try
        {
            if (jObject.has("messages") && !jObject.isNull("messages"))
            {
                ArrayList<Message> msgs = new ArrayList<Message>();
                JSONArray array = jObject.getJSONArray("messages");
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject msg = array.getJSONObject(i);
                    String messager = msg.getString("messager");
                    String mid = msg.getString("mid");
                    long date = msg.getLong("date");
                    String content = msg.getString("content");
                    
                    Message m = new Message(messager, mid, date, content);
                    msgs.add(m);
                }
                
                return msgs;
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Reservation> parserReservations(JSONObject jObject)
    {
        try
        {
            if (jObject.has("reservations") && !jObject.isNull("reservations"))
            {
                JSONArray array = jObject.getJSONArray("reservations");
                ArrayList<Reservation> reservations = new ArrayList<Reservation>();
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject obj = array.getJSONObject(i);
                    String challenger = obj.getString("challenger");
                    String uid = obj.getString("uid");
                    long date = obj.getLong("date");
                    Reservation reserv = new Reservation(challenger, uid, date);
                    reservations.add(reserv);
                }
                
                return reservations;
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void parserApplys(JSONObject jObject)
    {
        try
        {
            if (jObject.has("applys") && !jObject.isNull("applys"))
            {
                JSONArray applys = jObject.getJSONArray("applys");
                for (int i = 0; i < applys.length(); i++)
                {
                    JSONObject obj = applys.getJSONObject(i);
                    String name = obj.getString("name");
                    String id = obj.getString("aid");
                    // TODO: handle the request
                    
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void parserResponse(JSONObject jObject)
    {
        try
        {
            if (jObject.has("responses") && !jObject.isNull("responses"))
            {
                JSONArray arr = jObject.getJSONArray("responses");
                for (int i = 0; i < arr.length(); i++)
                {
                    JSONObject obj = arr.getJSONObject(i);
                    String name = obj.getString("name");
                    String uid = obj.getString("uid");
                    String res = obj.getString("res");
                    if (res.startsWith("agree"))
                    {
                        // agree
                    }
                    else if (res.startsWith("reject"))
                    {
                        // rejected
                        
                    }
                    // TODO: handle this
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public int parserLevelUp(JSONObject jObject)
    {
        int level = -1;
        try
        {
            if (jObject.has("level") && !jObject.isNull("level"))
            {
                level = jObject.getInt("level");
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return level;
    }
    
    public int parserPetLevelUp(JSONObject jObject)
    {
        int level = -1;
        try
        {
            if (jObject.has("m_level") && !jObject.isNull("m_level"))
            {
                level = jObject.getInt("m_level");
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return level;
    }
    
    public int parserBreakRelation(JSONObject jObject)
    {
        int hasbreak = -1;
        try
        {
            if (jObject.has("break") && !jObject.isNull("break"))
            {
                hasbreak = jObject.getInt("break");
                if (hasbreak == 1)
                {
                    // Some one terminate the relation.. :(
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return hasbreak;
        
    }
    
    public int parserOwnerChanged(JSONObject jObject)
    {
        int changed = -1;
        try
        {
            if (jObject.has("owner") && !jObject.isNull("owner"))
            {
                changed = jObject.getInt("owner");
                if (changed == 1)
                {
                    // I'm not the owner. :(
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return changed;
    }
    
    public Gamer parserGamerInfo(JSONObject jObject)
    {
        Gamer gamer = new Gamer();
        try
        {
            JSONObject obj = jObject.getJSONObject("gamer");
            gamer.setName(obj.getString("name"));
            gamer.setUid(obj.getString("uid"));
            gamer.setType(obj.getString("type"));
            gamer.setSex(obj.getString("sex"));
            gamer.setLevel(obj.getInt("level"));
            gamer.setLevelup(obj.getInt("levelup"));
            gamer.setElement(obj.getString("element"));
            gamer.setExp(obj.getString("exp"));
            gamer.setMoney(obj.getInt("money"));
            gamer.setSign(obj.getString("sign"));
            
            if (jObject.has("martyr") && !jObject.isNull("martyr"))
            {
                Martyr martyr = new Martyr();
                JSONObject m = jObject.getJSONObject("martyr");
            }
            
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return gamer;
    }
    
}
