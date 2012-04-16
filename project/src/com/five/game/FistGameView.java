package com.five.game;

import java.io.InputStream;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.five.R;
import com.five.util.ConstValue;

public class FistGameView extends SurfaceView implements SurfaceHolder.Callback
{
    /**
     * 
     */
    private Context mContext;
    
    /**
     * 爆炸
     */
    private Bitmap[] mExplosions = new Bitmap[4];
    
    private Bitmap[] mBitmapRock = new Bitmap[1];
    
    private Bitmap[] mBitmapKnife = new Bitmap[1];
    
    private Bitmap[] mBitmapCloth = new Bitmap[1];
    
    /**
     * 游戏背景
     */
    private Bitmap mBitMenuBG = null;
    
    /**
     * 记录两张背景图片时时更新的Y坐标
     */
    private int mBitposY0 = 0;
    private int mBitposY1 = 0;
    
    /**
     * 线程
     */
    private FistThread thread;
    
    /**
     * 
     */
    private Resources mRes;
    
    /**
     * 
     */
    private int mState = -1;
    
    /**
     * 无状态
     */
    public static final int STATE_NULL = -1;
    
    /**
     * 石头状态
     */
    public static final int STATE_ROCK = 0;
    
    /**
     * 剪刀
     */
    public static final int STATE_KNIFE = 1;
    
    /**
     * 布
     */
    public static final int STATE_CLOTH = 2;
    
    /**
     * 掉下来的敌人
     */
    // private Enemy mEnemy[] = null;
    private Vector mEnemyVector;
    
    /**
     * 敌人偏移量
     */
    final static int ENEMY_POS_OFF = 100;
    
    /**
     * 敌人对象的数量
     */
    final static int ENEMY_POOL_COUNT = 20;
    
    /**
     * 
     */
    private int mIndex;
    
    private Paint mPaint = null;
    
    public FistGameView(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    public FistGameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        
        this.mContext = context;
        
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        
        this.mPaint = new Paint();
        
        //
        this.mRes = context.getResources();
        
        //
        initRes();
        
        //
        this.thread = new FistThread(holder, context);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        // TODO Auto-generated method stub
        if (thread != null)
        {
            thread.mRun = true;
            thread.start();
        }
        
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // TODO Auto-generated method stub
        if (thread != null)
        {
            thread.mRun = false;
        }
    }
    
    /**
     * 
     */
    private void initRes()
    {
        // 拳头测试
        mBitmapRock[0] = ReadBitMap(mContext, R.drawable.rock_small);
        mBitmapKnife[0] = ReadBitMap(mContext, R.drawable.knife_small);
        mBitmapCloth[0] = ReadBitMap(mContext, R.drawable.cloth_small);
        
        // 游戏背景
        mBitMenuBG = ReadBitMap(mContext, R.drawable.map_0);
        
        // 爆炸效果图
        mExplosions[0] = ReadBitMap(mContext, R.drawable.asteroid_explode1);
        mExplosions[1] = ReadBitMap(mContext, R.drawable.asteroid_explode2);
        mExplosions[2] = ReadBitMap(mContext, R.drawable.asteroid_explode3);
        mExplosions[3] = ReadBitMap(mContext, R.drawable.asteroid_explode4);
        
        /** 创建敌人对象 **/
        // mEnemy = new Enemy[ENEMY_POOL_COUNT];
        mEnemyVector = new Vector(ENEMY_POOL_COUNT);
        
        for (int i = 0; i < ENEMY_POOL_COUNT; i++)
        {
            Enemy enemy = null;
            if (i % 3 == 0)
            {
                // 石头
                enemy = new Enemy(mContext, mBitmapRock, mExplosions);
                enemy.type = 1;
            }
            if (i % 3 == 1)
            {
                enemy = new Enemy(mContext, mBitmapKnife, mExplosions);
                enemy.type = 2;
            }
            else if (i % 3 == 2)
            {
                enemy = new Enemy(mContext, mBitmapCloth, mExplosions);
                enemy.type = 3;
            }
            enemy.init(ConstValue.SCREENWIDTH / 2 - UtilRandom(0, 3) * 50, -i * ENEMY_POS_OFF);
            mEnemyVector.add(enemy);
        }
    }
    
    int type = -1;
    
    public void handleObject(int type)
    {
        this.type = type;
    }
    
