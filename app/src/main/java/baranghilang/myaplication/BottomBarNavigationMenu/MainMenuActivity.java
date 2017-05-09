package baranghilang.myaplication.BottomBarNavigationMenu;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBarActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import baranghilang.myaplication.R;


public class MainMenuActivity extends ActionBarActivity {

    BottomBar mBottomBar;

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
                else if(i==R.id.report)
                {
                    ReportFragment f = new ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if(i==R.id.history)
                {
                   FoundFragment f = new FoundFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }
                else if(i==R.id.MyInfo)
                {
                    MyInfoFragment f = new MyInfoFragment();
                    getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();

                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int i) {

            }
        });

        mBottomBar.mapColorForTab(0, "#009688");
        mBottomBar.mapColorForTab(1, "#F4511E");
        mBottomBar.mapColorForTab(2, "#9C27B0");
        mBottomBar.mapColorForTab(3, "#0288D1");

        /*BottomBarBadge unread;
        unread = mBottomBar.makeBadgeForTabAt(3, "#BDBDBD", 13);
        unread.show();*/
    }
}
