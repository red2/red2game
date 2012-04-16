package com.five.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import com.five.R;
import com.five.SettingActivity;

/**
 * 属性
 * 
 * @author a
 * 
 */
public class AttributeActivity extends Activity implements OnClickListener
{
    private static String TAG = "AttributeActivity";
    
    private Gallery gallery = null;
    
    private Button mButtonBack = null;
    
    private Button mButtonModify = null;
    
    private View mViewDialog = null;
    
    private Dialog mDUserMessage = null;
    
    private Button mButtonSettign = null;
    
    private Integer[] mThumbIds =
    {
            R.drawable.achievement2, R.drawable.achievement3, R.drawable.achievement101, R.drawable.achievement102, R.drawable.achievement301, R.drawable.achievement302, R.drawable.achievement401,
            R.drawable.achievement402
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_attribute);
        gallery = (Gallery) findViewById(R.id.gallery);
        ImageAdapter adapter = new ImageAdapter(this);
        gallery.setAdapter(adapter);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
        
        //
        mButtonModify = (Button) findViewById(R.id.modify);
        mButtonModify.setOnClickListener(this);
        
        mButtonSettign = (Button) findViewById(R.id.seting);
        mButtonSettign.setOnClickListener(this);
    }
    
    class ImageAdapter extends BaseAdapter
    {
        Context context;
        
        public ImageAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return AttributeActivity.this.mThumbIds.length;
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return AttributeActivity.this.mThumbIds[position];
        }
        
        @Override
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView i = new ImageView(context);
            
            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack))
        {
            this.finish();
        }
        else if (v.equals(mButtonModify))
        {
            showModifyDialog();
        }
        else if (v.equals(mButtonCancel))
        {
            mDUserMessage.dismiss();
        }
        else if (v.equals(mButtonOk))
        {
            mDUserMessage.dismiss();
        }
        else if (v.equals(mButtonSettign))
        {
            Intent intent = new Intent();
            intent.setClass(AttributeActivity.this, SettingActivity.class);
            startActivity(intent);
        }
        
    }
    
    private Button mButtonOk;
    private Button mButtonCancel;
    
    /**
     * 显示修改信息Dialog
     */
    private void showModifyDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mViewDialog = layoutInflater.inflate(R.layout.dialog_modify_usermsg, null);
        mDUserMessage = new Dialog(this, R.style.NomarDialogStyle);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mDUserMessage.addContentView(mViewDialog, params);
        mDUserMessage.show();
        
        //
        mButtonOk = (Button) mDUserMessage.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
        mButtonCancel = (Button) mDUserMessage.findViewById(R.id.button_cancel);
        mButtonCancel.setOnClickListener(this);
    }
}
