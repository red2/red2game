package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

/**
 * 活动
 * 
 * @author a
 * 
 */
public class ActivityActivity extends Activity implements OnClickListener, OnItemClickListener
{
    private static String TAG = "ActivityActivity";
    
    private ListView mActivityListView;
    
    private List<Map<String, Object>> mActivityListData;
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_activity);
        
        mActivityListData = new ArrayList<Map<String, Object>>();
        
        // 测试数据
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("act", "活动列表----" + j);
            mActivityListData.add(item);
        }
        
        mActivityListView = (ListView) findViewById(R.id.activity_list);
        ActivityAdapter adapter = new ActivityAdapter(this);
        mActivityListView.setAdapter(adapter);
        mActivityListView.setOnItemClickListener(this);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
    }
    
    class ActivityAdapter extends BaseAdapter
    {
        Context context;
        
        public ActivityAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return ActivityActivity.this.mActivityListData.size();
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return Integer.valueOf(position);
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
            // TODO Auto-generated method stub
            LinearLayout linearLayout = null;
            
            TextView tv = null;
            ImageView iv = null;
            
            linearLayout = (LinearLayout) View.inflate(context, R.layout.activity_list_item, null);
            
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(ActivityActivity.this.mActivityListData.get(position).get("act").toString());
            
            iv = (ImageView) linearLayout.findViewById(R.id.iv);
            
            return linearLayout;
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
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        // TODO Auto-generated method stub    
        Intent intent = new Intent();
        intent.setClass(this, ActivityContentActivity.class);
        startActivity(intent);
    }
}
