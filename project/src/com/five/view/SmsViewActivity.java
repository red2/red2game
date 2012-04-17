package com.five.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;
import com.five.model.SmsModel;

public class SmsViewActivity extends Activity
{

    private ListView smsListView;

    private SmsListAdapter smsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_list_me_and_he);

        //
        smsListView = (ListView) findViewById(R.id.sms_list);
        smsListAdapter = new SmsListAdapter(this);
        SmsModel sms;

        for (int i = 0; i < 10; i++)
        {
            sms = new SmsModel();
            if (i % 2 == 0)
            {
                sms.setM_intType(SmsModel.FROM);
            }
            else
            {
                sms.setM_intType(SmsModel.TO);
            }
            if (i % 3 == 0)
            {
                sms.setM_strText("垃圾水电费绿卡就是连看到手机反垃圾水电费绿卡就是连看到放假啊是~~~~" + i);
            }
            else
            {
                sms.setM_strText("中国电信~~~~" + i);
            }
            smsListAdapter.addItem(sms);
        }
        smsListView.setAdapter(smsListAdapter);

    }

    class SmsListAdapter extends BaseAdapter
    {
        private Context context;

        private LayoutInflater layoutInflater;

        private ArrayList<SmsModel> items;

        public SmsListAdapter(Context context)
        {
            this.context = context;
            this.items = new ArrayList<SmsModel>();
            this.layoutInflater = LayoutInflater.from(this.context);
        }

        public void addItem(SmsModel sms)
        {
            items.add(sms);
        }

        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return items.size();
        }

        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return items.get(position);
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
            SmsModel sms = items.get(position);
            if (sms != null)
            {
                if (sms.getM_intType() == SmsModel.FROM)
                {
                    convertView = this.layoutInflater.inflate(R.layout.list_say_he_item, null);

                }
                else
                {
                    convertView = this.layoutInflater.inflate(R.layout.list_say_me_item, null);

                }
                Holder.tv_sms = (TextView) convertView.findViewById(R.id.tv_sms_content);
                Holder.tv_sms.setText(sms.getM_strText());
            }

            return convertView;
        }

    }

    static class Holder
    {
        static TextView tv_sms;
    }
}