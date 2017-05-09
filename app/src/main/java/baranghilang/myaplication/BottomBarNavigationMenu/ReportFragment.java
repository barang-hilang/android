package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import baranghilang.myaplication.R;
import baranghilang.myaplication.RecycleViewBarangAdapter.CustomAdapter;

import baranghilang.myaplication.R;
import baranghilang.myaplication.RecycleViewBarangAdapter.CustomAdapterReport;
import baranghilang.myaplication.RecyclerViewAdapter;


/**
 * Created by CMDDJ on 5/8/2017.
 */
public class ReportFragment  extends Fragment {

   /* private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60; // menampilkan data sebanyak value

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CustomAdapterReport mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset, mDataset2, mDataset4, mDataset5, mDataSet6;
    protected int[] mDataset3;

    int [] fotobarang = {R.drawable.ic_icon_24dp, R.drawable.ic_icon_24dp,R.drawable.ic_icon_24dp};
    String [] namabarang = {"Flashdisk 16GB","Dompet POLO Putih","HP VIVO F5"};
    String [] waktuhilang = {"13:25","10:45","09:32"};
    String [] lokasihilang = {"Kampus 3 UAJY","Shelter TJ Babarsari","The Crabbys JWalk"};
    String [] jumlah = {"2","1","1"};
    String [] deskripsi = {"Merk Sandisk","Ada SIM a/n Yunita","Ada casing warna pink"};





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

*/

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String[] mDemoDataSet = {"Flashdisk 8GB",
            "HP VIVO F5"+"\nCasing warna putih"
    };

    private String[] mDemoDataSet2 = {"warna hitam, merk toshiba",
            "Casing warna putih"
    };



    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_item, container, false);
       /* v.setTag(TAG);*/

   mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        // mLayoutManager.
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(mDemoDataSet);
        mRecyclerView.setAdapter(mAdapter);

/*
        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycleView2);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new CustomAdapterReport(mDataset,mDataset2,mDataset3,mDataset4, mDataset5, mDataSet6);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

*/
        return v;
    }

    /*public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.

    private void initDataset() {
        mDataset = new String[namabarang.length];
        mDataset2 = new String[waktuhilang.length];
        mDataset3 = new int[fotobarang.length];
        mDataset4 = new String[lokasihilang.length];
        mDataset5 = new String[jumlah.length];
        mDataSet6 = new String[deskripsi.length];
        for (int i = 0; i < namabarang.length; i++) {
            mDataset[i] = namabarang[i];
            mDataset2[i] = waktuhilang[i];
            mDataset3[i] = fotobarang[i];
            mDataset4[i] = lokasihilang[i];
            mDataset5[i] = jumlah[i];
            mDataSet6[i] = deskripsi[i];
        }
    }*/
}
