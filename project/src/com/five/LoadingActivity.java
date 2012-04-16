package com.five;

import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.five.db.DataSharedPreferences;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.util.ConstValue;
import com.five.util.DataParser;

public class LoadingActivity extends Activity implements OnClickListener
{
    /**
     * 注册按钮
     */
    private Button mButtonRegist;

    /**
     * 登录按钮
     */
    private Button mButtonLogin;

    /**
     * 获取密码
     */
    
    private Button mButtonGetPassword;

    private LinearLayout mLinearLaoutRegistLayout;
    
    private EditText NameOrPhone;
    private EditText PassWord;
    
    public String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        this.init();
    }

    /**
     * 初始话
     */
    private void init()
    {
        if (DataSharedPreferences.getInstance().isAutoLogin(LoadingActivity.this))
        {
            setContentView(R.layout.page_loading);

            // 获取信息
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addGetParmeter("uid", "138");
            hcu.addGetParmeter("myid", "138");
            hcu.get(Url.LoginConn, HttpConnectEvent.HTTP_LOGIN);
        }
        else
        {
            setContentView(R.layout.page_login);

            // 登录
            mButtonLogin = (Button) findViewById(R.id.button_confirm);
            mButtonLogin.setOnClickListener(this);

            // 马上加入
            mButtonRegist = (Button) findViewById(R.id.button_switch);
            mButtonRegist.setOnClickListener(this);

            // 重新获取密码
            mButtonGetPassword = (Button) findViewById(R.id.button_getpassword);
            mButtonGetPassword.setOnClickListener(this);

            mLinearLaoutRegistLayout = (LinearLayout) findViewById(R.id.login_LinearLayout1);
            
            NameOrPhone = (EditText) findViewById(R.id.editText_Email);     
            PassWord = (EditText) findViewById(R.id.editText_Password);
        }

        // 获取屏幕的尺寸
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ConstValue.SCREENWIDTH = displayMetrics.widthPixels;
        ConstValue.SCREENHEIGHT = displayMetrics.heightPixels;
    }

    private Handler handler = new HttpHandler(LoadingActivity.this, true)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // 自己处理成功后的操作
            super.succeed(jObject, event);

            if (loadingDialog != null && loadingDialog.isShowing())
            {
                loadingDialog.dismiss();
            }

            switch(event)
            {
                case HttpConnectEvent.HTTP_First_Login:
                    String isFirst = DataParser.getInstance().parserFirstLoginDate(jObject);
                    if(isFirst.equals("yes"))
                    {
                        //第一次
                        //进行注册
                        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
                        hcu.addPostParmeter("phone", NameOrPhone.getText().toString());
                        hcu.addPostParmeter("verification", PassWord.getText().toString());
                        hcu.post(Url.Regist, HttpConnectEvent.HTTP_REGIST);
                    }
                    else
                    {
                        //进行登录操作 通过电话号码登录
                        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
                        hcu.addGetParmeter("phone", NameOrPhone.getText().toString());
                        hcu.addGetParmeter("password", PassWord.getText().toString());
                        hcu.get(Url.PhoneLogin, HttpConnectEvent.HTTP_Phone_Login);
                    }
                    break;
                case HttpConnectEvent.HTTP_REGIST:
                    String uid = DataParser.getInstance().parserRegistDate(jObject);
                    //获得uid后需要存在本地数据库里
                    userid = uid;
                    //进入完善信息界面
                    showComplementInfoDialog();
                    break;
                case HttpConnectEvent.HTTP_CREATE_USER:
                
                    if (complementInfoDialog != null && complementInfoDialog.isShowing())
                    {
                        complementInfoDialog.dismiss();
                    }
                    // 进入主页
                    Intent intent = new Intent();
                    intent.setClass(LoadingActivity.this, FiveDynasty.class);
                    startActivity(intent);

                    // 完成
                    LoadingActivity.this.finish();                
                    break;
                case HttpConnectEvent.HTTP_Phone_Login:
                    userid = DataParser.getInstance().parserPhoneLoginDate(jObject);
                    // 进入主页
                    Intent intent1 = new Intent();
                    intent1.setClass(LoadingActivity.this, FiveDynasty.class);
                    startActivity(intent1);

                    // 完成
                    LoadingActivity.this.finish();  
                    break;
                case HttpConnectEvent.HTTP_Name_Login:
                    userid = DataParser.getInstance().parserNameLoginDate(jObject);
                    // 进入主页
                    Intent intent2 = new Intent();
                    intent2.setClass(LoadingActivity.this, FiveDynasty.class);
                    startActivity(intent2);

                    // 完成
                    LoadingActivity.this.finish();  
                    break;
            }
        }

        /*
         * (non-Javadoc)
         * @see com.five.http.HttpHandler#failed(org.json.JSONObject, int)
         * 此函数 中目前出错扔能进入主页面 为了无法联网时仍能调试 当服务器稳定后把此处进入代码删除，只留下异常处理代码
         */
        @Override
        protected void failed(JSONObject jObject, int event)
        {

            if (loadingDialog != null && loadingDialog.isShowing())
            {
                loadingDialog.dismiss();
            }

            // 进入主页当服务器出错时候进入
            Intent intent = new Intent();
            intent.setClass(LoadingActivity.this, FiveDynasty.class);
            startActivity(intent);

            // 完成
            LoadingActivity.this.finish();
        }


        /*
         * (non-Javadoc)
         * @see com.five.http.HttpHandler#failed(org.json.JSONObject, int)
         * 此函数 中目前出错扔能进入主页面 为了无法联网时仍能调试 当服务器稳定后把此处进入代码删除，只留下异常处理代码
         */
        @Override
        protected void error(int event)
        {
            if (loadingDialog != null && loadingDialog.isShowing())
            {
                loadingDialog.dismiss();
            }
            // 进入主页当服务器出错时候进入
            Intent intent = new Intent();
            intent.setClass(LoadingActivity.this, FiveDynasty.class);
            startActivity(intent);

            // 完成
            LoadingActivity.this.finish();
        }

    };

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonLogin))
        {    
            //验证输入是否为空 
            if(NameOrPhone.getText().toString().equals("") || PassWord.getText().toString().equals(""))
            {
                Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            // 加载框
            showLoadingDialog();
            //是否是纯数字
            String nameOrPhone = NameOrPhone.getText().toString();
            if(isNumeric(nameOrPhone))
            {
                //验证是否第一次登录
                HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
                hcu.addGetParmeter("phone", NameOrPhone.getText().toString());
                hcu.get(Url.FirstLogin, HttpConnectEvent.HTTP_First_Login);
            }
            else
            {
                //用户登录
                HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
                hcu.addGetParmeter("name", NameOrPhone.getText().toString());
                hcu.addGetParmeter("password", PassWord.getText().toString());
                hcu.get(Url.NameLogin, HttpConnectEvent.HTTP_Name_Login);
            }           
        }
        else if (v.equals(mButtonRegist))
        {
            // // 加载框
            // showLoadingDialog();
            //
            // // 联网注册
            // HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            // hcu.addPostParmeter("uid", "10004");
            // hcu.addPostParmeter("name", "wushengbing");
            // hcu.addPostParmeter("type", "le");
            // hcu.addPostParmeter("sex", "female");
            // hcu.post(Url.CreateUserConn, HttpConnectEvent.HTTP_CREATE_USER);

            // 进入注册页面

            Intent intent = new Intent();
            intent.setClass(LoadingActivity.this, RegistActivity.class);
            startActivity(intent);

        }
        else if (v.equals(mButtonGetPassword))
        {
            // 进入获取密码页面
            Intent intent = new Intent();
            intent.setClass(LoadingActivity.this, GetPassworActivity.class);
            startActivity(intent);
        }
        else if (v.equals(mButtonSubmit))
        {
            // 提交
            if(UserName.getText().toString().equals("") )
            {
                Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            if(isNumeric(UserName.getText().toString()))
            {
                Toast.makeText(getBaseContext(), "输入不能为纯数字", Toast.LENGTH_SHORT).show();
                return;
            }
            HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
            hcu.addPostParmeter("uid", userid);
            hcu.addPostParmeter("name", UserName.getText().toString());
            hcu.addPostParmeter("type", "chi");
            hcu.addPostParmeter("sex", "male");
            hcu.post(Url.CreateUserConn, HttpConnectEvent.HTTP_CREATE_USER);
            
        }

    }

    View loadingDialogView;
    Dialog loadingDialog;

    /**
     * 加载
     */
    private void showLoadingDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        loadingDialogView = layoutInflater.inflate(R.layout.dialog_loading, null);
        loadingDialog = new Dialog(this, R.style.loadingDialogStyle);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        loadingDialog.addContentView(loadingDialogView, params);
        loadingDialog.show();

    }
    /*
     * 判断字符串是否是正整数
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*"); 
        return pattern.matcher(str).matches();    
    }
    
    View complementInfoView;
    Dialog complementInfoDialog;
    Button mButtonSubmit;
    /**
     * 角色
     */
    Spinner spinnerRole = null;
    /**
     * 
     */
    ArrayAdapter<String> adapterRole = null;
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
    EditText UserName;
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
        
        UserName = (EditText) complementInfoDialog.findViewById(R.id.et_user_name);
    }
}
