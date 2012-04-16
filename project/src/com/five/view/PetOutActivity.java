package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

public class PetOutActivity extends Activity implements OnClickListener
{
    private String TAG = "PetOutActivity";
    
    private Button mButtonBack;
    
    private ListView mListView;
    
    private List<Map<String, Object>> mData;
    
    protected static final int CONTEXTMENU_DO_EXERCISE = 0;
    
    protected static final int CONTEXTMENU_OFFLINE_TASK = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_pet_out_view);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
        
        //
        mListView = (ListView) findViewById(R.id.pet_out_list);
        mListView.setOnCreateContextMenuListener(this);
        
        // test
        mData = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("msg", "商家----------------" + j);
            mData.add(item);
        }
        
        OutListAdapter adapter = new OutListAdapter(this);
        mListView.setAdapter(adapter);
    }
    
    /**
     * 商家列表的适配器
     * 
     * @author a
     * 
     */
    class OutListAdapter extends BaseAdapter
    {
        Context context;
        
        public OutListAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return PetOutActivity.this.mData.size();
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
            tv.setText(PetOutActivity.this.mData.get(position).get("msg").toString());
            
            iv = (ImageView) linearLayout.findViewById(R.id.iv);
            
            return linearLayout;
        }
    }
    
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
        
        menu.add(0, CONTEXTMENU_DO_EXERCISE, 0, "带领召唤兽修炼");
        menu.add(0, CONTEXTMENU_OFFLINE_TASK, 0, "领取线下任务");
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
