package com.five.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;
import com.five.model.SmsListModel;
import com.five.model.SmsModel;

public class SmsListViewActivity extends Activity
{
    /**
     * 信息列表
     */
    private ListView listView_smsList;
    
    private ArrayList<SmsListModel> smslists;
    
    private SmsListAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_list_view);
        
        //
        SmsListModel smslist = null;
        smslists = new ArrayList<SmsListModel>(10);
        
        // 初始化UI
        listView_smsList = (ListView) findViewById(R.id.all_sms_list);
        adapter = new SmsListAdapter(this);
        // 测试数据
        for (int j = 0; j < 10; j++)
        {
            smslist = new SmsListModel();
            smslist.setM_imgHead(BitmapFactory.decodeResource(getResources(), R.drawable.head));
            smslist.setM_strDate("时间:2012-4-4");
            smslist.setM_strName("发件人姓名" + j);
            smslist.setM_strNumber(String.valueOf(j % 2));
            adapter.addItem(smslist);
        }
        listView_smsList.setAdapter(adapter);
    }
    
    /**
     * 
     * @author wu
     * 
     * 
     */
    class SmsListAdapter extends BaseAdapter
    {
        private Context context;
        private ArrayList<SmsListModel> items;
        private LayoutInflater layoutInflater;
        
        public SmsListAdapter(Context context)
        {
            this.context = context;
            this.items = new ArrayList<SmsListModel>();
            this.layoutInflater = LayoutInflater.from(this.context);
            
        }
        
        public void addItem(SmsListModel smsList)
        {
            items.add(smsList);
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return this.items.size();
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return this.items.get(position);
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
            SmsListModel smsList = items.get(position);
            if (smsList != null)
            {
                convertView = this.layoutInflater.inflate(R.layout.all_sms_list_item, null);
                Holder.iv_icon = (ImageView) convertView.findViewById(R.id.img_head);
                Holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                Holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                Holder.bt_size = (Button) convertView.findViewById(R.id.sms_size);
                
                Holder.iv_icon.setImageBitmap(smsList.getM_imgHead());
                Holder.tv_name.setText(smsList.getM_strName());
                Holder.tv_date.setText(smsList.getM_strDate());
                Holder.bt_size.setText(smsList.getM_strNumber());
                if (Integer.parseInt(smsList.getM_strNumber())==0)
                {
                    Holder.bt_size.setVisibility(View.GONE);
                }
                else
                {
                    Holder.bt_size.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }
    }
    
    static class Holder
    {
        static ImageView iv_icon = null;
        static TextView tv_name = null;
        static TextView tv_date = null;
        static Button bt_size = null;
    }
}