    /**
     * 绘制
     * 
     * @param canvas
     */
    private void doDraw(Canvas canvas)
    {
        
        // 绘制背景
        canvas.drawBitmap(mBitMenuBG, 0, mBitposY0, mPaint);
        
        // 绘制敌人动画
        for (int i = 0; i < ENEMY_POOL_COUNT; i++)
        {
            ((Enemy) mEnemyVector.elementAt(i)).DrawEnemy(canvas, mPaint);
        }
        
        switch (mState)
        {
            case STATE_CLOTH:
            {
                
            }
                break;
            case STATE_KNIFE:
            {
                
            }
                break;
            case STATE_ROCK:
            {
                
            }
                break;
            default:
            {
                // canvas.drawBitmap(mExplosions[mIndex], 100, 100, null);
            }
                break;
        }
    }
    
    /**
     * 逻辑
     */
    private void logic()
    {
        
        /** 绘制敌人动画 **/
        for (int i = 0; i < ENEMY_POOL_COUNT; i++)
        {
            ((Enemy) mEnemyVector.elementAt(i)).UpdateEnemy();
        }
        
        /** 敌机死亡 或者 敌机超过屏幕还未死亡重置坐标 **/
        
        Enemy enemy = (Enemy) mEnemyVector.elementAt(0);
        if (enemy.mState == Enemy.ENEMY_DEATH_STATE || enemy.m_posY + 100 >= ConstValue.SCREENHEIGHT)
        {
            Enemy temp = null;
            if (enemy.type == 1)
            {
                // 石头
                temp = new Enemy(mContext, mBitmapRock, mExplosions);
                temp.type = 1;
            }
            if (enemy.type == 2)
            {
                temp = new Enemy(mContext, mBitmapKnife, mExplosions);
                temp.type = 2;
            }
            else if (enemy.type == 3)
            {
                temp = new Enemy(mContext, mBitmapCloth, mExplosions);
                temp.type = 3;
            }
            
            int tempY = ((Enemy) mEnemyVector.elementAt(ENEMY_POOL_COUNT - 1)).m_posY;
            mEnemyVector.remove(0);
            temp.init(ConstValue.SCREENWIDTH / 2 - UtilRandom(0, 3) * 50, tempY - 100);
            mEnemyVector.add(temp);
        }
        
        switch (type)
        {
            case STATE_CLOTH:
            {
                // 更新子弹与敌人碰撞
                if (((Enemy) mEnemyVector.elementAt(0)).type == 1)
                {
                    ((Enemy) mEnemyVector.elementAt(0)).mAnimState = Enemy.ENEMY_DEATH_STATE;
                }
                
            }
                break;
            case STATE_KNIFE:
            {
                if (((Enemy) mEnemyVector.elementAt(0)).type == 3)
                {
                    ((Enemy) mEnemyVector.elementAt(0)).mAnimState = Enemy.ENEMY_DEATH_STATE;
                }
            }
                break;
            case STATE_ROCK:
            {
                if (((Enemy) mEnemyVector.elementAt(0)).type == 2)
                {
                    ((Enemy) mEnemyVector.elementAt(0)).mAnimState = Enemy.ENEMY_DEATH_STATE;
                }
            }
                break;
            default:
            {
                // canvas.drawBitmap(mExplosions[mIndex], 100, 100, null);
            }
                break;
        }
        type = -1;
    }
    
    /**
     * 游戏线程
     * 
     * @author a
     * 
     */
    class FistThread extends Thread
    {
        /**
         * SurfaceHolder对象
         */
        private SurfaceHolder surfaceHolder;
        
        /**
         * 
         */
        private boolean mRun = false;
        
        public FistThread(SurfaceHolder surfaceHolder, Context context)
        {
            this.surfaceHolder = surfaceHolder;
        }
        
        @Override
        public void run()
        {
            // TODO Auto-generated method stub
            while (mRun)
            {
                Canvas canvas = null;
                try
                {
                    canvas = surfaceHolder.lockCanvas(null);
                    
                    // 游戏逻辑
                    logic();
                    synchronized (surfaceHolder)
                    {
                        // 绘制
                        doDraw(canvas);
                    }
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                
            }
        }
    }
    
    /**
     * 读取本地资源的图片
     * 
     * @param context
     * @param resId
     * @return
     */
    public Bitmap ReadBitMap(Context context, int resId)
    {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
    
    /**
     * 返回一个随机数
     * 
     * @param botton
     * @param top
     * @return
     */
    private int UtilRandom(int botton, int top)
    {
        return ((Math.abs(new Random().nextInt()) % (top - botton)) + botton);
    }
}
