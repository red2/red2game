package com.five.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.five.R;

/**
 * 好友主页
 * 
 * @author wu
 * 
 */
public class FriendActivity extends Activity implements OnClickListener
{
    private static final String TAG = "FriendActivity";

    /**
     * 返回
     */
    private Button mBtnBack;

    /**
     * 属性
     */
    private Button mBtnAttribute;

    /**
     * 私信息
     */
    private Button mBtnMessage;

    /**
     * 挑战
     */
    private Button mBtnBattle;

    /**
     * 人物动画iv
     */
    private ImageView m_ivPeople;

    /**
     * 宠物
     */
    private ImageView m_ivPet;

    /**
     * 人物动画AnimationDrawable
     */
    private AnimationDrawable m_adPeople;

    /**
     * 宠物动画AnimationDrawable
     */
    private AnimationDrawable m_adPet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_friend_view);

        // 返回按钮
        mBtnBack = (Button) findViewById(R.id.back);
        mBtnBack.setOnClickListener(this);

        // 属性按钮
        mBtnAttribute = (Button) findViewById(R.id.bt_attribute);
        mBtnAttribute.setOnClickListener(this);

        // 私信按钮
        mBtnMessage = (Button) findViewById(R.id.btn_message);
        mBtnMessage.setOnClickListener(this);

        // 挑战按钮
        mBtnBattle = (Button) findViewById(R.id.btn_battle);
        mBtnBattle.setOnClickListener(this);

        // 人物动画
        m_ivPeople = (ImageView) findViewById(R.id.animation_man_image);
        m_ivPeople.setBackgroundResource(R.anim.animation_1);
        m_ivPeople.setOnClickListener(this);
        m_adPeople = (AnimationDrawable) m_ivPeople.getBackground();
        m_adPeople.setOneShot(false);

        // 宠物
        m_ivPet = (ImageView) findViewById(R.id.animation_pet_image);
        m_ivPet.setBackgroundResource(R.anim.animation_pet);
        m_adPet = (AnimationDrawable) m_ivPet.getBackground();
        m_adPet.setOneShot(true);
        m_ivPet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mBtnBack))
        {
            finish();
        }
        else if (v.equals(mBtnMessage))
        {
            // 私信
            Intent intent = new Intent();
            intent.setClass(FriendActivity.this, SmsViewActivity.class);
            startActivity(intent);
        }
        else if (v.equals(mBtnAttribute))
        {
            // 属性
        }
        else if (v.equals(mBtnBattle))
        {
            // 挑战
        }
        else if (v.equals(m_ivPet))
        {
            //
            Intent intent = new Intent();
            intent.setClass(FriendActivity.this, PetActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        if (m_adPeople != null)
        {
            m_adPeople.start();
        }
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }

}
