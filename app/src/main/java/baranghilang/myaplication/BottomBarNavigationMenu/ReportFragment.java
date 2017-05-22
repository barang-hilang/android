package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.util.Map;

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

    public void getActiveOrderByEmail(){
        RequestQueue requestQueueActive = Volley.newRequestQueue(this.getContext());
        StringRequest endpointActive = new StringRequest(Request.Method.GET, "http://c-laundry.hol.es/api2/getOrders.php?email="+OrderHistory.emailUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            resultResponseActive.clear();
                            JSONObject result = new JSONObject(response);
                            JSONArray results = result.getJSONArray("result");
                            for (int i = 0; i < results.length(); i++) {
                                OrderLaundry orderLaundry = new OrderLaundry();
                                orderLaundry.setId_order(results.getJSONObject(i).getString("id_order"));
                                orderLaundry.setEmail(results.getJSONObject(i).getString("email"));
                                orderLaundry.setAddress(results.getJSONObject(i).getString("address"));
                                orderLaundry.setWeight(results.getJSONObject(i).getString("weight"));
                                orderLaundry.setPrice(results.getJSONObject(i).getString("price"));
                                orderLaundry.setDate_order(results.getJSONObject(i).getString("date_order"));
                                orderLaundry.setDate_end(results.getJSONObject(i).getString("date_end"));
                                orderLaundry.setStatus(results.getJSONObject(i).getString("status"));
                                resultResponseActive.add(orderLaundry);
                                for (OrderLaundry orderLaundry1: resultResponseActive) {
                                    Log.e("Data : ",orderLaundry1.toString());
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
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
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
        progressDialog = new ProgressDialog(this.getContext(),R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();

        requestQueueActive.add(endpointActive);

    }

}
