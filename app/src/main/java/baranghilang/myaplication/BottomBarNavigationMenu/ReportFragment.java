package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private ProgressDialog progressDialog = null;

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

    public void getMyPelaporan(){
        RequestQueue requestQueueActive = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest endpointActive = new StringRequest(Request.Method.GET, "url disini",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                                JSONObject data = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
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
        progressDialog = new ProgressDialog(getActivity().getApplicationContext(),R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();

        requestQueueActive.add(endpointActive);

    }

}
