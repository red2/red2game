package com.five.db;

import com.five.model.UserInfo;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

public class DataTable implements BaseColumns
{
    private static final String TAG = "DataTable";

    /**
     * 表名
     */
    public static final String GAMER_TABLE_NAME = "gamer";

    /**
     * id
     */
    public static final String _ID = "_id";

    /**
     * id
     */
    public static final String UID = "uid";

    /**
     * 昵称
     */
    public static final String NAME = "name";

    /**
     * 角色
     */
    public static final String TYPE = "type";

    /**
     * 性别
     */
    public static final String SEX = "sex";

    /**
     * 级别
     */
    public static final String LEVEL = "level";

    /**
     * 升级所需历练
     */
    public static final String LEVELUP = "levelup";

    /**
     * 当前经验历练
     */
    public static final String EXP = "exp";

    /**
     * 五行属性
     */
    public static final String ELEMENT = "element";

    /**
     * 金钱
     */
    public static final String MONEY = "money";

    /**
     * 签名
     */
    public static final String SIGN = "sign";

    /**
     * 选项表头
     */
    public static final String[] GAMER_TABLE_COLUMNS = new String[]
    {
            UID, NAME, TYPE, SEX, LEVEL, LEVELUP, EXP, ELEMENT, MONEY, SIGN

    };

    /**
     * 创建数据库信息表
     */
    public static final String GAMER_TABLE = "CREATE TABLE " + GAMER_TABLE_NAME + " (" + _ID + "  integer primary key autoincrement , " + UID + " text, " + NAME + " text," + TYPE + " text," + SEX
            + " text," + LEVEL + " text," + LEVELUP + " text," + EXP + " text," + ELEMENT + " text," + MONEY + " text, " + SIGN + " text" + ")";
    /**
     * 表名
     */
    public static final String USER_INFO = "userinfo";

    /**
     * 读取数据
     * 
     * @param cursor
     * @return
     */
    public static UserInfo parseUserInfoCursor(Cursor cursor)
    {

        if (null == cursor || 0 == cursor.getCount())
        {
            Log.w(TAG, "Cann't parse Cursor, bacause cursor is null or empty.");
            return null;
        }
        else if (-1 == cursor.getPosition())
        {
            cursor.moveToFirst();
        }
        UserInfo user = new UserInfo();
        user.setM_strUid(cursor.getString(cursor.getColumnIndex(UID)));
        user.setM_strName(cursor.getString(cursor.getColumnIndex(NAME)));
        user.setM_iRole(cursor.getInt(cursor.getColumnIndex(TYPE)));
        user.setM_iSex(cursor.getInt(cursor.getColumnIndex(SEX)));
        user.setM_iLevel(cursor.getInt(cursor.getColumnIndex(LEVEL)));
        user.setM_iLevelup(cursor.getInt(cursor.getColumnIndex(LEVELUP)));
        user.setM_iExp(cursor.getInt(cursor.getColumnIndex(EXP)));
        user.setM_iElement(cursor.getInt(cursor.getColumnIndex(ELEMENT)));
        user.setM_strMoney(cursor.getString(cursor.getColumnIndex(MONEY)));
        user.setM_strSignature(cursor.getString(cursor.getColumnIndex(SIGN)));

        return user;
    }
}