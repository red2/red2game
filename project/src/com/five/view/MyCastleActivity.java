package com.five.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.five.R;
import com.five.http.HttpConnectEvent;
import com.five.http.HttpConnectionUtils;
import com.five.http.HttpHandler;
import com.five.http.Url;
import com.five.model.CastleBasic;
import com.five.model.CastleInfo;
import com.five.model.CastleMember;
import com.five.util.DataParser;

/**
 * 我的城堡
 * 
 * @author a
 * 
 */
@Deprecated
public class MyCastleActivity extends Activity implements OnClickListener
{
    private static String TAG = "MyCastleActivity";
    
    private static final int INDEX_VIEW_MY_CASTLE = 0;
    private static final int INDEX_VIEW_PARTICIPANTS = 1;
    private static final int INDEX_VIEW_OTHER_CASTLE = 2;
    private static final int INDEX_VIEW_NEW_CASLTE = 3;
    
    private Button mButtonBack;
    private Button mButtonMyCastle;
    private Button mButtonParticipants;
    private Button mButtonOtherCastle;
    private Button mButtonNewCastle;
    
    private ArrayList<View> mViews = new ArrayList<View>();
    private int mCurrentViewIndex = 0;
    
    private CastleInfo mCastleInfo = null;
    
    private ArrayList<CastleBasic> mCastleList = null;
    
    // cache this
    private String mUsrId = "";
    
    private Handler mHandler = new HttpHandler(this)
    {
        protected void succeed(org.json.JSONObject jObject, int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_CASTLE_INFO:
                    mCastleInfo = DataParser.getInstance().parserCastleInfo(jObject);
                    
                    // TODO: Not sure about this. maybe another flag to indicate
                    // whether the user joined a castle
                    if (mCastleInfo.isJoin())
                    {
                        buildMyCastleView();
                        buildParticipantsView();
                        buildOtherCastleView();
                        showView(INDEX_VIEW_MY_CASTLE);
                    }
                    else
                    {
                        // Have not joined, just show the castle list? or
                        // something else?
                        // showNoJoinCastleView();
                        HttpConnectionUtils hcu = new HttpConnectionUtils(mHandler);
                        hcu.addGetParmeter("uid", mUsrId);
                        // TODO: How to carry these two parameters
                        /*
                         * hcu.addGetParmeter("P", castleID);
                         * hcu.addGetParmeter("style", "unknown");
                         */
                        hcu.get(Url.GetCastleList, HttpConnectEvent.HTTP_GET_CASTLE_LIST);
                    }
                    break;
                case HttpConnectEvent.HTTP_GET_CASTLE_LIST:
                    mCastleList = DataParser.getInstance().parserCastleList(jObject);
                    buildOtherCastleView();
                    showNoJoinCastleView();
                    break;
            }
        };
        
