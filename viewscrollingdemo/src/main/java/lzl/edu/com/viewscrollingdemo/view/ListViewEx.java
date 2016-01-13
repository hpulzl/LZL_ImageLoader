package lzl.edu.com.viewscrollingdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by admin on 2016/1/12.
 */
public class ListViewEx extends ListView {
    private static final String TAG = "ListViewEx";
    private HorizontalScrollViewEX2 horizontalScrollViewEX2;
    //上次滑动的地方
    private int mLastX;
    private int mLastY;
    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHorizontalScrollViewEX2(HorizontalScrollViewEX2 horizontalScrollViewEX2) {
        this.horizontalScrollViewEX2 = horizontalScrollViewEX2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG," ----------------------mLastX="+mLastY+"---------------mLastY="+mLastY);
                //告诉父View，不直接调用父View的onInterceptTouchEvent()方法，直接将方法分发给子View
                horizontalScrollViewEX2.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.i(TAG," ----------------------mLastX="+mLastY+"---------------mLastY="+mLastY);
                if(Math.abs(deltaX) > Math.abs(deltaY)){  //左右滑动的话，就转发给父View来处理
                    horizontalScrollViewEX2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG," ----------------------MotionEvent...ACTION_UP");
                break;
            default:break;
        }
        mLastX = x;
        mLastY = y;
        Log.i(TAG," mLastX="+mLastY+"mLastY="+mLastY);
        return super.dispatchTouchEvent(ev);
    }
}
