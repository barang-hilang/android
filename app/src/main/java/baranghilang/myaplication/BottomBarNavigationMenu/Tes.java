package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/13/2017.
 */
public class Tes extends AppCompatActivity  {

    private Button btnFound;
    private TextView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_report);

        btnFound = (Button) findViewById(R.id.btnTemukan);
        btnBack = (TextView) findViewById(R.id.tvBack);

        setListeners();
    }

    private void setListeners() {

    }

    public void Found()
    {

    }

    public void Back()
    {
       // Intent intent = new Intent(getActivity(), MainMenuActivity.class);
        //startActivity(intent);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case DIALOG1:
//                return createSimpleDialog1(Tes.this);
//        }
        return null;
    }





}
