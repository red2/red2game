package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.FiveDynasty;
import com.five.LoadingActivity;
import com.five.R;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

public class PlayerListActivity extends Activity implements OnClickListener, OnItemClickListener
{
    /**
     * 玩家页面
     */
    private static String TAG = "PlayerActivity";
    
    private Button mButtonBack;
    
    private Button mButtonRefresh;
    
    private ListView mActivityListView;
    
    private List<Map<String, Object>> mActivityListData;
    
    private int type;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_list_view);
        
        //
        mButtonBack = (Button) findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(this);
        
        //
        mButtonRefresh = (Button) findViewById(R.id.btn_refesh);
        mButtonRefresh.setOnClickListener(this);
        
        //
        
        mActivityListData = new ArrayList<Map<String, Object>>();
        
        // 测试数据
        for (int j = 0; j < 3; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", "玩家昵称：小不点");
            item.put("level", "玩家级别：10级");
            item.put("att", "属性：金木水火土");
            mActivityListData.add(item);
        }
        
        mActivityListView = (ListView) findViewById(R.id.player_list_view);
        PlayerAdapter adapter = new PlayerAdapter(this);
        mActivityListView.setAdapter(adapter);
        mActivityListView.setOnItemClickListener(this);
        
        //
        type = getIntent().getIntExtra("type", FiveActivity.MORA);
    }
    
    class PlayerAdapter extends BaseAdapter
    {
        Context context;
        
        public PlayerAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return PlayerListActivity.this.mActivityListData.size();
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return Integer.valueOf(position);
        }
        
        @Override
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // TODO Auto-generated method stub
            LinearLayout linearLayout = null;
            
            TextView tvName = null;
            TextView tvLevel = null;
            TextView tvAttribute = null;
            ImageView iv = null;
            
            linearLayout = (LinearLayout) View.inflate(context, R.layout.player_list_item, null);
            
            // player name
            tvName = (TextView) linearLayout.findViewById(R.id.player_name);
            tvName.setText(PlayerListActivity.this.mActivityListData.get(position).get("name").toString());
            
            // player level
            tvLevel = (TextView) linearLayout.findViewById(R.id.player_level);
            tvLevel.setText(PlayerListActivity.this.mActivityListData.get(position).get("level").toString());
            
            // player attribute
            tvAttribute = (TextView) linearLayout.findViewById(R.id.player_attribute);
            tvAttribute.setText(PlayerListActivity.this.mActivityListData.get(position).get("att").toString());
            
            iv = (ImageView) linearLayout.findViewById(R.id.iv);
            
            return linearLayout;
        }
        
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        // TODO Auto-generated method stub
        switch (type)
        {
            case FiveActivity.MATCH:
            {
                Intent intent = new Intent();
                intent.setClass(this, TournamentActivity.class);
                startActivity(intent);
            }
                break;
            case FiveActivity.MORA:
            {
                Intent intent = new Intent();
                intent.setClass(this, MoraActivity.class);
                startActivity(intent);
            }
                break;
            case FiveActivity.THRU:
            {
                
            }
                break;
            
            default:
                break;
        }
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack))
        {
            finish();
        }
        else if (v.equals(mButtonRefresh))
        {
            // 刷新对手列表信息
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.get(Url.GetMatchPlayer, HttpConnectEvent.HTTP_GET_MATCH_PLAYER);
        }
    }
    
    private Handler handler = new HttpHandler(PlayerListActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 刷新成功
            for (int j = 0; j < 3; j++)
            {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("name", "玩家昵称：小不点");
                item.put("level", "玩家级别：10级");
                item.put("att", "属性：金木水火土");
                mActivityListData.add(item);
            }
            
            mActivityListView = (ListView) findViewById(R.id.player_list_view);
            PlayerAdapter adapter = new PlayerAdapter(PlayerListActivity.this);
            mActivityListView.setAdapter(adapter);
            mActivityListView.setOnItemClickListener(PlayerListActivity.this);
        }
        
        @Override
        protected void failed(JSONObject jObject, int event)
        {
        }
        
        @Override
        protected void error(int event)
        {
        }
        
    };
    
}
