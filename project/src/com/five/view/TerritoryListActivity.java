package com.five.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.five.R;
import com.five.UserContext;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.model.ShopInfo;
import com.five.util.DataParser;

public class TerritoryListActivity extends Activity implements View.OnClickListener, OnItemClickListener
{
    private ListView mShopListView;
    private ListView mTerritoryListView;
    Button btnShopList;
    Button btnTerritory;
    Button btnBack;
    
    ShopAdapter mShopListAdapter;
    ShopAdapter mTerritoryListAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.territory_list_view);
        
        mShopListView = (ListView) findViewById(R.id.listview_shops);
        mTerritoryListView = (ListView) findViewById(R.id.listview_territory);
        
        mShopListAdapter = new ShopAdapter(this);
        mTerritoryListAdapter = new ShopAdapter(this);
        
        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        
        btnTerritory = (Button) findViewById(R.id.btn_territory);
        btnTerritory.setOnClickListener(this);
        btnShopList = (Button) findViewById(R.id.btn_shops);
        btnShopList.setOnClickListener(this);
        mShopListView.setOnItemClickListener(this);
        
        getNearby(23.0000, 453.02123, 1000, ShopInfo.TYPE.DEFAULT, "bei jing", false);
    }
    
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
    {   
        // TODO
    }
    
    private void getNearby(double longitude, double latitude, int distance, String type, String city, boolean wantPic)
    {
        /*
         * longitude double 经度（小数点后六位即可） latitude double 纬度（小数点后六位即可） distance
         * int 初始默认的距离（暂定500米）其他可选距离有1000，2000，5000 type String
         * 商家类型（chi，mai，wan，le，all）此接口默认是all，即获取所有类型的商家。 city String 城市（bei
         * jing/ shang hai)暂用的拼音 pic String 是否获取商家图片（yes/no）此接口默认是no
         */
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("longitude", String.valueOf(longitude));
        hcu.addGetParmeter("latitude", String.valueOf(latitude));
        hcu.addGetParmeter("distance", String.valueOf(distance));
        hcu.addGetParmeter("type", type);
        hcu.addGetParmeter("city", city);
        hcu.addGetParmeter("pic", wantPic ? "yes" : "no");
        hcu.get(Url.GetShopsNearby, HttpConnectEvent.HTTP_GET_SHOPS_NEARBY);
    }
    
    private void searchShopsWithKeyWord(String keyword, String district, String type, String city, boolean wantPic, String sort)
    {
        /**
         * name String 关键字（可以是商家名，地理位置，关键字） district String 某区（chao yang/hai
         * dian）如果不区分的话填all。此处界面可能暂时没有涉及，因为初期商家少，没必要在细分了，则初期统一可填all type String
         * 商家类型（chi，mai，wan，le，all）此接口默认是all，即获取所有类型的商家。此处的类型界面也可能暂时没有，填all city
         * String 城市（bei jing/ shang hai)暂用的拼音 pic String
         * 是否获取商家图片（yes/no）此接口默认是no sort String
         * 排序类型（不排序默认default）目前也有根据信誉度排序credit。其他排序方式如有需求可另添加。
         */
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("name", keyword);
        hcu.addGetParmeter("district", district);
        hcu.addGetParmeter("type", type);
        hcu.addGetParmeter("city", city);
        hcu.addGetParmeter("pic", wantPic ? "yes" : "no");
        hcu.addGetParmeter("sort", sort);
        hcu.get(Url.SearchShopsWithKeyword, HttpConnectEvent.HTTP_SEARCH_SHOPS_KEYWORD);
        
    }
    
    private void searchShopsWithKeywordSortedByDistance(String keyword, String district, String type, String city, boolean wantPic, double longitude, double latitude)
    {
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("name", keyword);
        hcu.addGetParmeter("district", district);
        hcu.addGetParmeter("type", type);
        hcu.addGetParmeter("city", city);
        hcu.addGetParmeter("pic", wantPic ? "yes" : "no");
        hcu.addGetParmeter("longitude", String.valueOf(longitude));
        hcu.addGetParmeter("latitude", String.valueOf(latitude));
        hcu.get(Url.SearchShopsWithKeywordSortByDistance, HttpConnectEvent.HTTP_SEARCH_SHOPS_KEYWORD_AND_DISTANCE_SORTED);
    }
    
    public void searchShopsByDistrict(String type, String city, boolean wantPic, String sort, String area)
    {
        /**
         * type String
         * 商家类型（chi，mai，wan，le，all）此接口默认是all，即获取所有类型的商家。此处的类型界面也可能暂时没有，填all city
         * String 城市（bei jing/ shang hai)暂用的拼音 pic String
         * 是否获取商家图片（yes/no）此接口默认是no sort String
         * 排序类型（不排序默认default）目前也有根据信誉度排序credit。其他排序方式如有需求可另添加。 area String
         * 某商圈（guo mao）
         */
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("type", type);
        hcu.addGetParmeter("city", city);
        hcu.addGetParmeter("pic", wantPic ? "yes" : "no");
        hcu.addGetParmeter("sort", sort);
        hcu.addGetParmeter("area", area);
        hcu.get(Url.SearchShopsByDistrict, HttpConnectEvent.HTTP_SEARCH_SHOPS_BY_DISTRICT);
        
    }
    
    public void searchShopsByDistrictSortedByDistance(String type, String city, boolean wantPic, String area, double longitude, double latitude)
    {
        /**
         * type String
         * 商家类型（chi，mai，wan，le，all）此接口默认是all，即获取所有类型的商家。此处的类型界面也可能暂时没有，填all city
         * String 城市（bei jing/ shang hai)暂用的拼音 pic String
         * 是否获取商家图片（yes/no）此接口默认是no longitude double 经度（小数点后六位即可） latitude
         * double 纬度（小数点后六位即可） area String 某商圈（guo mao）
         */
        HttpConnectionUtils hcu = new HttpConnectionUtils(handler);
        hcu.addGetParmeter("type", type);
        hcu.addGetParmeter("city", city);
        hcu.addGetParmeter("pic", wantPic ? "yes" : "no");
        hcu.addGetParmeter("area", area);
        hcu.addGetParmeter("longitude", String.valueOf(longitude));
        hcu.addGetParmeter("latitude", String.valueOf(latitude));
        hcu.get(Url.SearchShopsByDistrictSortedByDistance, HttpConnectEvent.HTTP_SEARCH_SHOPS_BY_DISTRICT_DISTANCE_SORTED);
    }
    
    private Handler handler = new HttpHandler(TerritoryListActivity.this, true)
    {
        
        protected void succeed(org.json.JSONObject jObject, int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_SHOPS_NEARBY:
                case HttpConnectEvent.HTTP_SEARCH_SHOPS_KEYWORD:
                case HttpConnectEvent.HTTP_SEARCH_SHOPS_KEYWORD_AND_DISTANCE_SORTED:
                case HttpConnectEvent.HTTP_SEARCH_SHOPS_BY_DISTRICT:
                case HttpConnectEvent.HTTP_SEARCH_SHOPS_BY_DISTRICT_DISTANCE_SORTED:
                    ArrayList<ShopInfo> shops = DataParser.getInstance().parserShopInfo(jObject);
                    mShopListAdapter.setData(shops);
                    break;
            }
        };
        
        protected void failed(org.json.JSONObject jObject, int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_SHOPS_NEARBY:
                    
                    break;
                case HttpConnectEvent.HTTP_SEARCH_SHOPS_KEYWORD:
                    break;
            }
        };
        
        protected void error(int event)
        {
        };
    };
    
    class ShopAdapter extends BaseAdapter
    {
        private ArrayList<ShopInfo> shops;
        private Context context;
        private LayoutInflater inflater;
        
        public ShopAdapter(Context ctx)
        {
            context = ctx;
            inflater = LayoutInflater.from(ctx);
        }
        
        public void setData(ArrayList<ShopInfo> shops)
        {
            this.shops = shops;
        }
        
        @Override
        public int getCount()
        {
            if (shops != null)
            {
                return shops.size();
            }
            return 0;
        }
        
        @Override
        public Object getItem(int position)
        {
            if (shops != null)
            {
                return shops.get(position);
            }
            return null;
        }
        
        @Override
        public long getItemId(int position)
        {
            return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = inflater.inflate(R.layout.shop_list_view_item, null);
            ShopInfo shop = shops.get(position);
            TextView name = (TextView) convertView.findViewById(R.id.shop_name);
            name.setText(shop.getName());
            TextView desc = (TextView) convertView.findViewById(R.id.shop_desc);
            desc.setText(shop.getSdesc());
            
            TextView distance = (TextView) convertView.findViewById(R.id.shop_distance);
            if (shop.getDistance() != -1)
            {
                distance.setText(String.valueOf(shop.getDistance()));
            }
            else
            {
                distance.setVisibility(View.GONE);
            }
            return convertView;
        }
        
    }
    
    @Override
    public void onClick(View v)
    {
        if (v.equals(btnShopList))
        {
            mTerritoryListView.setVisibility(View.GONE);
            mShopListView.setVisibility(View.VISIBLE);
        }
        else if (v.equals(btnTerritory))
        {
            mShopListView.setVisibility(View.GONE);
            mTerritoryListView.setVisibility(View.VISIBLE);
        }
        else if (v.equals(btnBack))
        {
            finish();
        }
    }

  
    
}
