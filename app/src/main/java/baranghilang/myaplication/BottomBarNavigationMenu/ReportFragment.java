package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import baranghilang.myaplication.R;
import baranghilang.myaplication.RecyclerViewAdapterTimeline;
import baranghilang.myaplication.model.BarangModel;
import baranghilang.myaplication.model.PelaporanModel;
import baranghilang.myaplication.model.UserModel;

import static baranghilang.myaplication.R.drawable.ic_icon_24dp;


/**
 * Created by CMDDJ on 5/8/2017.
 */

public class ReportFragment  extends Fragment implements
        View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progressDialog = null;
    List<UserModel> userMod = new ArrayList<>();
    List<PelaporanModel> pelaporanMod = new ArrayList<>();
    List<BarangModel> barangMod = new ArrayList<>();
    private int totalData=0;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_rcycl_report, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvTL);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());


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

    public void getPelaporanByPelapor(){
        RequestQueue requestQueueActive = Volley.newRequestQueue(getActivity());
        StringRequest endpointActive = new StringRequest(Request.Method.GET, "http://barang-hilang.azurewebsites.net/api/v1/pelaporan/pelapor/"+MainMenuActivity.userModel.getIdUser(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Toast.makeText(getActivity(),"Getting Data ...",Toast.LENGTH_LONG).show();
                            pelaporanMod.clear();
                            userMod.clear();
                            barangMod.clear();
                            JSONObject result = new JSONObject(response);
                            JSONArray results = result.getJSONArray("result");
                            totalData = results.length();

                            for (int i = 0; i < results.length(); i++) {
                                Log.e("Total array : ",String.valueOf(results.length()));

                                PelaporanModel pelaporan = new PelaporanModel();
                                UserModel user = new UserModel();
                                BarangModel barang = new BarangModel();

                                //Untuk Pelaporan
                                pelaporan.setIdLapor(results.getJSONObject(i).getString("idPelaporan"));
                                Log.e("Pelaporan : ",results.getJSONObject(i).getString("idPelaporan"));

                                pelaporan.setIdPelapor(results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));
                                Log.e("Id Pelapor : ",results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));

                                pelaporan.setIdBarang(results.getJSONObject(i).getJSONObject("barang").getString("idBarang"));
                                Log.e("Id Barang : ",results.getJSONObject(i).getJSONObject("barang").getString("idBarang"));

                                pelaporan.setKeterangan(results.getJSONObject(i).getString("keterangan"));
                                Log.e("Keterangan : ",results.getJSONObject(i).getString("keterangan"));

                                pelaporan.setLokHilang(results.getJSONObject(i).getString("tempatHilang"));
                                Log.e("Tempat : ",results.getJSONObject(i).getString("tempatHilang"));

                                pelaporan.setTglHilang(results.getJSONObject(i).getString("tanggalHilang"));
                                Log.e("Tanggal : ",results.getJSONObject(i).getString("tanggalHilang"));

//                                pelaporan.setLokDitemukan(results.getJSONObject(i).getString("tempatDitemukan"));
//                                Log.e("Tempat : ",results.getJSONObject(i).getString("tempatDitemukan"));
//
//                                pelaporan.setTglDitemukan(results.getJSONObject(i).getString("tanggalDitemukan"));
//                                Log.e("Tanggal : ",results.getJSONObject(i).getString("tanggalDitemukan"));

//                                pelaporan.setIdPenemu(results.getJSONObject(i).getJSONObject("penemu").getString("idUser"));

                                //Untuk Pelapor
//                                user.setIdUser(results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));
//                                Log.e("Id Pelapor : ",results.getJSONObject(i).getJSONObject("pelapor").getString("idUser"));
//
//                                user.setUsername(results.getJSONObject(i).getJSONObject("pelapor").getString("username"));
//                                Log.e("Nama : ",results.getJSONObject(i).getJSONObject("pelapor").getString("username"));
//
//                                //Untuk Barang
//                                barang.setIdBarang(results.getJSONObject(i).getJSONObject("barang").getString("idBarang"));
//                                Log.e("Id Barang : ",results.getJSONObject(i).getJSONObject("barang").getString("idBarang"));
//
//                                barang.setIdKateg(results.getJSONObject(i).getJSONObject("barang").getJSONObject("kategoriBarang").getString("idKategoriBarang"));
//                                Log.e("ID Kategori : ",results.getJSONObject(i).getJSONObject("barang").getJSONObject("kategoriBarang").getString("idKategoriBarang"));
//
//                                barang.setIdUser(results.getJSONObject(i).getJSONObject("barang").getJSONObject("user").getString("idBarang"));
//                                Log.e("ID Pemilik : ",results.getJSONObject(i).getJSONObject("barang").getJSONObject("user").getString("idBarang"));
//
//                                barang.setJumlahBarang(results.getJSONObject(i).getJSONObject("barang").getString("jumlah"));
//                                Log.e("Jumlah : ",results.getJSONObject(i).getJSONObject("barang").getString("jumlah"));

                                barang.setNamaBarang(results.getJSONObject(i).getJSONObject("barang").getString("nama"));
                                Log.e("Nama Barang : ",results.getJSONObject(i).getJSONObject("barang").getString("nama"));

                                barang.setStatusBarang(results.getJSONObject(i).getJSONObject("barang").getString("status"));
                                Log.e("Status : ",results.getJSONObject(i).getJSONObject("barang").getString("status"));
//
//                                barang.setUrlImage(results.getJSONObject(i).getJSONObject("barang").getString("url_image"));
//                                Log.e("URL : ",results.getJSONObject(i).getJSONObject("barang").getString("url_image"));

                                pelaporanMod.add(pelaporan);
                                userMod.add(user);
                                barangMod.add(barang);


                                Log.e("Keterangan : ",pelaporanMod.get(i).getKeterangan());
                                Log.e("Barang : ",barangMod.get(i).getNamaBarang());

                                Log.e("Test","reach end");

                                Toast.makeText(getActivity(),"Getting Data Success",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("apiKey","wakowakowakowa");
                return params;
            }
        };

        requestQueueActive.add(endpointActive);

    }

    public void setAdapter(){
        ArrayList<String> tempNamaBarang = new ArrayList<>();
        ArrayList<String> tempKeterangan = new ArrayList<>();
        ArrayList<Integer> tempImage = new ArrayList<>();
        for (int i = 0; i < totalData; i++) {
            tempImage.add(ic_icon_24dp);
            tempNamaBarang.add(barangMod.get(i).getNamaBarang());
            tempKeterangan.add(pelaporanMod.get(i).getKeterangan());
        }
        int[] image = new int[tempImage.size()];
        for (int i = 0; i < tempImage.size(); i++) {
            image[i] = tempImage.get(i);
        }

        String[] namaBarang = new String[tempNamaBarang.size()];
        for(int j =0;j<tempNamaBarang.size();j++){
            namaBarang[j] = tempNamaBarang.get(j);
        }

        String[] keterangan = new String[tempKeterangan.size()];
        for(int j =0;j<tempKeterangan.size();j++){
            keterangan[j] = tempKeterangan.get(j);
        }


        mAdapter = new RecyclerViewAdapterTimeline(image,namaBarang,keterangan);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

}


