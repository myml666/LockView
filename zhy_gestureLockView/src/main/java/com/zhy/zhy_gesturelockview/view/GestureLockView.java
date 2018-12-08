package com.zhy.zhy_gesturelockview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class GestureLockView extends View
{
	private static final String TAG = "GestureLockView";
	/**
	 * GestureLockView的三种状态
	 */
	enum Mode
	{
		STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP_SUCCESS,STATUS_FINGER_UP_FILED;
	}

	/**
	 * GestureLockView的当前状态
	 */
	private Mode mCurrentStatus = Mode.STATUS_NO_FINGER;
	
	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	/**
	 * 外圆半径
	 */
	private int mRadius;
	/**
	 * 画笔的宽度
	 */
	private int mStrokeWidth = 2;

	/**
	 * 圆心坐标
	 */
	private int mCenterX;
	private int mCenterY;
	private Paint mPaint;

	/**
	 * 内圆的半径 = mInnerCircleRadiusRate * mRadus
	 * 
	 */
	private float mInnerCircleRadiusRate = 0.3F;

	/**
	 * 四个颜色，可由用户自定义，初始化时由GestureLockViewGroup传入
	 */
	private int mColorNoFingerInner;
	private int mColorNoFingerOutter;
	private int mColorFingerOn;
	private int mColorFingerUp_SUCCESS;
	private int mColorFingerUp_FILED;
	private int mColorFingerFILL;//按下圆的填充颜色

	public GestureLockView(Context context , int colorNoFingerInner , int colorNoFingerOutter , int colorFingerOn , int colorFingerUpSuccess, int colorFingerUpFiled,int getmColorFingerFILL )
	{
		super(context);
		this.mColorNoFingerInner = colorNoFingerInner;
		this.mColorNoFingerOutter = colorNoFingerOutter;
		this.mColorFingerOn = colorFingerOn;
		this.mColorFingerUp_SUCCESS = colorFingerUpSuccess;
		this.mColorFingerUp_FILED=colorFingerUpFiled;
		this.mColorFingerFILL =getmColorFingerFILL;
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		// 取长和宽中的小值
		mWidth = mWidth < mHeight ? mWidth : mHeight;
		mRadius = mCenterX = mCenterY = mWidth / 2;
		mRadius -= mStrokeWidth / 2;
		mRadius = mRadius-6;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{

		switch (mCurrentStatus)
		{
		case STATUS_FINGER_ON:
			// 绘制外圆填充
			mPaint.setStyle(Style.FILL);
			mPaint.setColor(mColorFingerFILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制外圆
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(mColorFingerOn);
			mPaint.setStrokeWidth(4);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setColor(mColorFingerOn);
			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius
					* mInnerCircleRadiusRate, mPaint);
			break;
			case STATUS_FINGER_UP_SUCCESS:
			// 绘制外圆填充
			mPaint.setStyle(Style.FILL);
			mPaint.setColor(mColorFingerFILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制外圆
			mPaint.setColor(mColorFingerUp_SUCCESS);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(4);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setColor(mColorFingerUp_SUCCESS);
			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius
					* mInnerCircleRadiusRate, mPaint);

			break;
			case STATUS_FINGER_UP_FILED:
				// 绘制外圆填充
				mPaint.setStyle(Style.FILL);
				mPaint.setColor(mColorFingerFILL);
				canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
				// 绘制外圆
				mPaint.setColor(mColorFingerUp_FILED);
				mPaint.setStyle(Style.STROKE);
				mPaint.setStrokeWidth(4);
				canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
				// 绘制内圆
				mPaint.setColor(mColorFingerUp_FILED);
				mPaint.setStyle(Style.FILL);
				canvas.drawCircle(mCenterX, mCenterY, mRadius
						* mInnerCircleRadiusRate, mPaint);
				break;
		case STATUS_NO_FINGER:
			// 绘制外圆
			mPaint.setColor(mColorNoFingerOutter);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(4);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setStyle(Style.FILL);
			mPaint.setColor(mColorNoFingerInner);
			canvas.drawCircle(mCenterX, mCenterY, mRadius
					* mInnerCircleRadiusRate, mPaint);
			break;

		}

	}

	/**
	 * 设置当前模式并重绘界面
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode)
	{
		this.mCurrentStatus = mode;
		invalidate();
	}

}
