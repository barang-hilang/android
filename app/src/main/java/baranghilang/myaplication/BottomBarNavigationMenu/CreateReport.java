package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/12/2017.
 */
public class CreateReport extends Fragment  implements
        View.OnClickListener {

    private static Button btnPublish;
    private static TextView btnCancel;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.createnewreport, container, false);

        btnPublish = (Button) v.findViewById(R.id.publish);
        btnCancel = (TextView) v.findViewById(R.id.tvCancel);
        setListeners();

        return v;



    }


    private void setListeners() {
        btnPublish.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void Publish(){
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
}
