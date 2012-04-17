package com.five;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.five.db.DataSharedPreferences;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.services.FiveService;
import com.five.util.MenuId;
import com.five.view.ActivityActivity;
import com.five.view.AttributeActivity;
import com.five.view.BagActivity;
import com.five.view.ConnectionsActivity;
import com.five.view.FiveActivity;
import com.five.view.MenuView;
import com.five.view.MoneyActivity;
import com.five.view.MsgActivity;
import com.five.view.MyCastleActivity;
import com.five.view.PetActivity;
import com.five.view.SmsListViewActivity;
import com.five.view.VagrantActivity;

public class FiveDynasty extends Activity implements MenuView.onButtonClickLisener, View.OnClickListener
{
    
    private static final String TAG = "FiveDynasty";
    
    /**
     * MenuBar
     */
    private MenuView menuBar;
    
    /**
     * MenuBar的id
     */
    private int[] m_arryMenuIds =
    {
            MenuId.ID_MENU_CONNECTION, MenuId.ID_MENU_CASTLE, MenuId.ID_MENU_VAGRANT, MenuId.ID_MENU_FIVE_DYNASTY, MenuId.ID_MENU_ACTIVITY
    };
    
    /**
     * 属性
     */
    Button attributeButton;
    
    /**
     * 赚钱
     */
    Button moneyButton;
    
    /**
     * 背包
     */
    Button bagButton;
    
    /**
     * 消息
     */
    Button msgButton;
    
    ImageView iv;
    
    AnimationDrawable animationDrawable;
    
    Button newButton;
    
    /**
     * 角色
     */
    Spinner spinnerRole = null;
    
    /**
     * 
     */
    ArrayAdapter<String> adapterRole = null;
    
    /**
     * 
     */
    ArrayAdapter<String> adapterTime = null;
    
    /**
     * 性别
     */
    Spinner spinnerSex = null;
    
    /**
     * 
     */
    ArrayAdapter<String> adapterSex = null;
    
    /**
     * 性别
     */
    private String[] sexArray =
    {
            "男", "女"
    };
    
    /**
     * 角色
     */
    private String[] roleArray =
    {
            "吃货", "女吃货", "水货", "女水货", "不知道"
    };
    
    /**
     * 朝代
     */
    private String[] timeArray =
    {
            "唐朝", "宋代", "清朝", "2012年", "明朝"
    };
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 顶部的状态栏
        attributeButton = (Button) findViewById(R.id.attribute);
        attributeButton.setOnClickListener(this);
        
        // 赚钱
        moneyButton = (Button) findViewById(R.id.money);
        moneyButton.setOnClickListener(this);
        
        // 背包
        bagButton = (Button) findViewById(R.id.bag);
        bagButton.setOnClickListener(this);
        
        // 消息
        msgButton = (Button) findViewById(R.id.msg);
        msgButton.setOnClickListener(this);
        
        // 创建用户
        newButton = (Button) findViewById(R.id.create);
        newButton.setOnClickListener(this);
        
        //
        iv = (ImageView) findViewById(R.id.animation_image);
        iv.setBackgroundResource(R.anim.animation_1);
        iv.setOnClickListener(this);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.setOneShot(false);
        
        // 底部的MenuBar
        menuBar = (MenuView) findViewById(R.id.gridview);
        menuBar.setListener(this);
        menuBar.setMenuId(m_arryMenuIds);
        
        // 创建并 启动服务
        Intent intent = new Intent(this, FiveService.class);
        startService(intent);
        
