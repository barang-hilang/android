package baranghilang.myaplication.RecycleViewBarangAdapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/8/2017.
 */

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapterReport extends RecyclerView.Adapter<CustomAdapterReport.ViewHolder> {
    private static final String TAG = "CustomAdapterReport";

    private String[] mDataSet,mDataSet2,mDataSet4,mDataSet5, mDataSet6;
    private int[] mDataSet3;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNamaBarang, tvWaktu, tvLokasi, tvJumlah, tvDeskripsi;
        private final ImageView imgFotoBarang;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getPosition() + " clicked.");
                }
            });
            tvNamaBarang = (TextView) v.findViewById(R.id.namabarang);
            tvWaktu = (TextView) v.findViewById(R.id.waktuhilang);
            tvLokasi = (TextView) v.findViewById(R.id.lokasihilang);
            tvJumlah = (TextView) v.findViewById(R.id.jumlah);
            tvDeskripsi = (TextView) v.findViewById(R.id.deskripsi);
            imgFotoBarang = (ImageView) v.findViewById(R.id.fotobarang);
        }

        public ImageView getImageView() {
            return imgFotoBarang;
        }
        public TextView getTextView() {
            return tvNamaBarang;
        }
        public TextView getTextView2() {
            return tvWaktu;
        }
        public TextView getTextView3() { return  tvLokasi; }
        public TextView getTextView4() { return tvJumlah; }
        public TextView getTextView5() { return  tvDeskripsi; }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapterReport(String[] dataSet,String[] dataSet2, int[] dataSet3, String[] dataSet4, String[] dataSet5, String[] dataSet6 ) {
        this.mDataSet = dataSet;
        this.mDataSet2 = dataSet2;
        this.mDataSet3 = dataSet3;
        this.mDataSet4 = dataSet4;
        this.mDataSet5 = dataSet5;
        this.mDataSet6 = dataSet6;

    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listview_barang, viewGroup, false);

        return new ViewHolder(v);
    }
// END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText("test");
        viewHolder.getTextView2().setText("test");
        viewHolder.getImageView().setImageResource(mDataSet3[position]);
        viewHolder.getTextView3().setText("test");
        viewHolder.getTextView4().setText("test");
        viewHolder.getTextView5().setText("test");


    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}

