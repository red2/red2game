package com.five.view;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.five.R;

public class PetSongActivity extends Activity implements OnClickListener
{
    private Button mButtonBack;
    
    private Button mButtonStart;
    
    private Button mButtonStop;
    
    private Button mButtonSave;
    
    private Button mButtonPlay;
    
    private ImageView iv;
    
    private AnimationDrawable animationDrawable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_pet_song);
        
        //
        mButtonBack = (Button) findViewById(R.id.btn_back);
        mButtonBack.setOnClickListener(this);
        
        mButtonStart = (Button) findViewById(R.id.button_start);
        mButtonStart.setOnClickListener(this);
        mButtonStop = (Button) findViewById(R.id.button_stop);
        mButtonStop.setOnClickListener(this);
        mButtonSave = (Button) findViewById(R.id.button_save);
        mButtonSave.setOnClickListener(this);
        mButtonPlay = (Button) findViewById(R.id.button_play);
        mButtonPlay.setOnClickListener(this);
        
        iv = (ImageView) findViewById(R.id.pet_image);
        iv.setBackgroundResource(R.anim.animation_pet);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.setOneShot(false);
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonStart))
        {
            if (animationDrawable.isRunning())
            {
                return;
            }
            else
            {
                animationDrawable.start();
            }
        }
        else if (v.equals(mButtonPlay))
        {
            if (animationDrawable.isRunning())
            {
                return;
            }
            else
            {
                animationDrawable.start();
            }
        }
        else if (v.equals(mButtonStop))
        {
            animationDrawable.stop();
        }
        else if (v.equals(mButtonSave))
        {
        }
        else if (v.equals(mButtonBack))
        {
            finish();
        }
    }
}
