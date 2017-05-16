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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapterTimeline(int[] dataSet0, String[] dataSet, String[] dataSet2) {
        mDataset0 = dataSet0;
        mDataset = dataSet;
        mDataset2 = dataSet2;
        mItemSwipedStates = new ArrayList<>();
        for (int i = 0; i < dataSet.length && i < dataSet2.length; i++) {
            mItemSwipedStates.add(i, SwipedState.SHOWING_PRIMARY_CONTENT1);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapterTimeline.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Create a new view which is basically just a ViewPager in this case
        ViewPager v = (ViewPager) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lv_barang, parent, false);
        ViewPagerAdapterTimeline adapter = new ViewPagerAdapterTimeline();


        ((ViewPager) v.findViewById(R.id.viewPagerBarang)).setAdapter(adapter);

        //Perhaps the first most crucial part. The ViewPager loses its width information when it is put
        //inside a RecyclerView. It needs to be explicitly resized, in this case to the width of the
        //screen. The height must be provided as a fixed value.
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        v.getLayoutParams().width = displayMetrics.widthPixels;
        v.requestLayout();

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
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
                //This method keep incorrectly firing as the RecyclerView scrolls.
                //Use the one above instead
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
