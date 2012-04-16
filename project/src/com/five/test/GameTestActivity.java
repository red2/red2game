package com.five.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.five.R;

public class GameTestActivity extends Activity implements OnClickListener
{
    
    private GameView mAgileBuddyView;
    
    Button bLeft;
    Button bRight;
    Button bStop;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 禁止屏幕休眠
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // 全屏幕
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.game_test);
        
        mAgileBuddyView = (GameView) findViewById(R.id.agile_buddy);
        
        bLeft = (Button) findViewById(R.id.left);
        bLeft.setOnClickListener(this);
        bRight = (Button) findViewById(R.id.right);
        bRight.setOnClickListener(this);
        bStop = (Button) findViewById(R.id.stop);
        bStop.setOnClickListener(this);
    }
    
    @Override
    public void finish()
    {
        // 注销重力感应监听
        super.finish();
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(bLeft))
        {
            mAgileBuddyView.handleMoving(1);
        }
        else if (v.equals(bRight))
        {
            mAgileBuddyView.handleMoving(2);
        }
        else if (v.equals(bStop))
        {
            mAgileBuddyView.handleMoving(3);
        }
        
    }
}