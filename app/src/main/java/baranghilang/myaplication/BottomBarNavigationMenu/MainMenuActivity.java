package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import baranghilang.myaplication.MainActivity;
import baranghilang.myaplication.R;
import baranghilang.myaplication.model.UserModel;


public class MainMenuActivity extends ActionBarActivity {
    public static UserModel userModel;
    BottomBar mBottomBar;


    @Override
    public void onBackPressed() {
        new LovelyStandardDialog(this)
                .setTopColorRes(R.color.white_greyish)
                .setButtonsColorRes(R.color.colorToolbar)
                .setTitle("Konfirmasi")
                .setMessage("Apa anda yakin ingin keluar ?")
                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(v.getContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menumain);
        mBottomBar =  BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int i) {
                if(i==R.id.timeline)
                {
                    TimelineFragment f = new TimelineFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                }
                else if(i==R.id.Manage)
                {
                    ReportFragment f = new ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if(i==R.id.newreport)
                {
                    CreateReport f = new CreateReport();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if(i==R.id.history)
                {

                   HistoryFragment f = new HistoryFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if(i==R.id.Profile)
                {
                    ProfileFragment f = new ProfileFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int i) {

            }
        });

        mBottomBar.mapColorForTab(0, "#009688");
        mBottomBar.mapColorForTab(1, "#AB47BC");
        mBottomBar.mapColorForTab(2, "#0288D1");
        mBottomBar.mapColorForTab(3, "#F4511E");
        mBottomBar.mapColorForTab(4, "#B71C1C");

        /*BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(3, "#BDBDBD", 13);
        unread.show();*/


    }


}
