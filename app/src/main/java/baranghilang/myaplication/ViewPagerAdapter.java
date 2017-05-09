package baranghilang.myaplication;

/**
 * Created by CMDDJ on 5/9/2017.
 */
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class ViewPagerAdapter extends PagerAdapter {

    public Object instantiateItem(ViewGroup collection, int position) {

        int resId = 0;
        switch (position) {
            case 0:
                resId = R.id.primaryContentCardView;
                break;
            case 1:
                resId = R.id.secondaryContentFrameLayout;
                break;
        }
        return collection.findViewById(resId);
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
