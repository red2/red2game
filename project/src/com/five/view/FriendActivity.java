package com.five.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.five.R;

public class FriendActivity extends Activity
{
    private static final String TAG = "FriendActivity";
    private Button mBtnBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_friend_view);
        
        mBtnBack = (Button) findViewById(R.id.back);
        if(mBtnBack != null){
            mBtnBack.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    finish();
                }
            });
        }
    }
    
}
