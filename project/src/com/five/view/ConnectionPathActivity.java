package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

public class ConnectionPathActivity extends Activity
{
    private ListView mPathList;
    private ArrayList<Map<String, Object>> mConnList = new ArrayList<Map<String, Object>>();
    
    private Button mBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_path);
        
        for (int i = 0; i < 5; i++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("path", "name1-name2-name3-name4");
            mConnList.add(item);
        }
        
        mPathList = (ListView) findViewById(R.id.conn_path_lists);
        mPathList.setAdapter(new ConnListAdapter(this));
        
        
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
    
    class ConnListAdapter extends BaseAdapter
    {
        
        private Context mContext;
        
        public ConnListAdapter(Context context)
        {
            mContext = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return mConnList.size();
        }
        
        @Override
        public Object getItem(int arg0)
        {
            // TODO Auto-generated method stub
            return Integer.valueOf(arg0);
        }
        
        @Override
        public long getItemId(int arg0)
        {
            // TODO Auto-generated method stub
            return arg0;
        }
        
        @Override
        public View getView(int position, View view, ViewGroup group)
        {
            // TODO Auto-generated method stub
            LinearLayout linearLayout = (LinearLayout) View.inflate(this.mContext, R.layout.connection_path_item, null);
            TextView tv = (TextView) linearLayout.findViewById(R.id.con_path_item_first);
            tv.setText((CharSequence) mConnList.get(position).get("path"));
            return linearLayout;
        }
        
    }
}
