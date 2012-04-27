package com.five.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.five.R;

public class AddFriendActivity extends Activity implements OnClickListener
{
    /**
     * 返回
     */
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_add_friend_view);

        btBack = (Button) findViewById(R.id.back);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (v.equals(btBack))
        {
            finish();
        }

    }

}
