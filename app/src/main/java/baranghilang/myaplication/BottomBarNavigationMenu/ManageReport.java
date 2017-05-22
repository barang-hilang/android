package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/22/2017.
 */
public class ManageReport extends AppCompatActivity {

    public static final int DATE_DIALOG_ID = 0;

    private static Button btnEdit;
    private static TextView btnBack;
    private static EditText edNmBrg, edKet, edJumlah, edLok, edTgl, edUrl;
    Calendar c = Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managereport);

        btnEdit = (Button) findViewById(R.id.edit);
        btnBack = (TextView) findViewById(R.id.tvCancel);

        edNmBrg = (EditText) findViewById(R.id.edNamaBarang);
        edKet = (EditText) findViewById(R.id.edKeterangan);
        edJumlah = (EditText) findViewById(R.id.edJumlah);
        edLok = (EditText) findViewById(R.id.edLokasiHilang);
        edUrl = (EditText) findViewById(R.id.edUrlImage);
        edTgl = (EditText) findViewById(R.id.dtTanggalHilang);

        edTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getApplication(),
                        mDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        edNmBrg.setEnabled(false);
        edKet.setEnabled(false);
        edJumlah.setEnabled(false);
        edLok.setEnabled(false);
        edTgl.setEnabled(false);
        edUrl.setEnabled(false);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnEdit.getText()=="UBAH")
                {
                    Edit();
                }
                else if(btnEdit.getText()=="SIMPAN")
                {
                    Save();
                }

            }
        });

      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(btnBack.getText()=="KEMBALI")
              {
                  Back();
              }
              else if(btnBack.getText()=="BATAL")
              {
                  Cancel();
              }
          }
      });

    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);


            myFormat = "MM/dd/yyyy";
            sdf = new SimpleDateFormat(myFormat, Locale.US);
            edTgl.setText(sdf.format(c.getTime()));
        }
    };



    public void Save(){
        edNmBrg.setEnabled(false);
        edKet.setEnabled(false);
        edLok.setEnabled(false);
        edTgl.setEnabled(false);
        edUrl.setEnabled(false);
        edJumlah.setEnabled(false);
    }

    public void Edit(){
        edNmBrg.setEnabled(true);
        edKet.setEnabled(true);
        edJumlah.setEnabled(true);
        edLok.setEnabled(true);
        edTgl.setEnabled(true);
        edUrl.setEnabled(true);
    }

    public void Back(){
        Intent intent = new Intent(this, ManageReport.class);
        startActivity(intent);
    }

    public void Cancel(){
        edNmBrg.setText("");
        edKet.setText("");
        edLok.setText("");
        edTgl.setText("");
        edUrl.setText("");

        edNmBrg.setEnabled(false);
        edKet.setEnabled(false);
        edLok.setEnabled(false);
        edTgl.setEnabled(false);
        edUrl.setEnabled(false);
        edJumlah.setEnabled(false);
    }


}
