package com.five.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
 * 走江湖
 * 
 * @author a
 * 
 */
public class VagrantActivity extends Activity implements OnClickListener, OnItemClickListener
{
    private static String TAG = "VagrantActivity";
    
    private ListView mVagrantListView;
    
    private List<Map<String, Object>> mVagrantData;
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_vagrant);
        
        mVagrantListView = (ListView) findViewById(R.id.vagrant_list);
        
        // test
        mVagrantData = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("msg", "江湖信息----------------" + j);
            mVagrantData.add(item);
        }
        
        VagrantAdapter adapter = new VagrantAdapter(this);
        mVagrantListView.setAdapter(adapter);
        mVagrantListView.setOnItemClickListener(this);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
    }
    
    /**
     * 消息列表的适配器
     * 
     * @author a
     * 
     */
    class VagrantAdapter extends BaseAdapter
    {
        Context context;
        
        public VagrantAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return VagrantActivity.this.mVagrantData.size();
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
            LinearLayout linearLayout = null;
            TextView tv = null;
            ImageView iv = null;
            
            linearLayout = (LinearLayout) View.inflate(this.context, R.layout.vagrant_list_item, null);
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(VagrantActivity.this.mVagrantData.get(position).get("msg").toString());
            
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
        intent.setClass(this, TradeCompanyActivity.class);
        startActivity(intent);
    }
    
}
