package com.five.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

import com.five.R;
import com.five.view.AttributeActivity.ImageAdapter;

/**
 * 进入商家界面
 * 
 * @author a
 * 
 */
public class TradeCompanyActivity extends Activity implements OnClickListener
{
    private Button mButtonBack;
    
    private Button mButtonCollect;
    
    private Integer[] mThumbIds =
    {
            R.drawable.achievement2, R.drawable.achievement3, R.drawable.achievement101, R.drawable.achievement102, R.drawable.achievement301, R.drawable.achievement302, R.drawable.achievement401,
            R.drawable.achievement402
    };
    
    private Gallery gallery = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_tradecompany_view);
        
        //
        gallery = (Gallery) findViewById(R.id.gallery);
        ImageAdapter adapter = new ImageAdapter(this);
        gallery.setAdapter(adapter);
        
        //
        mButtonBack = (Button) findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(this);
        
        //
        mButtonCollect = (Button) findViewById(R.id.button_collect);
        mButtonCollect.setOnClickListener(this);
    }
    
    class ImageAdapter extends BaseAdapter
    {
        Context context;
        
        public ImageAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return TradeCompanyActivity.this.mThumbIds.length;
        }
        
        @Override
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return TradeCompanyActivity.this.mThumbIds[position];
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
            ImageView i = new ImageView(context);
            
            i.setImageResource(mThumbIds[position]);
            i.setAdjustViewBounds(true);
            i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // i.setBackgroundResource(R.drawable.picture_frame);
            return i;
        }
        
    }
    
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v.equals(mButtonBack))
        {
            // 返回
            this.finish();
        }
        else if (v.equals(mButtonCollect))
        {
            // 收藏
        }
        
    }
    
}
