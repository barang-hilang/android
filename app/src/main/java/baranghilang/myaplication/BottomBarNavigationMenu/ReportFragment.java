package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import baranghilang.myaplication.R;

import baranghilang.myaplication.RecyclerViewAdapterReport;
import baranghilang.myaplication.RecyclerViewAdapterTimeline;


/**
 * Created by CMDDJ on 5/8/2017.
 */

public class ReportFragment  extends Fragment implements
        View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int[] mDemoDataSet0 = {R.drawable.ic_icon_24dp,
            R.drawable.ic_icon_24dp,
            R.drawable.ic_icon_24dp
    };

    private String[] mDemoDataSet = {"Flashdisk 8GB",
            "HP VIVO F5"
    };

    private String[] mDemoDataSet2 = {"warna hitam, merk toshiba",
            "Casing warna putihhhhhhhhhh"
    };

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_rcycl_report, container, false);


        // btnBagi = (Button) v.findViewById(R.id.btnBagikan);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvTL);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        // mLayoutManager.

        mAdapter = new RecyclerViewAdapterTimeline(mDemoDataSet0,mDemoDataSet,mDemoDataSet2);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        btnBagi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Konten dibagikan", Toast.LENGTH_SHORT).show();
//            }
//        });

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(getActivity(), ManageReport.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

                Intent intent = new Intent(getActivity(), ManageReport.class);
                startActivity(intent);
            }
        }) );

        return v;

    }


    @Override
    public void onClick(View v) {

    }

    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }
}
