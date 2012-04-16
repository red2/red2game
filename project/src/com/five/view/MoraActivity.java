package com.five.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.five.R;

public class MoraActivity extends Activity implements OnClickListener, OnTouchListener
{
    
    private ImageView imgview;
    
    private ImageView ivRock;
    
    private ImageView ivCloth;
    
    private ImageView ivKnife;
    
    private WindowManager windowManager;
    
    private WindowManager.LayoutParams layoutParams;
    
    private ImageView ivMyRock;
    
    private ImageView ivMyCloth;
    
    private ImageView ivMyKnife;
    
    private int m_intItemWidth;
    
    private int m_intItemHeight;
    
    private int[] locationRock = new int[2];
    private int[] locationColth = new int[2];
    private int[] locationKnife = new int[2];
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_mora_view);
        
        //
        windowManager = this.getWindowManager();
        layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = Gravity.TOP;
        
        layoutParams.x = 0;
        layoutParams.y = 0;
        
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        layoutParams.format = PixelFormat.TRANSLUCENT;
        layoutParams.windowAnimations = 0;
        
        //
        ivRock = (ImageView) findViewById(R.id.hava_img_rock);
        ivCloth = (ImageView) findViewById(R.id.hava_img_cloth);
        ivKnife = (ImageView) findViewById(R.id.hava_img_knife);
        
        ivCloth.setOnTouchListener(this);
        ivKnife.setOnTouchListener(this);
        ivRock.setOnTouchListener(this);
        
        //
        ivMyRock = (ImageView) findViewById(R.id.my_rock_small);
        ivMyCloth = (ImageView) findViewById(R.id.my_cloth_small);
        ivMyKnife = (ImageView) findViewById(R.id.my_knife_small);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack))
        {
            this.finish();
        }
    }
    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int act = event.getAction();
        int layoutX = (int) event.getRawX() - v.getRight() - v.getWidth();
        int layoutY = (int) event.getRawY() - v.getHeight();
        
        int realX = (int) event.getRawX();
        int realY = (int) event.getRawY();
        
        layoutParams.x = layoutX;
        layoutParams.y = layoutY;
        switch (act)
        {
            case MotionEvent.ACTION_DOWN:
            {
                ImageView newimg = new ImageView(MoraActivity.this);
                v.setDrawingCacheEnabled(true);
                Bitmap bt = v.getDrawingCache();
                newimg.setImageBitmap(bt);
                imgview = newimg;
                windowManager.addView(imgview, layoutParams);
                imgview.setVisibility(View.VISIBLE);
            }
                
                break;
            case MotionEvent.ACTION_MOVE:
            {
                // wlayout.x = rawx;
                // wlayout.y = rawy;
                windowManager.updateViewLayout(imgview, layoutParams);
            }
                break;
            case MotionEvent.ACTION_UP:
            {
                windowManager.removeView(imgview);
                
                if ((realX > locationRock[0]) && (realX < locationRock[0] + m_intItemWidth) && (realY > locationRock[1])
                        && (realY - (m_intItemHeight >> 1) < locationRock[1] + m_intItemHeight || realY < locationRock[1] + m_intItemHeight))
                {
                    ivMyRock.setImageBitmap(v.getDrawingCache());
                }
                if (realX > locationColth[0] && realX < locationColth[0] + m_intItemWidth && realY > locationColth[1]
                        && (realY - (m_intItemHeight >> 1) < locationRock[1] + m_intItemHeight || realY < locationColth[1] + m_intItemHeight))
                {
                    ivMyCloth.setImageBitmap(v.getDrawingCache());
                }
                if (realX > locationKnife[0] && realX < locationKnife[0] + m_intItemWidth && event.getRawY() > locationKnife[1]
                        && (realY - (m_intItemHeight >> 1) < locationRock[1] + m_intItemHeight || realY < locationKnife[1] + m_intItemHeight))
                {
                    ivMyKnife.setImageBitmap(v.getDrawingCache());
                }
                if (imgview != null)
                {
                    imgview.destroyDrawingCache();
                    imgview = null;
                }
            }
                break;
        }
        
        return true;
        
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        //
        ivMyRock.getLocationOnScreen(locationRock);
        ivMyCloth.getLocationOnScreen(locationColth);
        ivMyKnife.getLocationOnScreen(locationKnife);
        
        //
        m_intItemWidth = ivMyRock.getWidth();
        m_intItemHeight = ivMyRock.getHeight();
        
        super.onWindowFocusChanged(hasFocus);
    }
}
