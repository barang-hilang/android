package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import baranghilang.myaplication.R;
import baranghilang.myaplication.RecyclerViewAdapterTimeline;
import baranghilang.myaplication.model.BarangModel;
import baranghilang.myaplication.model.PelaporanModel;
import baranghilang.myaplication.model.UserModel;


/**
 * Created by CMDDJ on 5/8/2017.
 */
public class TimelineFragment extends Fragment  implements
        View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int DIALOG1 = 1;
    private Button btnBagi;

    ArrayList<UserModel> userMod = new ArrayList<>();
    ArrayList<PelaporanModel> pelaporanMod = new ArrayList<>();
    ArrayList<BarangModel> barangMod = new ArrayList<>();
    public ProgressDialog progressDialog = null;

    private int[] mDemoDataSet0 = {R.drawable.ic_icon_24dp,
            R.drawable.ic_icon_24dp,
            R.drawable.ic_icon_24dp
    };
//
//    private String[] mDemoDataSet = {"Flashdisk 8GB",
//            "HP VIVO F5"
//    };
//
//    private String[] mDemoDataSet2 = {"warna hitam, merk toshiba",
//            "Casing warna putihhhhhhhhhh"
//    };

    private String[] urlImage,namaBarang,keterangan;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_rcycl_timeline, container, false);

        getPelaporan();
        setAdater();

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabRefresh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPelaporan();
                setAdater();
            }
        });

       // btnBagi = (Button) v.findViewById(R.id.btnBagikan);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvTL);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        // mLayoutManager.

        mAdapter = new RecyclerViewAdapterTimeline(mDemoDataSet0,namaBarang,keterangan);
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

                Intent intent = new Intent(getActivity(), DetailReport.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

                Intent intent = new Intent(getActivity(), DetailReport.class);
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


    public void getPelaporan(){
        RequestQueue requestQueueActive = Volley.newRequestQueue(getActivity());
        StringRequest endpointActive = new StringRequest(Request.Method.GET, "http://barang-hilang.azurewebsites.net/api/v1/pelaporan",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pelaporanMod.clear();
                            userMod.clear();
                            barangMod.clear();
                            JSONObject result = new JSONObject(response);
                            JSONArray results = result.getJSONArray("result");
                            for (int i = 0; i < results.length(); i++) {
                                PelaporanModel pelaporan = new PelaporanModel();
                                UserModel user = new UserModel();
                                BarangModel barang = new BarangModel();

                                //Untuk Pelaporan
                                pelaporan.setIdLapor(results.getJSONObject(i).getString("idPelaporan"));
                                pelaporan.setIdPelapor(results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));
                                pelaporan.setIdBarang(results.getJSONObject(i).getJSONObject("barang").getString("id_barang"));
                                pelaporan.setKeterangan(results.getJSONObject(i).getString("keterangan"));
                                pelaporan.setLokHilang(results.getJSONObject(i).getString("tempatHilang"));
                                pelaporan.setTglHilang(results.getJSONObject(i).getString("tanggalHilang"));
                                pelaporan.setLokDitemukan(results.getJSONObject(i).getString("tempatDitemukan"));
                                pelaporan.setTglDitemukan(results.getJSONObject(i).getString("tanggalDitemukan"));
                                pelaporan.setIdPenemu(results.getJSONObject(i).getJSONObject("penemu").getString("idUser"));

                                //Untuk Pelapor
                                user.setIdUser(results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));
                                user.setUsername(results.getJSONObject(i).getJSONObject("pelapor").getString("username"));

                                //Untuk Barang
                                barang.setIdBarang(results.getJSONObject(i).getJSONObject("barang").getString("idBarang"));
                                barang.setIdKateg(results.getJSONObject(i).getJSONObject("barang").getJSONObject("kategoriBarang").getString("idKategoriBarang"));
                                barang.setIdUser(results.getJSONObject(i).getJSONObject("barang").getJSONObject("user").getString("idBarang"));
                                barang.setJumlahBarang(results.getJSONObject(i).getJSONObject("barang").getString("jumlah"));
                                barang.setNamaBarang(results.getJSONObject(i).getJSONObject("barang").getString("nama"));
                                barang.setStatusBarang(results.getJSONObject(i).getJSONObject("barang").getString("status"));
                                barang.setUrlImage(results.getJSONObject(i).getJSONObject("barang").getString("url_image"));

                                pelaporanMod.add(pelaporan);
                                userMod.add(user);
                                barangMod.add(barang);

                                for (PelaporanModel modelPelaporan: pelaporanMod) {
                                    Log.e("Data : ",modelPelaporan.toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        progressDialog = new ProgressDialog(getActivity(),R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();

        requestQueueActive.add(endpointActive);

    }




    private void setAdater(){
        for (int i = 0; i < pelaporanMod.size(); i++) {
            urlImage[i]=barangMod.get(i).getUrlImage();
            namaBarang[i]=barangMod.get(i).getNamaBarang();
            keterangan[i]=pelaporanMod.get(i).getKeterangan();
        }
    }

}
