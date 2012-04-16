package com.five.test;


public class UIModel
{
    
    /**
     * 游戏属性常量
     */
    
    /**
     * 帧刷新间隔(单位微妙)
     */
    public static final int GAME_ATTRIBUTE_FRAME_DELAY = 30;
    
    /**
     * 游戏活动对象Y方向的像素密度(将1个单位像素拆分为更小单元)
     */
    public static final int GAME_ATTRIBUTE_PIXEL_DENSITY_Y = 10;
    
    /**
     * 游戏状态
     */
    public static final int GAME_STATUS_RUNNING = 1;
    
    /**
     * 主角的长度和宽度
     */
    public static final int ROLE_ATTRIBUTE_WIDTH = 32;
    public static final int ROLE_ATTRIBUTE_HEITH = 48;
    
    /**
     * 主角帧刷新间隔
     */
    public static final int ROLE_ATTRIBUTE_FRAME_DELAY = 2;
    
    /**
     * 主角状态
     */
    public static final int ROLE_STATUS_ON_FOOTBOARD = 0;
    public static final int ROLE_STATUS_ON_FOOTBOARD_LEFT = 1;
    public static final int ROLE_STATUS_ON_FOOTBOARD_RIGHT = 2;
    public static final int ROLE_STATUS_FREEFALL = 3;
    public static final int ROLE_STATUS_FREEFALL_LEFT = 4;
    public static final int ROLE_STATUS_FREEFALL_RIGHT = 5;
    
    /**
     * 主角帧
     */
    public static final int ROLE_SHARP_STANDING = 0;
    public static final int ROLE_SHARP_FREEFALL_NO1 = 1;
    public static final int ROLE_SHARP_FREEFALL_NO2 = 2;
    public static final int ROLE_SHARP_FREEFALL_NO3 = 3;
    public static final int ROLE_SHARP_FREEFALL_NO4 = 4;
    public static final int ROLE_SHARP_MOVE_LEFT_NO1 = 5;
    public static final int ROLE_SHARP_MOVE_LEFT_NO2 = 6;
    public static final int ROLE_SHARP_MOVE_LEFT_NO3 = 7;
    public static final int ROLE_SHARP_MOVE_LEFT_NO4 = 8;
    public static final int ROLE_SHARP_MOVE_RIGHT_NO1 = 9;
    public static final int ROLE_SHARP_MOVE_RIGHT_NO2 = 10;
    public static final int ROLE_SHARP_MOVE_RIGHT_NO3 = 11;
    public static final int ROLE_SHARP_MOVE_RIGHT_NO4 = 12;
    
    /**
     * 游戏界面属性
     */
    private ScreenAttribute mScreenAttribute;
    
    /**
     * 游戏状态
     */
    public int mGameStatus = GAME_STATUS_RUNNING;
    
    /**
     * 游戏主角
     */
    private Role mRole;
    
    /**
     * 主角X方向移动速度
     */
    private int mRoleVelocityX;
    
    /**
     * 主角Y方向移动速度
     */
    private int mRoleVelocityY;
    
    /**
     * 重力速度(即主角离开踏板后的y方向速度)
     */
    public static final int GAME_ATTRIBUTE_GRAVITY_VELOCITY = 5 * GAME_ATTRIBUTE_PIXEL_DENSITY_Y;
    
    /**
     * 踏板移动速度
     */
    private int mFootboartVelocity = -3 * GAME_ATTRIBUTE_PIXEL_DENSITY_Y;
    
    /**
     * 附加速度(用于控制速度,在选项面板里设定)
     */
    private int mAddVelocity;
    
    public UIModel(ScreenAttribute screenAttribute, int addVelocity)
    {
        mScreenAttribute = screenAttribute;
        mAddVelocity = addVelocity;
        mRole = new Role((screenAttribute.maxX - ROLE_ATTRIBUTE_WIDTH) / 2, screenAttribute.maxY * 3 / 4, ROLE_ATTRIBUTE_WIDTH, ROLE_ATTRIBUTE_HEITH, ROLE_ATTRIBUTE_FRAME_DELAY);
    }
    
