package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

public class ActivityContentActivity extends Activity implements OnClickListener
{
    
    private ListView mConnectionsListView;
    
    private List<Map<String, Object>> mConnectionsListData;
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_activity_content_view);
        
        mConnectionsListData = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", "参与此活动的人----" + j);
            mConnectionsListData.add(item);
        }
        
        mConnectionsListView = (ListView) findViewById(R.id.connections_list_view);
        ConnectionsAdapter adapter = new ConnectionsAdapter(this);
        mConnectionsListView.setAdapter(adapter);
        
        //
        mButtonBack = (Button) findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(this);
        
    }
    
    class ConnectionsAdapter extends BaseAdapter
    {
        Context context;
        
        public ConnectionsAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return ActivityContentActivity.this.mConnectionsListData.size();
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
            
            linearLayout = (LinearLayout) View.inflate(context, R.layout.connec_list_item, null);
            
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(ActivityContentActivity.this.mConnectionsListData.get(position).get("name").toString());
            
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
}
