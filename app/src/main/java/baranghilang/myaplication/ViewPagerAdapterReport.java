package baranghilang.myaplication;

/**
 * Created by CMDDJ on 5/9/2017.
 */
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class ViewPagerAdapterReport extends PagerAdapter {

    public Object instantiateItem(ViewGroup collection, int position) {

        int resId2 = 0;
        switch (position) {
            case 0:
                resId2 = R.id.primaryContentCardViewReport;
                break;
            case 1:
                resId2 = R.id.secondaryContentFrameLayoutReport;
                break;
        }
        return collection.findViewById(resId2);
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
