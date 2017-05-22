package baranghilang.myaplication.BottomBarNavigationMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import baranghilang.myaplication.MainActivity;
import baranghilang.myaplication.R;
import baranghilang.myaplication.model.UserModel;


public class MainMenuActivity extends ActionBarActivity {
    public static UserModel userModel;
    BottomBar mBottomBar;
    public FloatingActionButton floatingActionButton;
    private int status=0;

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
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fbRefresh);

        mBottomBar =  BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int i) {
                if(i==R.id.timeline)
                {
                    final TimelineFragment f = new TimelineFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    status=1;
                    floatingActionButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            f.getPelaporan();
                            f.setAdapter();
                        }
                    });
                }
                else if(i==R.id.Manage)
                {
                    final ReportFragment f = new ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    status=2;
                    floatingActionButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            f.getPelaporanByPelapor();
                            f.setAdapter();
                        }
                    });
                }
                else if(i==R.id.newreport)
                {
                    CreateReport f = new CreateReport();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    status=3;
                }
                else if(i==R.id.history)
                {
                    HistoryFragment f = new HistoryFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    status=4;
                }
                else if(i==R.id.Profile)
                {
                    ProfileFragment f = new ProfileFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
                    status=5;
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
