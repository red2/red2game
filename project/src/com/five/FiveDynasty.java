package com.five;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import com.five.db.DataSharedPreferences;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.services.FiveService;
import com.five.util.ConstValue;
import com.five.util.MenuId;
import com.five.view.PetActivity;
import com.five.view.SmsListViewActivity;

public class FiveDynasty extends Activity implements View.OnClickListener
{
    
    private static final String TAG = "FiveDynasty";
    
    /**
     * MenuBar
     */
    // private MenuView menuBar;
    /**
     * MenuBar的id
     */
    private int[] m_arryMenuIds =
    {
            MenuId.ID_MENU_CONNECTION, MenuId.ID_MENU_CASTLE, MenuId.ID_MENU_VAGRANT, MenuId.ID_MENU_FIVE_DYNASTY
    };
    
    /**
     * 帮助
     */
    private Button buttonHelp;
    
    /**
     * 设置
     */
    private Button buttonSetting;
    
    /**
     * 社交
     */
    private Button buttonSns;
    
    /**
     * 返米箱按钮
     */
    private Button buttonReturnRice;
    
    /**
     * 地盘按钮
     */
    private Button buttonField;
    
    /**
     * 幻世界按钮
     */
    private Button buttonWrold;
    
    /**
     * 信箱
     */
    private Button buttonMessageBox;
    
    /**
     * 动画iv
     */
    private ImageView iv;
    
    /**
     * 动画AnimationDrawable
     */
    private AnimationDrawable animationDrawable;
    
    /**
     * 角色
     */
    private Spinner spinnerRole = null;
    
    /**
     * 
     */
    private ArrayAdapter<String> adapterRole = null;
    
    /**
     * 
     */
    private ArrayAdapter<String> adapterTime = null;
    
    /**
     * 性别
     */
    private Spinner spinnerSex = null;
    
    /**
     * 
     */
    private ArrayAdapter<String> adapterSex = null;
    
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
    
    /**
     * 弹出菜单1
     */
    private PopupWindow pop1;
    
    /**
     * 弹出菜单2
     */
    private PopupWindow pop2;
    
    /**
     * 根view
     */
    private View currentView;
    
    private LinearLayout menuBar;
    
    private int temp;
    
    private int menu_item_width;
    
    private int menu_item_height;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        currentView = findViewById(R.id.root);
        
