package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
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
 * 消息列表
 * 
 * @author a
 * 
 */
public class MsgActivity extends Activity implements OnClickListener, OnItemClickListener
{
    
    private static String TAG = "MsgActivity";
    
    private ListView mMsgListView;
    
    private List<Map<String, Object>> mMsgData;
    
    private Button mButtonBack;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_list_view);
        mMsgListView = (ListView) findViewById(R.id.msg_list);
        
        // test
        mMsgData = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("msg", "测试信息----------------" + j);
            mMsgData.add(item);
        }
        
        MsgListAdapter adapter = new MsgListAdapter(this);
        mMsgListView.setAdapter(adapter);
        mMsgListView.setOnItemClickListener(this);
        
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
    class MsgListAdapter extends BaseAdapter
    {
        Context context;
        
        public MsgListAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return MsgActivity.this.mMsgData.size();
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
            
            linearLayout = (LinearLayout) View.inflate(this.context, R.layout.msg_list_item, null);
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(mMsgData.get(position).get("msg").toString());
            Log.i("msg", mMsgData.get(position).get("msg").toString());
            
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
    
    private View mViewDialog;
    
    private Dialog mDMessage;
    
    private TextView mTvMessage;
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mViewDialog = layoutInflater.inflate(R.layout.dialog_simple_content, null);
        mDMessage = new Dialog(this, R.style.NomarDialogStyle);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mDMessage.addContentView(mViewDialog, params);
        
        mTvMessage = (TextView) mViewDialog.findViewById(R.id.tv_msg);
        mTvMessage.setText(position + " 移动互联网正在全球掀起新一轮的热潮,高速增长的中国市场毫无疑问成为瞩目焦点.");
        mDMessage.show();
    }
    
}
