package baranghilang.myaplication;

/**
 * Created by CMDDJ on 5/9/2017.
 */
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterTimeline extends RecyclerView.Adapter<RecyclerViewAdapterTimeline.ViewHolder> {
    private String[] mDataset, mDataset2;
    private int[] mDataset0;




    private enum SwipedState {
        SHOWING_PRIMARY_CONTENT1,
        SHOWING_SECONDARY_CONTENT1
    }

    private List<SwipedState> mItemSwipedStates;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public RecyclerViewAdapterTimeline(int[] dataSet0, String[] dataSet, String[] dataSet2) {
        mDataset0 = dataSet0;
        mDataset = dataSet;
        mDataset2 = dataSet2;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i < dataSet.length && i < dataSet2.length; i++) {
            mItemSwipedStates.add(i, SwipedState.SHOWING_PRIMARY_CONTENT1);
        }
    }

    @Override
    public RecyclerViewAdapterTimeline.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        ViewPager v = (ViewPager) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lv_barang, parent, false);
        ViewPagerAdapterTimeline adapter = new ViewPagerAdapterTimeline();


        ((ViewPager) v.findViewById(R.id.viewPagerBarang)).setAdapter(adapter);


        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        v.getLayoutParams().width = displayMetrics.widthPixels;
        v.requestLayout();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ((ImageView) holder.mView.findViewById(R.id.imgFotobrg)).setImageResource(mDataset0[position]);
        ((TextView) holder.mView.findViewById(R.id.tvNamabrg)).setText(mDataset[position]);
        ((TextView) holder.mView.findViewById(R.id.tvDeskripsi)).setText(mDataset2[position]);

        Log.i("MyAdapter", "PagePosition " + position + " set to " + mItemSwipedStates.get(position).ordinal());
        ((ViewPager) holder.mView).setCurrentItem(mItemSwipedStates.get(position).ordinal());

       ((ViewPager) holder.mView).setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int previousPagePosition = 0;

            @Override
            public void onPageScrolled(int pagePosition, float positionOffset, int positionOffsetPixels) {
                if (pagePosition == 0)
                    return;

                switch (pagePosition) {
                    case 0:
                        mItemSwipedStates.set(position, SwipedState.SHOWING_PRIMARY_CONTENT1);
                        break;
                    case 1:
                        mItemSwipedStates.set(position, SwipedState.SHOWING_SECONDARY_CONTENT1);
                        break;

                }
                previousPagePosition = pagePosition;

                Log.i("MyAdapter", "PagePosition " + position + " set to " + mItemSwipedStates.get(position).ordinal());
            }

            @Override
            public void onPageSelected(int pagePosition) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
