package com.five.view;

import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.five.R;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

public class TournamentActivity extends Activity implements OnClickListener
{
    Button mButtonBack;
    
    Button mButtonPea;
    
    Button mButtonAdd;
    
    Button mButtonChallenge;
    
    private ImageView iv;
    
    private PokerCard pokerCard;
    
    private int currentScore;
    
    private AnimationDrawable animationDrawable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_tournament_view);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
        
        mButtonPea = (Button) findViewById(R.id.btn_pea);
        mButtonPea.setOnClickListener(this);
        
        mButtonAdd = (Button) findViewById(R.id.btn_add);
        mButtonAdd.setOnClickListener(this);
        
        mButtonChallenge = (Button) findViewById(R.id.btn_challenge);
        mButtonChallenge.setOnClickListener(this);
        
        //
        iv = (ImageView) findViewById(R.id.match_image);
        iv.setBackgroundResource(R.anim.animation_pet);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.setOneShot(true);
        
        //
        pokerCard = new PokerCard();
        pokerCard.shuffle();
        pokerCard.cut();
    }
    
    @Override
    public void onClick(View v)
    {
        if (v.equals(mButtonBack))
        {
            this.finish();
        }
        else if (v.equals(mButtonAdd))
        {
            if (animationDrawable.isRunning())
            {
                animationDrawable.stop();
            }
            animationDrawable.start();
            
            // 加点
            currentScore += pokerCard.getScore(pokerCard.getCard());
            
            mButtonPea.setText("魔幻豆:" + currentScore);
            
            if (currentScore > 21)
            {
                Toast.makeText(this, "哈哈你爆了!", Toast.LENGTH_SHORT).show();
                currentScore = 0;
            }
        }
        else if (v.equals(mButtonChallenge))
        {
            if (currentScore > 21 || currentScore <= 0)
            {
                Toast.makeText(this, "当前魔幻豆数量无效!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (animationDrawable.isRunning())
            {
                animationDrawable.stop();
            }
            animationDrawable.start();
            
            // 联网
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("uid", "");
            hcu.addGetParmeter("vsid", "");
            hcu.addGetParmeter("Point", String.valueOf(currentScore));
            hcu.post(Url.UserGetChallenge, HttpConnectEvent.HTTP_GET_CHALLENGE);
            
            currentScore = 0;
          
        }
    }
    
    private Handler handler = new HttpHandler(TournamentActivity.this)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // TODO Auto-generated method stub
            super.succeed(jObject, event);
        }
        
        @Override
        protected void failed(JSONObject jObject, int event)
        {
            // TODO Auto-generated method stub
            super.failed(jObject, event);
        }
        
        @Override
        protected void error(int event)
        {
            // TODO Auto-generated method stub
            super.error(event);
        }
        
    };
    
    /**
     * 扑克牌
     */
    private class PokerCard
    {
        /**
         * 当前位置
         */
        private int mPosition;
        
        /**
         * 扑克牌数组
         */
        private int[] cards =
        {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
                49, 50, 51, 52
        };
        
        /**
         * 洗牌
         */
        public void shuffle()
        {
            int temp = 0;
            for (int j = 0; j < 3; j++)
            {
                for (int i = 0; i < 52; i++)
                {
                    int rand1 = (int) Math.floor(Math.random() * 52);
                    int rand2 = (int) Math.floor(Math.random() * 52);
                    temp = cards[rand1];
                    cards[rand1] = cards[rand2];
                    cards[rand2] = temp;
                }
            }
        }
        
        /**
         * 切牌
         */
        public void cut()
        {
            mPosition = (int) Math.floor(Math.random() * 52);
        }
        
        /**
         * 发一张
         * 
         * @return
         */
        public int getCard()
        {
            int temp = mPosition;
            mPosition = (mPosition + 1) % 52;
            return cards[temp];
        }
        
        /**
         * 得分
         * 
         * @param card
         * @return
         */
        public int getScore(int score)
        {
            int temp = score % 13;
            if (temp == 0)
            {
                temp = 13;
            }
            return temp;
        }
    }
}
