package com.five.view;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.five.R;
import com.five.game.FistGameActivity;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.DataParser;

/**
 * 闯五朝
 * 
 * @author a
 * 
 */
public class FiveActivity extends Activity implements OnClickListener
{
    private static String TAG = "FiveActivity";
    
    /**
     * 猜拳
     */
    public static final int MORA = 0;
    
    /**
     * 穿越
     */
    public static final int THRU = 1;
    
    /**
     * 竞技
     */
    public static final int MATCH = 2;
    
    private Button mButtonBack;
    
    private Button mButtonFiveDynasty;
    
    private Button mButtonArena;
    
    private Button mButtonMatch;
    
    private Button mTestButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_halls);
        
        //
        mButtonBack = (Button) findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(this);
        mButtonFiveDynasty = (Button) findViewById(R.id.btn_five_dynasty);
        mButtonFiveDynasty.setOnClickListener(this);
        mButtonArena = (Button) findViewById(R.id.btn_arena);
        mButtonArena.setOnClickListener(this);
        mButtonMatch = (Button) findViewById(R.id.btn_match);
        mButtonMatch.setOnClickListener(this);
        
        //
        mTestButton = (Button) findViewById(R.id.hall_btn_bourse);
        mTestButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonFiveDynasty))
        {
            // 联网测试
            // new
            // HttpConnectionUtils(handler).get("http://www.red2game.com/five-dynasty/v1/character?uid=4&name=red&type=chi&sex=male");
        }
        else if (v.equals(mButtonArena))
        {
            // 获取吹牛对手列表信息
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.get(Url.GetMatchPlayer, HttpConnectEvent.HTTP_GET_MORA_PLAYER);
            
        }
        else if (v.equals(mButtonMatch))
        {
            // 获取竞技对手列表信息
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.get(Url.GetMatchPlayer, HttpConnectEvent.HTTP_GET_MATCH_PLAYER);
        }
        else if (v.equals(mButtonBack))
        {
            this.finish();
        }
        else if (v.equals(mTestButton))
        {
            // 游戏测试
            Intent intent = new Intent();
            intent.setClass(this, FistGameActivity.class);
            startActivity(intent);
        }
        
    }
    
    private Handler handler = new HttpHandler(FiveActivity.this)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_MATCH_PLAYER:
                {
                    DataParser.getInstance().ParserChallngeInfo(jObject);
                    
                    // 竞技
                    Intent intent = new Intent();
                    intent.putExtra("type", MATCH);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                }
                    
                    break;
                case HttpConnectEvent.HTTP_GET_MORA_PLAYER:
                {
                    DataParser.getInstance().ParserChallngeInfo(jObject);
                    
                    // 吹牛
                    Intent intent = new Intent();
                    intent.putExtra("type", MORA);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                    
                }
                    
                default:
                    break;
            }
        }
        
        @Override
        protected void failed(JSONObject jObject, int event)
        {
            // TODO Auto-generated method stub
            super.failed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_MATCH_PLAYER:
                {
                    // 竞技测试情况下进入
                    Intent intent = new Intent();
                    intent.putExtra("type", MATCH);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                }
                    
                    break;
                case HttpConnectEvent.HTTP_GET_MORA_PLAYER:
                {
                    // 吹牛
                    Intent intent = new Intent();
                    intent.putExtra("type", MORA);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                    
                }
                    
                default:
                    break;
            }
        }
        
        @Override
        protected void error(int event)
        {
            // TODO Auto-generated method stub
            super.error(event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_MATCH_PLAYER:
                {
                    // 竞技测试情况下进入
                    Intent intent = new Intent();
                    intent.putExtra("type", MATCH);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                }
                    
                    break;
                case HttpConnectEvent.HTTP_GET_MORA_PLAYER:
                {
                    // 吹牛
                    Intent intent = new Intent();
                    intent.putExtra("type", MORA);
                    intent.setClass(FiveActivity.this, PlayerListActivity.class);
                    startActivity(intent);
                    
                }
                    
                default:
                    break;
            }
        }
    };
}
