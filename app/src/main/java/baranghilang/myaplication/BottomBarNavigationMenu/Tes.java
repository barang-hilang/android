package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/13/2017.
 */
public class Tes extends AppCompatActivity  {

    private static final int DIALOG1 = 1;
    private static final int DIALOG2 = 2;

    private Button btnFound;
    private TextView btnBack;
    ProgressDialog progDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_report);

        btnFound = (Button) findViewById(R.id.btnTemukan);
        btnBack = (TextView) findViewById(R.id.tvBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tes.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

    }

    private Dialog createSimpleDialog1(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("We will notify the owner that you found the lost item.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                progDialog= ProgressDialog.show(Tes.this,"","Loading... Please wait..", true);
                new Thread(){
                    public void run()
                    {
                        try{
                            this.sleep(1500);
                        }catch(Exception e){}
                        progDialog.dismiss();
                    }
                }.start();

                Toast.makeText(Tes.this, "Notification sent to owner!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ;
            }
        });
        return builder.create();
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG1:
                return createSimpleDialog1(Tes.this);

        }
        return null;
    }

    public void eventBtnFood1(View v)
    {
        showDialog(DIALOG1);
    }



}
