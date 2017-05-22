package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import baranghilang.myaplication.R;

import baranghilang.myaplication.RecyclerViewAdapterReport;


/**
 * Created by CMDDJ on 5/8/2017.
 */

public class ReportFragment  extends Fragment {

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


   mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        // mLayoutManager.
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapterReport(mDemoDataSet0, mDemoDataSet, mDemoDataSet2);
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }
}
