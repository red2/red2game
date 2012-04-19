package com.five.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.five.FiveApplication;
import com.five.R;

@Deprecated
public class MenuView extends GridView implements GridView.OnItemClickListener
{

    private static final String TAG = "MenuView";

    /**
     * Context对象
     */
    private Context context;

    /**
     * Adapter实例
     */
    private BaseAdapter mAdapter;

    /**
     * Paint对象
     */
    private Paint mPaint;

    /**
     * 宽和高
     */
    private int width = 38, height = 38;

    /**
     * 菜单项的id
     */
    private int[] menuId;

    /**
     * onButtonClickLisener实例
     */
    private onButtonClickLisener listener = null;

    public MenuView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    public MenuView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public MenuView(Context context)
    {
        super(context);
        init(context);

    }

    /**
     * 初始化
     */
    private void init(Context context)
    {
        this.context = context;

        this.mPaint = new Paint();

        Paint.Style style = Paint.Style.STROKE;

        this.mPaint.setStyle(style);

        this.mPaint.setColor(0xE5808080);

        this.width = getResources().getDimensionPixelSize(R.dimen.menu_wx);

        this.height = this.width;

        this.setOnItemClickListener(this);
    }

    /**
     * 回调
     * 
     * @author a
     * 
     */
    public abstract interface onButtonClickLisener
    {
        public abstract void onMenuItemClick(int menuId);
    }

    /**
     * 获取listener对象
     * 
     * @return
     */
    public onButtonClickLisener getListener()
    {
        return listener;
    }

    /**
     * 设置listener对象
     * 
     * @param listener
     */
    public void setListener(onButtonClickLisener listener)
    {
        this.listener = listener;
    }

    /**
     * 设置菜单id
     * 
     * @param ids
     */
    public void setMenuId(int[] ids)
    {
        if (ids != null && ids.length > 0)
        {
            this.menuId = ids;
        }
        this.setAdapter(new GridAdapter(this.context));
    }

    class GridAdapter extends BaseAdapter
    {
        private LayoutInflater layoutInflater = null;

        GridAdapter(Context context)
        {
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount()
        {
            return MenuView.this.menuId.length;
        }

        @Override
        public Object getItem(int position)
        {
            return Integer.valueOf(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LinearLayout linearLayout = (LinearLayout) this.layoutInflater.inflate(R.layout.menu_item, null);

            TextView textView = (TextView) linearLayout.findViewById(R.id.item_text);

            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.item_image);

            int index = MenuView.this.menuId[position];

            textView.setText(FiveApplication.MENU_ITEMS[index].m_strName);

            Bitmap icon = Bitmap.createScaledBitmap(FiveApplication.MENU_ITEMS[index].m_bIcon, width, height, true);

            imageView.setImageBitmap(icon);

            return linearLayout;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        int menuId = MenuView.this.menuId[position];
        if (listener != null)
        {
            listener.onMenuItemClick(menuId);
        }
    }

}