    /**
     * 更新UI模型
     */
    public void logic()
    {
        mRole.addX(mRoleVelocityX);
        mRole.addY(mRoleVelocityY);
        handleBorder();
        handleRoleAction();
    }
    
    /**
     * 处理主角移动
     * 
     * @param angleValue
     */
    public void handleMoving(int angleValue)
    {
        switch (angleValue)
        {
            case 1:
            {
                // 左
                mRoleVelocityX = -5 + mAddVelocity;
            }
                break;
            case 2:
            {
                // 右
                mRoleVelocityX = 5 + mAddVelocity;
            }
                break;
            case 3:
            {
                mRoleVelocityX = 0;
                mRole.setRoleStatus(UIModel.ROLE_STATUS_ON_FOOTBOARD);
            }
                break;
            case 4:
            {
                // 跳
                mRole.setRoleStatus(UIModel.ROLE_STATUS_FREEFALL);
            }
                break;
            
            default:
                break;
        }
    }
    
    public static final int BORDER_ATTRIBUTE_IMAGE_HEITH = 20;
    
    /**
     * 处理边界
     */
    private void handleBorder()
    {
        if (mRole.getMinX() < mScreenAttribute.minX)
        {
            mRoleVelocityX = 0;
            mRole.setX(0);
            return;
        }
        if (mRole.getMaxX() > mScreenAttribute.maxX)
        {
            mRoleVelocityX = 0;
            mRole.setX(mScreenAttribute.maxX - ROLE_ATTRIBUTE_WIDTH);
            return;
        }
        
    }
    
    /**
     * 处理主角在踏板上的活动
     */
    private void handleRoleAction()
    {
        Role role = mRole;
        {
            if (role.getRoleStatus() == ROLE_STATUS_ON_FOOTBOARD || role.getRoleStatus() == ROLE_STATUS_ON_FOOTBOARD_RIGHT || role.getRoleStatus() == ROLE_STATUS_ON_FOOTBOARD_LEFT)
            {
                updateRoleStatus(ROLE_STATUS_ON_FOOTBOARD);
            }
            else if (role.getRoleStatus() == ROLE_STATUS_FREEFALL)
            {
                mRoleVelocityY = mFootboartVelocity - GAME_ATTRIBUTE_GRAVITY_VELOCITY;
                role.addY(-1 * GAME_ATTRIBUTE_PIXEL_DENSITY_Y);
                updateRoleStatus(ROLE_STATUS_FREEFALL);
            }
            else
            {
                // 主角第一次触板
                updateRoleStatus(ROLE_STATUS_ON_FOOTBOARD);
            }
            
        }
        updateRoleStatus(ROLE_STATUS_FREEFALL);
    }
    
    private void updateRoleStatus(int status)
    {
        if (status == ROLE_STATUS_FREEFALL)
        {
            if (mRoleVelocityX > 0)
            {
                mRole.setRoleStatus(ROLE_STATUS_FREEFALL_RIGHT);
            }
            else if (mRoleVelocityX < 0)
            {
                mRole.setRoleStatus(ROLE_STATUS_FREEFALL_LEFT);
            }
            else
            {
                mRole.setRoleStatus(ROLE_STATUS_ON_FOOTBOARD);
            }
        }
        else
        {
            if (mRoleVelocityX > 0)
            {
                mRole.setRoleStatus(ROLE_STATUS_ON_FOOTBOARD_RIGHT);
            }
            else if (mRoleVelocityX < 0)
            {
                mRole.setRoleStatus(ROLE_STATUS_ON_FOOTBOARD_LEFT);
            }
            else
            {
                mRole.setRoleStatus(ROLE_STATUS_ON_FOOTBOARD);
            }
        }
    }
    
    /**
     * 清除操作
     */
    public void destroy()
    {
        mScreenAttribute = null;
        mRole = null;
    }
    
    public Role getRoleUIObject()
    {
        return mRole;
    }
}
