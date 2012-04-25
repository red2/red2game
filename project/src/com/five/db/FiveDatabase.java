package com.five.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import com.five.model.UserInfo;

public class FiveDatabase
{
    /**
     * 标记
     */
    private static final String TAG = "IbiduDatabase";
    
    /**
     * Context实例子
     */
    private Context context;
    
    /**
     * DatabaseHelper实例
     */
    private static DatabaseHelper databaseHelper = null;
    
    /**
     * 名称
     */
    private static final String DATABASE_NAME = "ibidu.db";
    
    /**
     * 版本
     */
    private static final int DATABASE_VERSION = 2;
    
    /**
     * 单例
     * 
     */
    private static FiveDatabase instance = null;
    
    /**
     * DatabaseHelper
     * 
     * @author wushengbing
     * 
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        public DatabaseHelper(Context context)
        {
            this(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        public DatabaseHelper(Context context, String name, CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // 创建玩家基本信息的表
            db.execSQL(DataTable.TABLE_GAMER.SQL_CREATE_TABLE);
            db.execSQL(DataTable.TABLE_USERINFO.SQL_CREATE_TABLE);
            db.execSQL(DataTable.TABLE_MESSAGE.SQL_CREATE_TABLE);
        }
        
        @Override
        public synchronized void close()
        {
            Log.d(TAG, "Close Database.");
            super.close();
        }
        
        @Override
        public void onOpen(SQLiteDatabase db)
        {
            Log.d(TAG, "Open Database.");
            super.onOpen(db);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.d(TAG, "onUpgrade");
            db.execSQL("DROP TABLE IF EXISTS " + DataTable.TABLE_GAMER.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DataTable.TABLE_USERINFO.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DataTable.TABLE_MESSAGE.TABLE_NAME);
        }
        
    }
    
    /**
     * 构造
     * 
     * @param context
     */
    public FiveDatabase(Context context)
    {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }
    
    /**
     * 单例模式
     * 
     * @param context
     * @return
     */
    public static synchronized FiveDatabase getInstance(Context context)
    {
        if (null == instance)
        {
            return new FiveDatabase(context);
        }
        return instance;
    }
    
    public UserInfo queryUserInfo()
    {
        UserInfo user = new UserInfo();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DataTable.TABLE_USERINFO.TABLE_NAME, null, null, null, null, null, null);
        try
        {
            if (cursor != null && cursor.moveToFirst())
            {
                user.setM_strUid(cursor.getString(cursor.getColumnIndex(DataTable.TABLE_USERINFO.UID)));
                user.setM_strName(cursor.getString(cursor.getColumnIndex(DataTable.TABLE_USERINFO.NAME)));
                user.setM_iRole(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.TYPE)));
                user.setM_iSex(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.SEX)));
                user.setM_iLevel(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.LEVEL)));
                user.setM_iLevelup(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.LEVELUP)));
                user.setM_iExp(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.EXP)));
                user.setM_iElement(cursor.getInt(cursor.getColumnIndex(DataTable.TABLE_USERINFO.ELEMENT)));
                user.setM_strMoney(cursor.getString(cursor.getColumnIndex(DataTable.TABLE_USERINFO.MONEY)));
                user.setM_strSignature(cursor.getString(cursor.getColumnIndex(DataTable.TABLE_USERINFO.SIGN)));
            }
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            if (cursor != null)
            {
                cursor.close();
            }
        }
        
        return user;
    }
    
    /**
     * 插入一条用户信息
     * 
     * @param wm
     * @return
     */
    public void insertUserData(UserInfo user)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try
        {
            db.beginTransaction();
            
            // 插入一条新消息
            ContentValues initialValues = new ContentValues();
            initialValues.put(DataTable.TABLE_USERINFO.UID, user.getM_strUid());
            initialValues.put(DataTable.TABLE_USERINFO.NAME, user.getM_strName());
            initialValues.put(DataTable.TABLE_USERINFO.TYPE, String.valueOf(user.getM_iRole()));
            initialValues.put(DataTable.TABLE_USERINFO.SEX, String.valueOf(user.getM_iSex()));
            initialValues.put(DataTable.TABLE_USERINFO.LEVEL, String.valueOf(user.getM_iLevel()));
            initialValues.put(DataTable.TABLE_USERINFO.LEVELUP, String.valueOf(user.getM_iLevelup()));
            initialValues.put(DataTable.TABLE_USERINFO.EXP, String.valueOf(user.getM_iExp()));
            initialValues.put(DataTable.TABLE_USERINFO.ELEMENT, String.valueOf(user.getM_iElement()));
            initialValues.put(DataTable.TABLE_USERINFO.MONEY, user.getM_strMoney());
            initialValues.put(DataTable.TABLE_USERINFO.SIGN, user.getM_strSignature());
            
            long id = db.insert(DataTable.TABLE_USERINFO.TABLE_NAME, null, initialValues);
            
            if (-1 == id)
            {
                Log.e(TAG, "cann't insert the tweet : " + user.toString());
            }
            else
            {
                Log.v("TAG", "Insert Status");
            }
            db.setTransactionSuccessful();
        }
        finally
        {
            db.endTransaction();
        }
    }
    
    public void updateUserInfo(String userid, ContentValues values)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.update(DataTable.TABLE_USERINFO.TABLE_NAME, values, DataTable.TABLE_USERINFO.UID + "=" + userid, null);
    }
    
    public void clearUserInfo(int userid)
    {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.delete(DataTable.TABLE_USERINFO.TABLE_NAME, DataTable.TABLE_USERINFO.UID + "=" + userid, null);
    }
    
    /**
     * 
     * @param owner
     * @param type
     * @return
     */
    public Cursor getUserMsg(int userid)
    {
        SQLiteDatabase mDb = databaseHelper.getReadableDatabase();
        
        return mDb.query(DataTable.TABLE_MESSAGE.TABLE_NAME, null, DataTable.TABLE_MESSAGE.FROM + "=" + userid + " or " + DataTable.TABLE_MESSAGE.TO + "=" + userid, null, null, null, 
                DataTable.TABLE_MESSAGE.DATE+" DESC");
    }
}
