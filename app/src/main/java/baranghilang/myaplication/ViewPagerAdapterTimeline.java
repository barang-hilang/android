package baranghilang.myaplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CMDDJ on 5/9/2017.
 */
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

class ViewPagerAdapterTimeline extends PagerAdapter {

    private View v;
   /* private boolean isPagingEnabled = true;

    public ViewPagerAdapterTimeline(Context context) {
        super(context);
    }

    public ViewPagerAdapterTimeline(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }*/

    private Button btnBagi;

    public Object instantiateItem(final ViewGroup collection, int position) {

    v = new View(collection.getContext());

        int resId1 = 0;
        switch (position) {
            case 0:
                resId1 = R.id.primaryContentCardViewBarang;
                break;
            case 1:
                resId1 = R.id.secondaryContentFrameLayoutBarang;

                break;
        }
        return collection.findViewById(resId1);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }






}
