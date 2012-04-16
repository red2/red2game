package com.five.http;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Add the {@link FakeData} for we can debug the application when the server is
 * not prepared. Maybe we should add a flag in {@link HttpConnectUtils} to
 * indicate whether we should connect to the sever or just fake the data;
 * IF THIS IS *NOT* necessary, remove this.
 * 
 * @author zhaomiao
 * 
 */
public class FakeData
{
    public static JSONObject fakeCastleInfo()
    {
        JSONObject result = new JSONObject();
        try
        {
            result.put("cid", "cid");
            result.put("cname", "cname");
            result.put("Location", "Location");
            result.put("Population", "Population");
            result.put("Money", "10000");
            result.put("War_money", "1100");
            result.put("desc", "decs");
            result.put("Treature", "Treature");
            result.put("Beast", "beast");
            
            JSONObject members = new JSONObject();
            for (int i = 0; i < 10; i++)
            {
                JSONObject member = new JSONObject();
                if (i == 0)
                {
                    member.put("name", "lord");
                    member.put("id", "lordid");
                    members.put("Lord", member);
                }
                else
                {
                    member.put("name", "senator" + i);
                    member.put("id", "senator id" + i);
                    members.put("senator", member);
                }
            }
            result.put("Main-members", members);
            
            JSONObject state = new JSONObject();
            state.put("join", "1");
            state.put("castle_state", "1");
            state.put("Relevant", "1");
            state.put("position", "1");
            state.put("aim", "1");
            
            result.put("state", state);
            
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }
    
}
