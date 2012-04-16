package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;

public class ConnectionMessageActivity extends Activity implements OnClickListener
{
    private Button mButtonBack;
    
    private Button mButtonLeavaMessage;
    
    private ListView mActivityListView;
    
    private List<Map<String, Object>> mActivityListData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_connection_message);
        
        mActivityListData = new ArrayList<Map<String, Object>>();
        
        // 测试数据
        for (int j = 0; j < 10; j++)
        {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("act", "他所参加的活动-------------------------------" + j);
            mActivityListData.add(item);
        }
        
        mActivityListView = (ListView) findViewById(R.id.activity_list);
        ActivityAdapter adapter = new ActivityAdapter(this);
        mActivityListView.setAdapter(adapter);
        
        //
        mButtonBack = (Button) findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(this);
        
        //
        mButtonLeavaMessage = (Button) findViewById(R.id.button_leave_message);
        mButtonLeavaMessage.setOnClickListener(this);
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
            return ConnectionMessageActivity.this.mActivityListData.size();
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
            
            linearLayout = (LinearLayout) View.inflate(context, R.layout.jion_activity_list_item, null);
            
            tv = (TextView) linearLayout.findViewById(R.id.tv);
            tv.setText(ConnectionMessageActivity.this.mActivityListData.get(position).get("act").toString());
            
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
        else if (v.equals(mButtonLeavaMessage))
        {
            showLeaveMessageDialog();
        }
        else if (v.equals(mButtonOk))
        {
            
        }
        else if (v.equals(mButtonCancel))
        {
            mDLeavaMessage.dismiss();
        }
    }
    
    private Button mButtonOk;
    private Button mButtonCancel;
    private View mViewDialog = null;
    
    private Dialog mDLeavaMessage = null;
    
    /**
     * 流言对话框
     */
    private void showLeaveMessageDialog()
    {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mViewDialog = layoutInflater.inflate(R.layout.dialog_leave_msg, null);
        mDLeavaMessage = new Dialog(this, R.style.NomarDialogStyle);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mDLeavaMessage.addContentView(mViewDialog, params);
        mDLeavaMessage.show();
        
        //
        mButtonOk = (Button) mDLeavaMessage.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(this);
        mButtonCancel = (Button) mDLeavaMessage.findViewById(R.id.button_cancel);
        mButtonCancel.setOnClickListener(this);
    }
    
}
