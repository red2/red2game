package com.five.view;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.five.R;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;

public class PetActivity extends Activity implements View.OnClickListener
{
    /**
     * 返回
     */
    private Button mButtonBack;

    /**
     * 属性
     */
    private Button mButtonAttribute;

    /**
     * 喂食
     */
    private Button mButtonEat;

    /**
     * 挑逗
     */
    private Button mButtonPlay;

    /**
     * 嗦使
     */
    private Button mButtonTech;

    /**
     * 宠物
     */
    private ImageView iv_petImage;

    private ImageView iv_peopleImage;

    private AnimationDrawable animationDrawable;

    private HttpConnectionUtils hcu;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_pet_view);

        // 初始化按钮
        mButtonBack = (Button) findViewById(R.id.bt_back);
        mButtonBack.setOnClickListener(this);

        // 属性
        mButtonAttribute = (Button) findViewById(R.id.bt_attribute);
        mButtonAttribute.setOnClickListener(this);

        // 喂食
        mButtonEat = (Button) findViewById(R.id.bt_eat);
        mButtonEat.setOnClickListener(this);

        // 挑逗
        mButtonPlay = (Button) findViewById(R.id.bt_play);
        mButtonPlay.setOnClickListener(this);

        // 嗦使
        mButtonTech = (Button) findViewById(R.id.bt_tech);
        mButtonTech.setOnClickListener(this);

        //
        iv_petImage = (ImageView) findViewById(R.id.pet_image);
        iv_petImage.setBackgroundResource(R.anim.animation_pet);
        animationDrawable = (AnimationDrawable) iv_petImage.getBackground();
        animationDrawable.setOneShot(true);

        //
        iv_peopleImage = (ImageView) findViewById(R.id.animation_man_image);
        iv_peopleImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack) || v.equals(iv_peopleImage))
        {
            finish();
        }
        else if (v.equals(mButtonAttribute))
        {
            // 属性
            // showModifyDialog();
        }
        else if (v.equals(mButtonEat))
        {
            // 吃
            if (animationDrawable.isRunning())
            {
                animationDrawable.stop();
            }
            animationDrawable.start();

            // feed、sing、play、learn、abet
            if (hcu == null)
            {
                hcu = new HttpConnectionUtils(handler);
            }
            hcu.addPostParmeter("uid", "10004");
            hcu.addPostParmeter("mid", "宠物id");
            hcu.addPostParmeter("option", "feed");
            hcu.post(Url.PetInteractive, HttpConnectEvent.HTTP_PET_INTERACTIVE);

        }
        else if (v.equals(mButtonPlay))
        {
            // 挑逗
            if (animationDrawable.isRunning())
            {
                animationDrawable.stop();
            }
            animationDrawable.start();

            // feed、sing、play、learn、abet
            if (hcu == null)
            {
                hcu = new HttpConnectionUtils(handler);
            }
            hcu.addPostParmeter("uid", "10004");
            hcu.addPostParmeter("mid", "宠物id");
            hcu.addPostParmeter("option", "learn");
            hcu.post(Url.PetInteractive, HttpConnectEvent.HTTP_PET_INTERACTIVE);
        }
        else if (v.equals(mButtonTech))
        {
            // 嗦使
            // Intent intent = new Intent();
            // intent.setClass(this, PetSongActivity.class);
            // startActivity(intent);

            //
            if (hcu == null)
            {
                hcu = new HttpConnectionUtils(handler);
            }
            hcu.addPostParmeter("uid", "10004");
            hcu.addPostParmeter("mid", "宠物id");
            hcu.addPostParmeter("option", "sing");
            hcu.post(Url.PetInteractive, HttpConnectEvent.HTTP_PET_INTERACTIVE);
        }
        // else if (v.equals(mButtonOk))
        // {
        // // 修改宠物名称提交服务器
        // if (hcu == null)
        // {
        // hcu = new HttpConnectionUtils(handler);
        // }
        // hcu.addPostParmeter("uid", "10004");
        // hcu.addPostParmeter("mid", "宠物id");
        // hcu.addPostParmeter("name", mEditTextPetName.getText().toString());
        // hcu.post(Url.ModifyPetName, HttpConnectEvent.HTTP_MODIFY_PET_NAME);
        //
        // }
        // else if (v.equals(mButtonCancel))
        // {
        // if (mDialogPetMessage != null)
        // {
        // mDialogPetMessage.dismiss();
        // }
        // }
    }

    private View mViewDialog = null;

    private Dialog mDialogPetMessage = null;

    private Button mButtonOk;

    private Button mButtonCancel;

    private EditText mEditTextPetName;

    /**
     * 显示修改信息Dialog
     */
    private void showModifyDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mViewDialog = layoutInflater.inflate(R.layout.dialog_modify_usermsg, null);
        mDialogPetMessage = new Dialog(this, R.style.NomarDialogStyle);
        mEditTextPetName = (EditText) mViewDialog.findViewById(R.id.et_name);
        LinearLayout linearLayout = (LinearLayout) mViewDialog.findViewById(R.id.linear_mark);
        linearLayout.setVisibility(View.GONE);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mDialogPetMessage.addContentView(mViewDialog, params);
        mDialogPetMessage.show();

        //
        mButtonOk = (Button) mDialogPetMessage.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
        mButtonCancel = (Button) mDialogPetMessage.findViewById(R.id.button_cancel);
        mButtonCancel.setOnClickListener(this);
    }

    private Handler handler = new HttpHandler(PetActivity.this)
    {
        @Override
        protected void succeed(JSONObject jObject, int event)
        {
            // connec success
        }

        protected void failed(JSONObject jObject, int event)
        {
            // failed

        }

        @Override
        protected void error(int event)
        {
            // error
        }

    };
}
