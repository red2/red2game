package com.five;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingActivity extends Activity implements OnClickListener
{
    /**
     * 声音
     */
    Button mButtonVoiceSwitch;
    
    /**
     * 位置
     */
    Button mButtonLocationSwithc;
    
    Button mButtonProtectPassword;
    
    Button mButtonModifyPassword;
    
    Button mButtonModifyPhoneNumber;
    
    Button mButtonLogout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_setting_activity);
        
        mButtonVoiceSwitch = (Button) findViewById(R.id.button_voice_switch);
        mButtonVoiceSwitch.setOnClickListener(this);
        
        mButtonLocationSwithc = (Button) findViewById(R.id.button_location_switch);
        mButtonLocationSwithc.setOnClickListener(this);
        
        mButtonProtectPassword = (Button) findViewById(R.id.button_protect_password);
        mButtonProtectPassword.setOnClickListener(this);
        
        mButtonModifyPassword = (Button) findViewById(R.id.button_modify_password);
        mButtonModifyPassword.setOnClickListener(this);
        
        mButtonModifyPhoneNumber = (Button) findViewById(R.id.button_modify_phonenumber);
        mButtonModifyPhoneNumber.setOnClickListener(this);
        
        mButtonLogout = (Button) findViewById(R.id.button_logout);
        mButtonLogout.setOnClickListener(this);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
            {
                this.finish();
            }
                break;
            
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onClick(View v)
    {
        if (v.equals(mButtonVoiceSwitch))
        {
            // 声音
        }
        else if (v.equals(mButtonLocationSwithc))
        {
            // 位置
        }
        else if (v.equals(mButtonProtectPassword))
        {
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, ProtectPasswordActivity.class);
            startActivity(intent);
            
        }
        else if (v.equals(mButtonModifyPassword))
        {
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, ModifyPasswordActivity.class);
            startActivity(intent);
        }
        else if (v.equals(mButtonModifyPhoneNumber))
        {
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, ModifyPhoneNumberActivity.class);
            startActivity(intent);
        }
        else if (v.equals(mButtonLogout))
        {
            // 注销
        }
    }
    
}
