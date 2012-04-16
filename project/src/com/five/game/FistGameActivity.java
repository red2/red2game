package com.five.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

import com.five.R;

public class FistGameActivity extends Activity implements OnTouchListener
{
    /**
     * 石头
     */
    ImageButton rock;
    
    /**
     * 剪刀
     */
    ImageButton knife;
    
    /**
     * 布
     */
    ImageButton cloth;
    
    /**
     * 游戏页面
     */
    FistGameView fgv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.fist_game_view_layout);
        
        // 游戏页面
        this.fgv = (FistGameView) findViewById(R.id.fist_game_view);
        
        // 石头
        rock = (ImageButton) findViewById(R.id.rock_small);
        rock.setOnTouchListener(this);
        
        // 剪刀
        knife = (ImageButton) findViewById(R.id.knife_small);
        knife.setOnTouchListener(this);
        
        // 布
        cloth = (ImageButton) findViewById(R.id.cloth_small);
        cloth.setOnTouchListener(this);
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            // TODO Auto-generated method stub
            if (v != null)
            {
                if (v.equals(rock))
                {
                    rock.setImageResource(R.drawable.rock);
                    fgv.handleObject(FistGameView.STATE_ROCK);
                }
                else if (v.equals(knife))
                {
                    knife.setImageResource(R.drawable.knife);
                    fgv.handleObject(FistGameView.STATE_KNIFE);
                }
                else if (v.equals(cloth))
                {
                    cloth.setImageResource(R.drawable.cloth);
                    fgv.handleObject(FistGameView.STATE_CLOTH);
                }
                
            }
        }
        
        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            // TODO Auto-generated method stub
            if (v != null)
            {
                if (v.equals(rock))
                {
                    rock.setImageResource(R.drawable.rock_small);
                }
                else if (v.equals(knife))
                {
                    knife.setImageResource(R.drawable.knife_small);
                }
                else if (v.equals(cloth))
                {
                    cloth.setImageResource(R.drawable.cloth_small);
                }
            }
        }
        
        return false;
    }
}
