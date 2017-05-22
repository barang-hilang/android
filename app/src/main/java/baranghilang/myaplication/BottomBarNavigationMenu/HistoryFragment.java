package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/20/2017.
 */
public class HistoryFragment extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_rcycl_timeline, container, false);
        return v;
    }
}
