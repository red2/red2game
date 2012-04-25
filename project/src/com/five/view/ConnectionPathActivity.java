package com.five.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;
import com.five.model.UserInfo;

/**
 * 人脉
 * 
 * 
 */
public class ConnectionPathActivity extends Activity
{
    private ListView mPathList;
    
    private Button mBtnBack;
    
    private ConnListAdapter connListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_path);
        
        //
        UserInfo userInfo = null;
        
        mPathList = (ListView) findViewById(R.id.conn_path_lists);
        connListAdapter = new ConnListAdapter(this);
        mPathList.setAdapter(connListAdapter);
        
        // 测试数据
        for (int j = 0; j < 10; j++)
        {
            userInfo = new UserInfo();
            userInfo.setM_imgHead(BitmapFactory.decodeResource(getResources(), R.drawable.head));
            userInfo.setM_strName("姓名" + j);
            userInfo.setM_iLevel(10);
            connListAdapter.addItem(userInfo);
        }
        
        mBtnBack = (Button) findViewById(R.id.back);
        if (mBtnBack != null)
        {
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
    
    class ConnListAdapter extends BaseAdapter
    {
        
        private Context mContext;
        
        private ArrayList<UserInfo> items;
        
        private LayoutInflater layoutInflater;
        
        public ConnListAdapter(Context context)
        {
            mContext = context;
            items = new ArrayList<UserInfo>();
            this.layoutInflater = LayoutInflater.from(mContext);
        }
        
        public void addItem(UserInfo info)
        {
            items.add(info);
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return items.size();
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return items.get(position);
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
            UserInfo info = items.get(position);
            if (info != null)
            {
                convertView = this.layoutInflater.inflate(R.layout.connection_path_item, null);
                Holder.iv_icon = (ImageView) convertView.findViewById(R.id.img_head);
                Holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                Holder.tv_level = (TextView) convertView.findViewById(R.id.tv_level);
                
                Holder.iv_icon.setImageBitmap(info.getM_imgHead());
                Holder.tv_name.setText(info.getM_strName());
                Holder.tv_level.setText("等级：" + info.getM_iLevel());
            }
            return convertView;
        }
        
    }
    
    static class Holder
    {
        static ImageView iv_icon = null;
        static TextView tv_name = null;
        static TextView tv_level = null;
    }
}
