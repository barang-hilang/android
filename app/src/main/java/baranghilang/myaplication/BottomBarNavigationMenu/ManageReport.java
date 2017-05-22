package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/22/2017.
 */
public class ManageReport extends Fragment implements
        View.OnClickListener {

    public static final int DATE_DIALOG_ID = 0;

    private static Button btnEdit;
    private static TextView btnBack;
    private static EditText edNmBrg, edKet, edJumlah, edLok, edTgl, edUrl;
    Calendar c = Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.managereport, container, false);

        btnEdit = (Button) v.findViewById(R.id.edit);
        btnBack = (TextView) v.findViewById(R.id.tvCancel);

        edNmBrg = (EditText) v.findViewById(R.id.edNamaBarang);
        edKet = (EditText) v.findViewById(R.id.edKeterangan);
        edJumlah = (EditText) v.findViewById(R.id.edJumlah);
        edLok = (EditText) v.findViewById(R.id.edLokasiHilang);
        edUrl = (EditText) v.findViewById(R.id.edUrlImage);
        edTgl = (EditText) v.findViewById(R.id.dtTanggalHilang);
        edTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(),
                        mDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        edNmBrg.setEnabled(false);
        edKet.setEnabled(false);
        edJumlah.setEnabled(false);
        edLok.setEnabled(false);
        edTgl.setEnabled(false);
        edUrl.setEnabled(false);


        setListeners();

        return v;
    }


    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //   edTgl.setText(date_selected);

            myFormat = "MM/dd/yyyy";
            sdf = new SimpleDateFormat(myFormat, Locale.US);
            edTgl.setText(sdf.format(c.getTime()));
        }
    };

    private void setListeners() {
        btnEdit.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

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
        Intent intent = new Intent(getActivity(), ManageReport.class);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                if(btnEdit.getText()=="UBAH")
                {
                    Edit();
                }
                else if(btnEdit.getText()=="SIMPAN")
                {
                    Save();
                }

                break;
            case R.id.tvCancel:
                if(btnBack.getText()=="KEMBALI")
                {
                    Back();
                }
                else if(btnBack.getText()=="BATAL")
                {
                    Cancel();
                }
                break;
        }
    }
}
