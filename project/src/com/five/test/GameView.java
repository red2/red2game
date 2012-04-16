package com.five.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.five.R;
import com.five.util.ConstValue;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    
    /**
     * 游戏线程
     */
    private GameThread gameThread;
    
    /**
     * Context
     */
    private Context mContext;
    
    /**
     * 属性
     */
    private ScreenAttribute mScreenAttribute;
    
    private int mActionPower;
    
    private Bitmap mBackgroundImage;
    
    private Bitmap mRoleStandImage;
    private Bitmap mRoleFreefallImage1;
    private Bitmap mRoleFreefallImage2;
    private Bitmap mRoleFreefallImage3;
    private Bitmap mRoleFreefallImage4;
    private Bitmap mRoleMovingLeftImage1;
    private Bitmap mRoleMovingLeftImage2;
    private Bitmap mRoleMovingLeftImage3;
    private Bitmap mRoleMovingLeftImage4;
    private Bitmap mRoleMovingRightImage1;
    private Bitmap mRoleMovingRightImage2;
    private Bitmap mRoleMovingRightImage3;
    private Bitmap mRoleMovingRightImage4;
    
    private Bitmap mFootboardImage;
    
    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        
        // 初始化资源
        initRes();
        gameThread = new GameThread(holder, context);
        setFocusable(true);
    }
    
    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        mScreenAttribute = new ScreenAttribute(0, 20, width, height);
        gameThread.initUIModel(mScreenAttribute);
        gameThread.setRunning(true);
        gameThread.start();
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        gameThread.setRunning(false);
        while (retry)
        {
            try
            {
                gameThread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                Log.d("", "Surface destroy failure:", e);
            }
        }
    }
    
    public void handleMoving(int angleValue)
    {
        if (gameThread != null)
        {
            gameThread.handleMoving(angleValue);
        }
    }
    
    public void restartGame()
    {
        gameThread = new GameThread(this.getHolder(), this.getContext());
        gameThread.initUIModel(mScreenAttribute);
        gameThread.setRunning(true);
        gameThread.start();
    }
    
    /**
     * 初始化资源
     */
    private void initRes()
    {
        mActionPower = 40;
        Resources res = mContext.getResources();
        mRoleStandImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_standing), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleFreefallImage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_freefall1), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleFreefallImage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_freefall2), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleFreefallImage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_freefall3), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleFreefallImage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_freefall4), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingLeftImage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_left1), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingLeftImage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_left2), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingLeftImage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_left3), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingLeftImage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_left4), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingRightImage1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_right1), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingRightImage2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_right2), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingRightImage3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_right3), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        mRoleMovingRightImage4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.role_moving_right4), UIModel.ROLE_ATTRIBUTE_WIDTH, UIModel.ROLE_ATTRIBUTE_HEITH, true);
        
        mFootboardImage = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, R.drawable.footboard_normal), ConstValue.SCREENWIDTH, 20, true);
        mBackgroundImage = BitmapFactory.decodeResource(res, R.drawable.bg_game);
    }
    
    // thread for updating UI
    class GameThread extends Thread
    {
        private SurfaceHolder mSurfaceHolder;
        
        // 运行标志
        private boolean mRun = true;
        // UI模型
        private UIModel mUIModel;
        // 时间记录器
        private long mTimeLogger;
        
        public GameThread(SurfaceHolder surfaceHolder, Context context)
        {
            this.mSurfaceHolder = surfaceHolder;
        }
        
        @Override
        public void run()
        {
            while (mRun)
            {
                Canvas c = null;
                try
                {
                    mTimeLogger = System.currentTimeMillis();
                    try
                    {
                        mUIModel.logic();
                        c = mSurfaceHolder.lockCanvas(null);
                        synchronized (mSurfaceHolder)
                        {
                            doDraw(c);
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("", "Error at 'run' method", e);
                    }
                    finally
                    {
                        if (c != null)
                        {
                            mSurfaceHolder.unlockCanvasAndPost(c);
                        }
                    }
                    mTimeLogger = System.currentTimeMillis() - mTimeLogger;
                    if (mTimeLogger < UIModel.GAME_ATTRIBUTE_FRAME_DELAY)
                    {
                        Thread.sleep(UIModel.GAME_ATTRIBUTE_FRAME_DELAY - mTimeLogger);
                    }
                }
                catch (Exception ex)
                {
                    Log.d("", "Error at 'run' method", ex);
                }
            }
        }
        
        private void doDraw(Canvas canvas)
        {
            Bitmap tempBitmap = null;
            canvas.drawBitmap(mBackgroundImage, 0, 0, null);
            
            tempBitmap = mFootboardImage;
            canvas.drawBitmap(tempBitmap, 0, 300, null);
            
            Role role = mUIModel.getRoleUIObject();
            {
                switch (role.getRoleSharp())
                {
                    // 帧
                    case UIModel.ROLE_SHARP_FREEFALL_NO1:
                        tempBitmap = mRoleFreefallImage1;
                        break;
                    case UIModel.ROLE_SHARP_FREEFALL_NO2:
                        tempBitmap = mRoleFreefallImage2;
                        break;
                    case UIModel.ROLE_SHARP_FREEFALL_NO3:
                        tempBitmap = mRoleFreefallImage3;
                        break;
                    case UIModel.ROLE_SHARP_FREEFALL_NO4:
                        tempBitmap = mRoleFreefallImage4;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_LEFT_NO1:
                        tempBitmap = mRoleMovingLeftImage1;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_LEFT_NO2:
                        tempBitmap = mRoleMovingLeftImage2;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_LEFT_NO3:
                        tempBitmap = mRoleMovingLeftImage3;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_LEFT_NO4:
                        tempBitmap = mRoleMovingLeftImage4;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_RIGHT_NO1:
                        tempBitmap = mRoleMovingRightImage1;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_RIGHT_NO2:
                        tempBitmap = mRoleMovingRightImage2;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_RIGHT_NO3:
                        tempBitmap = mRoleMovingRightImage3;
                        break;
                    case UIModel.ROLE_SHARP_MOVE_RIGHT_NO4:
                        tempBitmap = mRoleMovingRightImage4;
                        break;
                    default:
                        tempBitmap = mRoleStandImage;
                        break;
                    
                }
                canvas.drawBitmap(tempBitmap, role.getMinX(), 300 - role.getmHeith() + role.getMinY(), null);
            }
            
        }
        
        public void initUIModel(ScreenAttribute screenAttribut)
        {
            mBackgroundImage = Bitmap.createScaledBitmap(mBackgroundImage, screenAttribut.maxX, screenAttribut.maxY, true);
            if (mUIModel != null)
            {
                mRun = false;
                mUIModel.destroy();
            }
            int addVelocity = 0;
            if (mActionPower < 10)
            {
                addVelocity = -2;
            }
            else if (mActionPower < 25)
            {
                addVelocity = -1;
            }
            else if (mActionPower < 50)
            {
                addVelocity = 0;
            }
            else if (mActionPower < 60)
            {
                addVelocity = 1;
            }
            else if (mActionPower < 70)
            {
                addVelocity = 2;
            }
            else if (mActionPower < 80)
            {
                addVelocity = 3;
            }
            else if (mActionPower < 90)
            {
                addVelocity = 4;
            }
            else
            {
                addVelocity = 5;
            }
            mUIModel = new UIModel(screenAttribut, addVelocity);
        }
        
        public void handleMoving(int angleValue)
        {
            if (mUIModel != null)
            {
                mUIModel.handleMoving(angleValue);
            }
        }
        
        public void setRunning(boolean run)
        {
            mRun = run;
        }
    }
    
}