        this.init();
    }
    
    /**
     * 初始化
     */
    private void init()
    {
        temp = getResources().getDimensionPixelSize(R.dimen.temp);
        menu_item_width = getResources().getDimensionPixelSize(R.dimen.pop_menu_width);
        menu_item_height = getResources().getDimensionPixelSize(R.dimen.pop_menu_height);
        
        menuBar = (LinearLayout) findViewById(R.id.menubar);
        
        // 设置
        buttonSetting = (Button) findViewById(R.id.bt_setting);
        buttonSetting.setOnClickListener(this);
        
        // 帮助
        buttonHelp = (Button) findViewById(R.id.bt_help);
        buttonHelp.setOnClickListener(this);
        
        // 社交
        buttonSns = (Button) findViewById(R.id.bt_sns);
        buttonSns.setOnClickListener(this);
        
        //
        buttonReturnRice = (Button) findViewById(R.id.bt_return_rice);
        buttonReturnRice.setOnClickListener(this);
        
        //
        buttonField = (Button) findViewById(R.id.bt_field);
        buttonField.setOnClickListener(this);
        
        //
        buttonWrold = (Button) findViewById(R.id.bt_world);
        buttonWrold.setOnClickListener(this);
        
        //
        iv = (ImageView) findViewById(R.id.animation_image);
        iv.setBackgroundResource(R.anim.animation_1);
        iv.setOnClickListener(this);
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.setOneShot(false);
        
        // 创建并 启动服务
        Intent intent = new Intent(this, FiveService.class);
        startService(intent);
        
        // 如果是第一次运行则弹出 完善信息窗口
        if (DataSharedPreferences.getInstance().isFirstRun(this))
        {
            showComplementInfoDialog();
        }
        
        // 社交
        View view = this.getLayoutInflater().inflate(R.layout.pop_menu1, null);
        pop1 = new PopupWindow(view, menu_item_width * 4 + temp, menu_item_height);
        pop1.setOutsideTouchable(false);
        pop1.setBackgroundDrawable(new BitmapDrawable());
        pop1.setFocusable(true);
        pop1.setTouchInterceptor(new OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getY() > (ConstValue.SCREENHEIGHT - menu_item_height))
                {
                    // 菜单关闭
                    if (pop1.isShowing())
                    {
                        pop1.dismiss();
                    }
                }
                return false;
            }
        });
        
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.popwindow);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(menu_item_width * 4, menu_item_height);
        linearLayout.setLayoutParams(params);
        
        //
        buttonMessageBox =  (Button) view.findViewById(R.id.bt_message_box);
        buttonMessageBox.setOnClickListener(this);
        
        // 返米箱
        View view1 = this.getLayoutInflater().inflate(R.layout.pop_menu2, null);
        pop2 = new PopupWindow(view1, menu_item_width * 2 + temp, menu_item_height);
        pop2.setOutsideTouchable(false);
        pop2.setBackgroundDrawable(new BitmapDrawable());
        pop2.setFocusable(true);
        pop2.setTouchInterceptor(new OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent event)
            {
                if (event.getY() > (ConstValue.SCREENHEIGHT - menu_item_height))
                {
                    // 菜单关闭
                    if (pop2.isShowing())
                    {
                        pop2.dismiss();
                    }
                }
                return false;
            }
        });
        
        LinearLayout linearLayout1 = (LinearLayout) view1.findViewById(R.id.popwindow);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(menu_item_width * 2, menu_item_height);
        linearLayout1.setLayoutParams(params1);
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
    public void onClick(View v)
    {
        if (v.equals(iv))
        {
            // 宠物
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, PetActivity.class);
            startActivity(intent);
        }
        else if (v.equals(buttonSetting))
        {
            // 设置按钮
        }
        else if (v.equals(buttonSetting))
        {
            // 帮助按钮
        }
        else if (v.equals(buttonSns))
        {
            if (pop2 != null && pop2.isShowing())
            {
                pop2.dismiss();
            }
            // 社交按钮
            if (pop1.isShowing())
            {
                pop1.dismiss();
            }
            else
            {
                pop1.showAtLocation(currentView, Gravity.LEFT | Gravity.BOTTOM, temp, menuBar.getHeight() + temp);
            }
        }
        else if (v.equals(buttonReturnRice))
        {
            if (pop1 != null && pop1.isShowing())
            {
                pop1.dismiss();
            }
            // 返米箱
            if (pop2.isShowing())
            {
                pop2.dismiss();
            }
            else
            {
                pop2.showAtLocation(currentView, Gravity.RIGHT | Gravity.BOTTOM, 0, menuBar.getHeight() + temp);
            }
        }
        else if (v.equals(buttonMessageBox))
        {
            Intent intent = new Intent();
            intent.setClass(FiveDynasty.this, SmsListViewActivity.class);
            startActivity(intent);
        }            
        else if (v.equals(buttonField))
        {
        }
        else if (v.equals(buttonWrold))
        {
        }
        
        // else if (v.equals(mButtonSubmit))
        // {
        // // 提交
        // HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        // hcu.addPostParmeter("uid", "10004");
        // hcu.addPostParmeter("name", "wushengbing");
        // hcu.addPostParmeter("type", "le");
        // hcu.addPostParmeter("sex", "female");
        // hcu.post(Url.CreateUserConn, HttpConnectEvent.HTTP_CREATE_USER);
        //
        // }
        
        // else if (v.equals(newButton))
        // {
        // 随时能弹出修改信息
        // showComplementInfoDialog();
        // Intent intent = new Intent();
        // intent.setClass(FiveDynasty.this, GameTestActivity.class);
        // startActivity(intent);
        // 测试openCV
        // intent.setClass(FiveDynasty.this, Sample1Java.class);
        // startActivity(intent);
        //
        // UserInfo user = new UserInfo();
        // user.setM_strUid("1234");
        // user.setM_strName("name");
        // user.setM_iRole(1);
        // user.setM_iSex(0);
        // user.setM_iLevel(10);
        // user.setM_iLevelup(20);
        // user.setM_iExp(30);
        // user.setM_iElement(1);
        // user.setM_strMoney("10000");
        // user.setM_strSignature("ttttttttt");
        // FiveApplication.mDb.insertUserData(user);
        //
        // // 读取
        // Cursor cursor = FiveApplication.mDb.getUserMsg();
        // if (cursor != null)
        // {
        // if (cursor.moveToFirst())
        // {
        // UserInfo user1 = DataTable.parseUserInfoCursor(cursor);
        // if (user1 != null)
        // {
        // Log.i("uid======", "=========" + user1.getM_strUid());
        // Log.i("name======", "=========" + user1.getM_strName());
        // Log.i("type======", "=========" + user1.getM_iRole());
        // Log.i("sex======", "=========" + user1.getM_iSex());
        // Log.i("level======", "=========" + user1.getM_iLevel());
        // Log.i("levelup======", "=========" + user1.getM_iLevelup());
        // Log.i("exp======", "=========" + user1.getM_iExp());
        // Log.i("element======", "=========" + user1.getM_iElement());
        // Log.i("money======", "=========" + user1.getM_strMoney());
        // Log.i("sign======", "=========" + user1.getM_strSignature());
        // }
        // }
        // }
        // cursor.close();
        // }
        
    }
    
    /**
     * 关闭弹出菜单
     */
    private void closePopMenu()
    {
        if (pop1 != null && pop1.isShowing())
        {
            pop1.dismiss();
        }
        
        // 返米箱
        if (pop2 != null && pop2.isShowing())
        {
            pop2.dismiss();
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