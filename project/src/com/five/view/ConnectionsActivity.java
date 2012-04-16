package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
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
 * 人脉图
 * 
 * @author a
 * 
 */
public class ConnectionsActivity extends Activity implements OnClickListener, OnItemClickListener
{
    private static String TAG = "ConnectionsActivity";
    
    private ListView mConnectionsListView;
    
    private List<Map<String, Object>> mConnectionsListData;
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_list);
        
        mConnectionsListData = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", "人脉测试----" + j);
            mConnectionsListData.add(item);
        }
        
        mConnectionsListView = (ListView) findViewById(R.id.connections_list_view);
        ConnectionsAdapter adapter = new ConnectionsAdapter(this);
        mConnectionsListView.setAdapter(adapter);
        
        // 长按菜单
        mConnectionsListView.setOnCreateContextMenuListener(this);
        mConnectionsListView.setOnItemClickListener(this);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
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
            return ConnectionsActivity.this.mConnectionsListData.size();
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
            tv.setText(ConnectionsActivity.this.mConnectionsListData.get(position).get("name").toString());
            
            iv = (ImageView) linearLayout.findViewById(R.id.iv);
            
            return linearLayout;
        }
        
    }
    
    protected static final int CONTEXTMENU_ENTERN = 0;
    
    protected static final int CONTEXTMENU_COMPETITION = 1;
    
    protected static final int CONTEXTMENU_BRAG = 2;
    
    protected static final int CONTEXTMENU_NEXTCONNEC = 3;
    
    /**
     * 
     */
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        // TODO Auto-generated method stub
        
        AdapterView.AdapterContextMenuInfo info;
        try
        {
            info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        }
        catch (ClassCastException e)
        {
            Log.e(TAG, "bad menuInfo", e);
            return;
        }
        menu.setHeaderTitle("菜单");
        
        menu.add(0, CONTEXTMENU_ENTERN, 0, "进入主页");
        menu.add(0, CONTEXTMENU_BRAG, 0, "邀请吹牛");
        menu.add(0, CONTEXTMENU_COMPETITION, 0, "邀请竞技");
        menu.add(0, CONTEXTMENU_NEXTCONNEC, 0, "查看人脉");
    }
    
    /**
     * 菜单响应
     */
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case CONTEXTMENU_ENTERN:
            {
                //
                Intent intent = new Intent();
                intent.setClass(this, ConnectionMessageActivity.class);
                startActivity(intent);
            }
                break;
            case CONTEXTMENU_BRAG:
            {
                
            }
                break;
            case CONTEXTMENU_COMPETITION:
            {
                
            }
                break;
            case CONTEXTMENU_NEXTCONNEC:
            {
                
            }
                break;
        }
        return false;
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
        intent.setClass(this, ConnectionMessageActivity.class);
        startActivity(intent);
    }
    
}
