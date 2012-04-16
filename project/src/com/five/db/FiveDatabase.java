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
    private static final int DATABASE_VERSION = 1;

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
            db.execSQL(DataTable.GAMER_TABLE);
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
            db.execSQL("DROP TABLE IF EXISTS " + DataTable.GAMER_TABLE_NAME);
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

    /**
     * 清空数据
     * 
     * @return
     */
    public int clearWeatherData()
    {
        SQLiteDatabase mDb = databaseHelper.getReadableDatabase();

        return mDb.delete(DataTable.USER_INFO, null, null);
    }

    /**
     * 插入一条用户信息
     * 
     * @param wm
     * @return
     */
    public void insertUserData(UserInfo user)
    {
        Log.i("iiiiiiiiiiiiiiiiiii", "iiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        // uid
        // name
        // type
        // sex
        // level
        // levelup
        // exp
        // element
        // money
        // sign
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try
        {
            db.beginTransaction();

            // 插入一条新消息
            ContentValues initialValues = new ContentValues();
            initialValues.put(DataTable.UID, user.getM_strUid());
            initialValues.put(DataTable.NAME, user.getM_strName());
            initialValues.put(DataTable.TYPE, String.valueOf(user.getM_iRole()));
            initialValues.put(DataTable.SEX, String.valueOf(user.getM_iSex()));
            initialValues.put(DataTable.LEVEL, String.valueOf(user.getM_iLevel()));
            initialValues.put(DataTable.LEVELUP, String.valueOf(user.getM_iLevelup()));
            initialValues.put(DataTable.EXP, String.valueOf(user.getM_iExp()));
            initialValues.put(DataTable.ELEMENT, String.valueOf(user.getM_iElement()));
            initialValues.put(DataTable.MONEY, user.getM_strMoney());
            initialValues.put(DataTable.SIGN, user.getM_strSignature());

            long id = db.insert(DataTable.GAMER_TABLE_NAME, null, initialValues);

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

    /**
     * 
     * @param owner
     * @param type
     * @return
     */
    public Cursor getUserMsg()
    {
        SQLiteDatabase mDb = databaseHelper.getReadableDatabase();

        return mDb.query(DataTable.GAMER_TABLE_NAME, DataTable.GAMER_TABLE_COLUMNS, null, null, null, null, null);
    }
}