        protected void failed(org.json.JSONObject jObject, int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_CASTLE_INFO:
                    finish();
                    break;
                case HttpConnectEvent.HTTP_GET_CASTLE_LIST:
                    finish();
                    break;
            }
        };
        
        protected void error(int event)
        {
            switch (event)
            {
                case HttpConnectEvent.HTTP_GET_CASTLE_INFO:
                    finish();
                    break;
                case HttpConnectEvent.HTTP_GET_CASTLE_LIST:
                    finish();
                    break;
            }
        };
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_my_castle);
        
        //
        mButtonBack = (Button) findViewById(R.id.back);
        mButtonBack.setOnClickListener(this);
        
        mButtonParticipants = (Button) findViewById(R.id.btn_participants);
        mButtonParticipants.setOnClickListener(this);
        
        mButtonOtherCastle = (Button) findViewById(R.id.btn_other_castle);
        mButtonOtherCastle.setOnClickListener(this);
        
        mButtonNewCastle = (Button) findViewById(R.id.btn_new_castle);
        mButtonNewCastle.setOnClickListener(this);
        
        mButtonMyCastle = (Button) findViewById(R.id.btn_my_castle);
        mButtonMyCastle.setOnClickListener(this);
        
        LinearLayout view;
        view = (LinearLayout) findViewById(R.id.view_my_castle);
        mViews.add(view);
        
        view = (LinearLayout) findViewById(R.id.view_participants);
        mViews.add(view);
        
        view = (LinearLayout) findViewById(R.id.view_other_castle);
        mViews.add(view);
        
        view = (LinearLayout) findViewById(R.id.view_new_castle);
        mViews.add(view);
        
        mCurrentViewIndex = 0;
        
        Intent intent = getIntent();
        // String castleID = intent.getStringExtra("castle_id");
        String userID = intent.getStringExtra("user");
        if (userID.length() > 0)
        {
            mUsrId = userID;
            // Have joined a castle; so we should query & show the castle info;
            HttpConnectionUtils hcu = new HttpConnectionUtils(mHandler);
            hcu.addGetParmeter("uid", userID);
            // hcu.addGetParmeter("cid", castleID);
            hcu.get(Url.GetCastleInfo, HttpConnectEvent.HTTP_GET_CASTLE_INFO);
        }
        else
        {
            // Here we should show an error dialog;
            
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
        else if (v.equals(mButtonMyCastle))
        {
            showView(INDEX_VIEW_MY_CASTLE);
        }
        else if (v.equals(mButtonParticipants))
        {
            showView(INDEX_VIEW_PARTICIPANTS);
        }
        else if (v.equals(mButtonOtherCastle))
        {
            showView(INDEX_VIEW_OTHER_CASTLE);
        }
        else if (v.equals(mButtonNewCastle))
        {
            showView(INDEX_VIEW_NEW_CASLTE);
        }
    }
    
    private void showView(int index)
    {
        mViews.get(mCurrentViewIndex).setVisibility(View.GONE);
        mViews.get(index).setVisibility(View.VISIBLE);
        mCurrentViewIndex = index;
    }
    
    // participants view
    private static final int MENU_ID_CONNECTIONS_PATH = 0;
    private static final int MENU_ID_ENTER = 1;
    private static final int MENU_ID_INVITE_GAME = 2;
    
    private void buildParticipantsView()
    {
        mParticipantsListView = (ListView) findViewById(R.id.participants_listview);
        // test
        /*
         * mParticipantsData = new ArrayList<Map<String, Object>>(); for (int j
         * = 0; j < 10; j++) { HashMap<String, Object> item = new
         * HashMap<String, Object>(); item.put("msg", "城堡成员----------------" +
         * j); mParticipantsData.add(item); }
         */
        
        ParticipantsListAdapter adapter = new ParticipantsListAdapter(this);
        mParticipantsListView.setAdapter(adapter);
        mParticipantsListView.setOnCreateContextMenuListener(this);
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        
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
        if (v.getId() == R.id.participants_listview)
        {
            menu.add(0, MENU_ID_CONNECTIONS_PATH, 0, "人脉路径");
            menu.add(0, MENU_ID_ENTER, 0, "访问主页");
            menu.add(0, MENU_ID_INVITE_GAME, 0, "邀请吹牛");
        }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case MENU_ID_CONNECTIONS_PATH:
            {
                Intent intent = new Intent();
                intent.setClass(this, ConnectionPathActivity.class);
                startActivity(intent);
            }
                break;
            case MENU_ID_ENTER:
            {
                Intent intent = new Intent();
                intent.setClass(this, FriendActivity.class);
                startActivity(intent);
            }
                break;
            case MENU_ID_INVITE_GAME:
            {
                
            }
                break;
            default:
                break;
        }
        
        return false;
    }
    
    private ListView mParticipantsListView;
    
    @Deprecated
    private List<Map<String, Object>> mParticipantsData;
    
    class ParticipantsListAdapter extends BaseAdapter
    {
        Context context;
        
        public ParticipantsListAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            int result = 0;
            if (mCastleInfo != null)
            {
                ArrayList<CastleMember> senators = mCastleInfo.getSenator();
                if (senators != null)
                {
                    result = senators.size();
                }
                
                if (mCastleInfo.getLord() != null)
                {
                    result += 1;
                }
                
            }
            return result;
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
            LinearLayout linearLayout = null;
            TextView tv = null;
            /*
             * TextView tvCrc = null; ImageView iv = null;
             */
            
            linearLayout = (LinearLayout) View.inflate(this.context, R.layout.participants_list_item, null);
            tv = (TextView) linearLayout.findViewById(R.id.tv_name);
            if (position == 0)
            {
                // Lord
                tv.setText(mCastleInfo.getLord().getName());
                linearLayout.setTag(mCastleInfo.getLord().getId());
            }
            else
            {
                // senators
                tv.setText(mCastleInfo.getSenator().get(position - 1).getName());
                linearLayout.setTag(mCastleInfo.getSenator().get(position - 1).getId());
            }
            
            /*
             * tv.setText(mParticipantsData.get(position).get("msg").toString());
             * 
             * tvCrc = (TextView) linearLayout.findViewById(R.id.tv_crc);
             * tvCrc.setText
             * (mParticipantsData.get(position).get("msg").toString());
             */
            
            // iv = (ImageView) linearLayout.findViewById(R.id.iv);
            
            return linearLayout;
        }
    }
    
    // Other castle
    private void buildOtherCastleView()
    {
        if (mButtonOtherCastle != null)
        {
            mButtonOtherCastle.setVisibility(View.VISIBLE);
        }
        
        mOthercastles = (ListView) findViewById(R.id.other_view_listview_castle_results);
        // test
        /*
         * mOtherCastlesData = new ArrayList<Map<String, Object>>(); for (int j
         * = 0; j < 10; j++) { HashMap<String, Object> item = new
         * HashMap<String, Object>(); item.put("msg", "城堡----------------" + j);
         * mOtherCastlesData.add(item); }
         */
        
        OtherCastlesListAdapter adapter = new OtherCastlesListAdapter(this);
        mOthercastles.setAdapter(adapter);
    }
    
    private ListView mOthercastles;
    
    @Deprecated
    private List<Map<String, Object>> mOtherCastlesData;
    
    class OtherCastlesListAdapter extends BaseAdapter
    {
        Context context;
        
        public OtherCastlesListAdapter(Context context)
        {
            this.context = context;
        }
        
        @Override
        public int getCount()
        {
            int result = 0;
            if (mCastleList != null)
            {
                result = mCastleList.size();
            }
            return result;
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
            LinearLayout linearLayout = null;
            TextView tv = null;
            TextView tvLocation = null;
            ImageView iv = null;
            
            linearLayout = (LinearLayout) View.inflate(this.context, R.layout.participants_list_item, null);
            tv = (TextView) linearLayout.findViewById(R.id.tv_name);
            CastleBasic cb = mCastleList.get(position);
            tv.setText(cb.getCname());
            
            tvLocation = (TextView) linearLayout.findViewById(R.id.tv_crc);
            tvLocation.setText("地点: " + cb.getLocation() + "  等级：" + cb.getLevel());
            
            iv = (ImageView) linearLayout.findViewById(R.id.iv_head);
            iv.setImageDrawable(getResources().getDrawable(R.drawable.icon_silver_asset));
            
            linearLayout.setTag(cb);
            
            return linearLayout;
        }
    }
    
    private void buildMyCastleView()
    {
        if (mButtonMyCastle != null)
        {
            mButtonMyCastle.setVisibility(View.VISIBLE);
        }
        
        LinearLayout myCastleView = (LinearLayout) findViewById(R.id.view_my_castle);
        if (myCastleView != null)
        {
            TextView castleName = (TextView) myCastleView.findViewById(R.id.castle_name);
            if (castleName != null)
            {
                castleName.setText("名称：" + mCastleInfo.getCname());
            }
            
            TextView castleLocation = (TextView) myCastleView.findViewById(R.id.castle_place);
            if (castleLocation != null)
            {
                castleLocation.setText("地点：" + mCastleInfo.getLocation());
            }
            
            TextView castleNotes = (TextView) myCastleView.findViewById(R.id.castle_msg);
            if (castleNotes != null)
            {
                castleNotes.setText(mCastleInfo.getDesc());
            }
            
            TextView population = (TextView) myCastleView.findViewById(R.id.castle_people);
            if (population != null)
            {
                population.setText("人数：" + mCastleInfo.getPopulation() + "人");
            }
            
            // TODO: level,money,war_money
            
            // 造反
            Button btnRevolt = (Button) findViewById(R.id.revolt_castle);
            btnRevolt.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    // TODO: Add ui to choose camp!!!
                    HttpConnectionUtils hcu = new HttpConnectionUtils(mHandler);
                    hcu.addPostParmeter("uid", mUsrId);
                    hcu.addPostParmeter("cid", mCastleInfo.getCid());
                    // hcu.addPostParmeter("Camp", );
                    hcu.post(Url.Rebel, HttpConnectEvent.HTTP_REBEL);
                }
            });
            
            // 攻城-- Can't find operation from specification
            /*
             * 请求方式 Post 2.请求URI pre-url/castle/gofight 3.参数说明 查询参数 参数名称 参数类型
             * 参数说明 uid String 用户id cid Obj 城堡id
             */
            Button btnAttack = (Button) findViewById(R.id.launch_castle);
            btnAttack.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    
                }
            });
            
            // 守城-- Can't find operation from specification
            Button btnGuard = (Button) findViewById(R.id.guard_castle);
            btnGuard.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    
                }
            });
            
            // 选举投票
            Button btnVoteForAdmin = (Button) findViewById(R.id.vote_for_admin);
            btnVoteForAdmin.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    // TODO: Add ui to choose candidates;
                    /*
                     * HttpConnectionUtils hcu = new
                     * HttpConnectionUtils(mHandler); hcu.addPostParmeter("uid",
                     * mUsrId); hcu.addPostParmeter("cid",
                     * mCastleInfo.getCid()); // hcu.addPostParmeter("Camp", );
                     * hcu.post(Url.Rebel, HttpConnectEvent.HTTP_REBEL);
                     */
                }
            });
            
            // 攻城投票
            Button btnVoteForAttack = (Button) findViewById(R.id.vote_for_attack);
            btnVoteForAttack.setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View v)
                {
                    // TODO: add ui to choose op;
                    HttpConnectionUtils hcu = new HttpConnectionUtils(mHandler);
                    hcu.addPostParmeter("uid", mUsrId);
                    // hcu.addPostParmeter("Op", );
                    // hcu.addPostParmeter("Camp", );
                    hcu.post(Url.VoteForAttack, HttpConnectEvent.HTTP_VOTE_FOR_ATTACK);
                    
                }
            });
            
            // IF I'am Lord, then there should be a btn let me point out a caslte to
            // attack,
            // if i'am a senator, there should be a btn let me to choose support
            // or not
            
            // hide & show btns; and switch diff animations
            // 造反，征战，选举投票，攻城投票，等待指定攻城对象，日常
            int castleState = mCastleInfo.getCastleState();
            switch (castleState)
            {
            // 造反
                case 0:
                {
                    btnRevolt.setVisibility(View.VISIBLE);
                    btnAttack.setVisibility(View.GONE);
                    btnGuard.setVisibility(View.GONE);
                    btnVoteForAdmin.setVisibility(View.GONE);
                    btnVoteForAttack.setVisibility(View.GONE);
                    break;
                }
                
                case 1:
                {
                    btnRevolt.setVisibility(View.GONE);
                    btnAttack.setVisibility(View.VISIBLE);
                    btnGuard.setVisibility(View.VISIBLE);
                    btnVoteForAdmin.setVisibility(View.GONE);
                    btnVoteForAttack.setVisibility(View.GONE);
                    break;
                }
                
                case 2:
                {
                    btnRevolt.setVisibility(View.GONE);
                    btnAttack.setVisibility(View.GONE);
                    btnGuard.setVisibility(View.GONE);
                    btnVoteForAdmin.setVisibility(View.VISIBLE);
                    btnVoteForAttack.setVisibility(View.GONE);
                    break;
                }
                case 3:
                {
                    btnRevolt.setVisibility(View.GONE);
                    btnAttack.setVisibility(View.GONE);
                    btnGuard.setVisibility(View.GONE);
                    btnVoteForAdmin.setVisibility(View.GONE);
                    btnVoteForAttack.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }
    }
    
    // New Castle
    @Deprecated
    private Spinner mSpinner;
    
    @Deprecated
    private void buildNewCastleView()
    {
        mSpinner = (Spinner) findViewById(R.id.new_castle_spinner_locations);
        String[] locations = new String[]
        {
                "123", "222", "123", "1231"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        
        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // showToast("Spinner2: position=" + position + " id=" + id);
            }
            
            public void onNothingSelected(AdapterView<?> parent)
            {
                // showToast("Spinner2: unselected");
            }
        });
        
        // mSpinner.setAdapter()
        
    }
    
    private void showNoJoinCastleView()
    {
        if (null != mButtonMyCastle)
        {
            mButtonMyCastle.setVisibility(View.GONE);
        }
        if (null != mButtonParticipants)
        {
            mButtonParticipants.setVisibility(View.GONE);
        }
        
        showView(INDEX_VIEW_OTHER_CASTLE);
    }
    
}
