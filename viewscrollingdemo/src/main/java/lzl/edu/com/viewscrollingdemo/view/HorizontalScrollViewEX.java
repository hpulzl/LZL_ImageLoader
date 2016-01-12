package lzl.edu.com.viewscrollingdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by admin on 2016/1/12.
 */
public class HorizontalScrollViewEX extends ViewGroup {
    private static final String TAG="HorizontalScrollViewEX";
    private int mChildSize=0;
    private int mChildWidth = 0;
    private int mLastXIntercept;
    private int mLastYIntercept;
    private int mLastX;
    private int mLastY;
    private int mChildIndex;

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    public HorizontalScrollViewEX(Context context) {
        super(context);
        init(context);
    }



    public HorizontalScrollViewEX(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalScrollViewEX(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        if(mScroller == null){
            mScroller = new Scroller(context);
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    /**
     * 事件拦截
     *
     * 通过手势的滑动，判断是上下滑动还是左右滑动。
     * 如果是左右滑动，则父View进行事件的处理。
     * 反之子View进行处理。
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if(Math.abs(deltaX) > deltaY){ //左右滑动，父容器拦截事件
                    intercept = true;
                }else{
                    intercept = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            default:break;
        }
        Log.i(TAG,"intercept:"+intercept);
        mLastYIntercept = y;
        mLastXIntercept = x;

        Log.i(TAG,"x="+x+" y="+y+" mLastXIntercept="+mLastXIntercept+" mLastYIntercept="+mLastYIntercept);
        return intercept;
    }

    /**
     * 事件处理
     *
     * * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished())
                    mScroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                scrollBy(-deltaX,0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                //计算滑动速率
                float xVelocity = mVelocityTracker.getXVelocity();
                if(Math.abs(xVelocity) >=50){//滑动速率大于50.
                    mChildIndex = xVelocity > 0 ? mChildIndex-1 : mChildIndex+1;
                }else{
                    mChildIndex = (scrollX + mChildWidth /2) / mChildWidth;
                }
                mChildIndex = Math.max(0,Math.min(mChildIndex,mChildSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx,0);
                mVelocityTracker.clear();
                break;
            default:break;
        }
            mLastY = y;
            mLastX = x;
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildSize = childCount;
        for(int i=0;i<childCount;i++){
            final View childView = getChildAt(i);
            if(childView.getVisibility() != GONE){//如果子View没有设置为Gone
                //计算并设置View的位置,并设置
                final int childWidth = childView.getMeasuredWidth();
                mChildWidth = childWidth;
                childView.layout(childLeft,0,childLeft + childWidth,childView.getMeasuredHeight());

                childLeft += childWidth;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //获取宽高模式
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取宽高
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasure(widthSpecMode,heightSpecMode,widthSpecSize,heightSpecSize);
    }
    private void setMeasure(int widthSpecMode, int heightSpecMode, int widthSpecSize, int heightSpecSize){
        int measureHeight = 0;
        int measureWidth = 0;
        int childCount = getChildCount();
        if(childCount == 0){
            setMeasuredDimension(0,0);
        }else if(widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST){  //如果宽高设置为wrap_content
            //获取容器的总宽高
            final View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            measureWidth = childView.getMeasuredHeight() * childCount;
            setMeasuredDimension(measureWidth,measureHeight);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measureWidth = childView.getMeasuredHeight() * childCount;
            setMeasuredDimension(measureWidth,heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measureHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpecSize,measureHeight);
        }else{
            setMeasuredDimension(widthSpecSize,measureHeight);
        }
    }
    private void smoothScrollBy(int dx,int dy){
        mScroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }
}
