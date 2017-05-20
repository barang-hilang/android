package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import baranghilang.myaplication.R;

/**
 * Created by CMDDJ on 5/12/2017.
 */
public class CreateReport extends Fragment  implements
        View.OnClickListener {
    public static final int DATE_DIALOG_ID = 0;

    private static Button btnPublish;
    private static TextView btnCancel;
    private static EditText edNmBrg, edKet, edLok, edTgl, edUrl;
    Calendar c = Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.createnewreport, container, false);

        btnPublish = (Button) v.findViewById(R.id.publish);
        btnCancel = (TextView) v.findViewById(R.id.tvCancel);
        edNmBrg = (EditText) v.findViewById(R.id.edNamaBarang);
        edKet = (EditText) v.findViewById(R.id.edKeterangan);
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
