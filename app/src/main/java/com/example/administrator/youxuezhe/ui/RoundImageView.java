package com.example.administrator.youxuezhe.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.youxuezhe.R;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/3/27 0027.
 * 自定义圆形的ImageView
 */

public class RoundImageView extends AppCompatImageView {
    /**
     * 圆角ImageView圆角的半径大小
     */
    private int mRadius=dp2px(10);
    /**
     * 圆形类型
     */
    private int TYPE_CIRCLE=0;
    /**
     * 圆角类型
     */
    private int TYPED_ROUND=1;
    /**
     * 图片类型
     */
    private int mType=TYPE_CIRCLE;
    /**
     * 图片缩放模式
     */
    private ImageView.ScaleType mScaleType;
    /**
     * 缓存bitmap
     */
    private WeakReference<Bitmap> mWeakReference;
    /**
     * 模板Bitmap
     */
    private Bitmap mMaskBitmap;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * shape paint
     */
    private Paint shapePaint;
    /**
     * 画笔Xfermode
     */
    private Xfermode mXfermode=new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyleAttr(context,attrs,defStyleAttr);
        mScaleType=getScaleType();
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        shapePaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
    }

    private void obtainStyleAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView,defStyleAttr,0);
        mRadius=a.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius,mRadius);
        mType=a.getInteger(R.styleable.RoundImageView_type,mType);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap = mWeakReference==null?null:mWeakReference.get();
        if(bitmap==null || bitmap.isRecycled()){
            //获取一下设置的图片资源
            Drawable drawable=getDrawable();
            if(drawable!=null){
                //创建一个空白画布，用来画模板跟原图
                bitmap=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
                Canvas dstCanvas=new Canvas(bitmap);
                //画原图
                drawable.draw(dstCanvas);
                //设置画笔的Xfermode
                mPaint.setXfermode(mXfermode);
                //画模板
                if(mMaskBitmap==null||mMaskBitmap.isRecycled()){
                    mMaskBitmap=getShapeBitmap();
                }
                dstCanvas.drawBitmap(mMaskBitmap,0,0,mPaint);
                mPaint.setXfermode(null);
            }
        }
        //最后把我们准备好的Bitmap画在canvas上
        try {
            canvas.drawBitmap(bitmap,0,0,null);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    /**
     * 根据Shape类型创建ShapeBitmap
     */
    private Bitmap getShapeBitmap() {
        Bitmap bitmap=Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas(bitmap);
        if(TYPE_CIRCLE==mType){
            canvas.drawCircle(canvas.getWidth()/2,canvas.getHeight()/2,canvas.getWidth()/2,shapePaint);
        } else{
            canvas.drawRoundRect(new RectF(0,0,canvas.getWidth(),canvas.getHeight()),mRadius,mRadius,shapePaint);
        }
        return bitmap;
    }

    /**
     * dp2px
     * @param value
     * @return px
     */
    private int dp2px(int value) {
        return (int) (value*getContext().getResources().getDisplayMetrics().density+0.5f);
    }
}