        // 如果是第一次运行则弹出 完善信息窗口
        if (DataSharedPreferences.getInstance().isFirstRun(this))
        {
            showComplementInfoDialog();
        }
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        if (animationDrawable != null)
        {
            animationDrawable.start();
        }
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }
    
    @Override
    public void onMenuItemClick(int menuId)
    {
        Intent intent = new Intent();
        switch (menuId)
        {
            case MenuId.ID_MENU_CONNECTION:
            {
                // 人脉图
                intent.setClass(FiveDynasty.this, ConnectionsActivity.class);
                startActivity(intent);
            }
                break;
            case MenuId.ID_MENU_CASTLE:
            {
                // 我的城堡
                intent.setClass(FiveDynasty.this, MyCastleActivity.class);
                startActivity(intent);
            }
                break;
            case MenuId.ID_MENU_VAGRANT:
            {
                // 走江湖
                intent.setClass(FiveDynasty.this, VagrantActivity.class);
                startActivity(intent);
            }
                break;
            case MenuId.ID_MENU_FIVE_DYNASTY:
            {
                // 闯五朝
                intent.setClass(FiveDynasty.this, FiveActivity.class);
                startActivity(intent);
            }
                break;
            case MenuId.ID_MENU_ACTIVITY:
            {
                // 活动
                intent.setClass(FiveDynasty.this, ActivityActivity.class);
                startActivity(intent);
            }
                break;
            
            default:
                break;
        }
    }
    
    @Override
    public void onClick(View v)
    {
        if (v.equals(iv))
        {
            // 宠物
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, PetActivity.class);
            startActivity(intent);
        }
        else if (v.equals(msgButton))
        {
            // 消息
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, MsgActivity.class);
            startActivity(intent);
            
        }
        else if (v.equals(bagButton))
        {
            // 背包
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, BagActivity.class);
            startActivity(intent);
        }
        else if (v.equals(moneyButton))
        {
            // 赚钱
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, MoneyActivity.class);
            startActivity(intent);
        }
        else if (v.equals(attributeButton))
        {
            // 属性
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, AttributeActivity.class);
            startActivity(intent);
        }
        else if (v.equals(mButtonSubmit))
        {
            // 提交
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("uid", "10004");
            hcu.addPostParmeter("name", "wushengbing");
            hcu.addPostParmeter("type", "le");
            hcu.addPostParmeter("sex", "female");
            hcu.post(Url.CreateUserConn, HttpConnectEvent.HTTP_CREATE_USER);
            
        }
        else if (v.equals(newButton))
        {
            // 随时能弹出修改信息
            // showComplementInfoDialog();
            // Intent intent = new Intent();
            // intent.setClass(FiveDynasty.this, GameTestActivity.class);
            // startActivity(intent);
            // 测试openCV
            // intent.setClass(FiveDynasty.this, Sample1Java.class);
            // startActivity(intent);
            
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, SmsListViewActivity.class);
            startActivity(intent);
            
//            UserInfo user = new UserInfo();
//            user.setM_strUid("1234");
//            user.setM_strName("name");
//            user.setM_iRole(1);
//            user.setM_iSex(0);
//            user.setM_iLevel(10);
//            user.setM_iLevelup(20);
//            user.setM_iExp(30);
//            user.setM_iElement(1);
//            user.setM_strMoney("10000");
//            user.setM_strSignature("ttttttttt");
//            FiveApplication.mDb.insertUserData(user);
//            
//            // 读取
//            Cursor cursor = FiveApplication.mDb.getUserMsg();
//            if (cursor != null)
//            {
//                if (cursor.moveToFirst())
//                {
//                    UserInfo user1 = DataTable.parseUserInfoCursor(cursor);
//                    if (user1 != null)
//                    {
//                        Log.i("uid======", "=========" + user1.getM_strUid());
//                        Log.i("name======", "=========" + user1.getM_strName());
//                        Log.i("type======", "=========" + user1.getM_iRole());
//                        Log.i("sex======", "=========" + user1.getM_iSex());
//                        Log.i("level======", "=========" + user1.getM_iLevel());
//                        Log.i("levelup======", "=========" + user1.getM_iLevelup());
//                        Log.i("exp======", "=========" + user1.getM_iExp());
//                        Log.i("element======", "=========" + user1.getM_iElement());
//                        Log.i("money======", "=========" + user1.getM_strMoney());
//                        Log.i("sign======", "=========" + user1.getM_strSignature());
//                    }
//                }
//            }
//            cursor.close();
        }
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        
        switch (keyCode)
        {
            case KeyEvent.KEYCODE_BACK:
            {
                // 创建并 启动服务
                Intent intent = new Intent(this, FiveService.class);
                stopService(intent);
                FiveDynasty.this.finish();
            }
                break;
        }
        return true;
    }
    
    private Handler handler = new HttpHandler(FiveDynasty.this)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);
            switch (event)
            {
                case HttpConnectEvent.HTTP_CREATE_USER:
                {
                    if (complementInfoDialog != null && complementInfoDialog.isShowing())
                    {
                        complementInfoDialog.dismiss();
                    }
                }
                    break;
                
                default:
                    break;
            }
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
    
    View complementInfoView;
    Dialog complementInfoDialog;
    Button mButtonSubmit;
    
    /**
     * 完善信息对话框
     */
    private void showComplementInfoDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        complementInfoView = layoutInflater.inflate(R.layout.dialog_complement_info, null);
        complementInfoDialog = new Dialog(this, R.style.NomarDialogStyle);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        complementInfoDialog.addContentView(complementInfoView, params);
        complementInfoDialog.show();
        
        //
        spinnerRole = (Spinner) complementInfoDialog.findViewById(R.id.spinner_role);
        adapterRole = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.roleArray);
        adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapterRole);
        
        spinnerSex = (Spinner) complementInfoDialog.findViewById(R.id.spinner_sex);
        adapterSex = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.sexArray);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSex.setAdapter(adapterSex);
        
        //
        mButtonSubmit = (Button) complementInfoDialog.findViewById(R.id.button_submit);
        mButtonSubmit.setOnClickListener(this);
    }
    
}