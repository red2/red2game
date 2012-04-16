package com.five.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

/**
 * 背包
 * 
 * @author a
 * 
 */
public class BagActivity extends Activity implements OnClickListener
{
    private static String TAG = "BagActivity";
    
    /**
     * 道具表
     */
    private GridView gridView = null;
    
    /**
     * 道具适配器
     */
    private GridAdapter adapter = null;
    
    /**
     * 道具id
     */
    private int toolId[];
    
    private String weapData[];
    
    private Button mButtonBack;
    
    /**
     * 武器列表
     */
    private ListView weapListView = null;
    
    private WeapAdapter weapAdapter = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_bag_view);
        
        // 吹牛袋子
        this.gridView = (GridView) findViewById(R.id.gridview);
        
        toolId = new int[]
        {
                R.drawable.icon_silver_asset, R.drawable.icon_silver_cash, R.drawable.icon_silver_checkin, R.drawable.icon_silver_level, R.drawable.icon_silver_point, R.drawable.icon_silver_poprate,
                R.drawable.icon_silver_skull, R.drawable.icon_silver_stamina
        };
        adapter = new GridAdapter(this);
        this.gridView.setAdapter(adapter);
        
        // 武器装备
        this.weapListView = (ListView) findViewById(R.id.weapon_list_view);
        weapData = new String[]
        {
                "大刀", "手枪", "火箭筒", "匕首", "弓箭", "冲锋枪", "哈哈哈"
        };
        weapAdapter = new WeapAdapter(this);
        this.weapListView.setAdapter(weapAdapter);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
    }
    
    class GridAdapter extends BaseAdapter
    {
        Context context;
        
        public GridAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return BagActivity.this.toolId.length;
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return BagActivity.this.toolId[position];
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
            ImageView iv = null;
            if (convertView == null)
            {
                iv = new ImageView(this.context);
                iv.setLayoutParams(new GridView.LayoutParams(36, 37));
                iv.setBackgroundResource(BagActivity.this.toolId[position]);
            }
            else
            {
                iv = (ImageView) convertView;
            }
            return iv;
        }
        
    }
    
    class WeapAdapter extends BaseAdapter
    {
        Context context;
        
        public WeapAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return BagActivity.this.weapData.length;
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return BagActivity.this.weapData[position];
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
            
            linearLayout = (LinearLayout) View.inflate(context, R.layout.weap_list_item, null);
            
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(BagActivity.this.weapData[position]);
            
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
            finish();
        }
    }
}
