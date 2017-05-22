package baranghilang.myaplication.BottomBarNavigationMenu;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import baranghilang.myaplication.CustomToast;
import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/12/2017.
 */
public class CreateReport extends Fragment  implements
        View.OnClickListener {

    public static final int DATE_DIALOG_ID = 0;
    private static Spinner spinner;
    private static Button btnPublish;
    private static TextView btnCancel;
    private static EditText edNmBrg, edKet,edJumlah, edLok, edTgl, edUrl;
    private String lastIdBarang="";

    Calendar c = Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.createnewreport, container, false);

        spinner = (Spinner) v.findViewById(R.id.spinnerChoice);
        btnPublish = (Button) v.findViewById(R.id.publish);
        btnCancel = (TextView) v.findViewById(R.id.tvCancel);

        edNmBrg = (EditText) v.findViewById(R.id.edNamaBarang);
        edKet = (EditText) v.findViewById(R.id.edKeterangan);
        edJumlah = (EditText) v.findViewById(R.id.edJumlah);
        edLok = (EditText) v.findViewById(R.id.edLokasiHilang);
        edUrl = (EditText) v.findViewById(R.id.edUrlImage);
        edTgl = (EditText) v.findViewById(R.id.dtTanggalHilang);

        List<String> myArraySpinner = new ArrayList<String>();

        myArraySpinner.add("Elektronik");
        myArraySpinner.add("Keperluan Pribadi");
        myArraySpinner.add("Uang");
        myArraySpinner.add("Surat");
        myArraySpinner.add("Kendaraan");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, myArraySpinner);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down vieww

        spinner.setAdapter(spinnerArrayAdapter);

        edTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),
                        mDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        setListeners();

        return v;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
          c.set(Calendar.YEAR, year);
          c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
         //   edTgl.setText(date_selected);

            myFormat = "MM/dd/yyyy";
            sdf = new SimpleDateFormat(myFormat, Locale.US);
            edTgl.setText(sdf.format(c.getTime()));
        }
    };

    private void setListeners() {
        btnPublish.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void Publish(){
        registerBarang();
        getLastIdBarang();
        registerPelaporan();
        Toast.makeText(getActivity(), "Report published!", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
        startActivity(intent);
    }

    public void Cancel(){
        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish:
                Publish();
                break;
            case R.id.tvCancel:
                Cancel();
                break;
        }
    }

    private void registerBarang(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://barang-hilang.azurewebsites.net/api/v1/barang/";

        Map<String,String> params = new HashMap<String, String>();
        params.put("idKategoriBarang",String.valueOf(spinner.getSelectedItemPosition()+1));
        params.put("idUserPemilik",MainMenuActivity.userModel.getIdUser());
        params.put("nama",edNmBrg.getText().toString());
        params.put("status","Lost");
        params.put("url_image","");
        params.put("jumlahBarang",edJumlah.getText().toString());


        JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        try {
                            //Process os success response
//                            JSONArray result = response.getJSONArray("result");

                            Toast.makeText(getActivity(), "Register Pelaporan Success!", Toast.LENGTH_SHORT)
                                    .show();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                            new CustomToast().Show_Toast(getActivity(), getView(),
//                                    "Sorry, please insert correctly, "+e.getMessage());
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

                new CustomToast().Show_Toast(getActivity(), getView(),
                        error.toString());
                Log.e("Error : ",error.toString()+" "+error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("apiKey","wakowakowakowa");
                header.put("Content-Type", "application/json");
                return header ;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        request_json.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Toast.makeText(getActivity().getApplicationContext(),"Please wait a while",Toast.LENGTH_LONG).show();
        queue.add(request_json);
    }

    public void getLastIdBarang(){
        RequestQueue requestQueueActive = Volley.newRequestQueue(getActivity());
        StringRequest endpointActive = new StringRequest(Request.Method.GET, "http://barang-hilang.azurewebsites.net/api/v1/barang",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONArray results = result.getJSONArray("result");
                            for (int i = 0; i < results.length(); i++) {
                                if(results.getJSONObject(i).getJSONObject("user").getString("email")
                                        .equalsIgnoreCase(MainMenuActivity.userModel.getEmail())){
                                    lastIdBarang=results.getJSONObject(i).getString("idBarang");
                                }
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
        Toast.makeText(getActivity().getApplicationContext(),"Please wait a while",Toast.LENGTH_LONG).show();
        requestQueueActive.add(endpointActive);

    }

    private void registerPelaporan(){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://barang-hilang.azurewebsites.net/api/v1/pelaporan/";

        Map<String,String> params = new HashMap<String, String>();
        params.put("idBarang",lastIdBarang);
        params.put("idPelapor",MainMenuActivity.userModel.getIdUser());
        params.put("tempatHilang",edLok.getText().toString());
//        params.put("tanggalHilang",edTgl.getText().toString().);
        params.put("tanggalHilang",new Date().toString());
        params.put("keterangan",edKet.getText().toString());


        JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                            Toast.makeText(getActivity(), "Register Pelaporan Success!", Toast.LENGTH_SHORT)
                                    .show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                new CustomToast().Show_Toast(getActivity(), getView(),
                        error.toString());
                Log.e("Error : ",error.toString()+" "+error.getMessage());

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("apiKey","wakowakowakowa");
                header.put("Content-Type", "application/json");
                return header ;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        request_json.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Toast.makeText(getActivity().getApplicationContext(),"Registering .....",Toast.LENGTH_LONG).show();
        queue.add(request_json);
    }
}
