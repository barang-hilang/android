package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import baranghilang.myaplication.R;
import baranghilang.myaplication.model.BarangModel;
import baranghilang.myaplication.model.PelaporanModel;

/**
 * Created by CMDDJ on 5/13/2017.
 */
public class DetailReport extends AppCompatActivity  {
    public static PelaporanModel pelaporanModel;
    public static BarangModel barangModel;

    private static final int DIALOG1 = 1;
    private static final int DIALOG2 = 2;
    private static View view;
    private Button btnFound;
    private TextView btnBack;
    ProgressDialog progDialog;
    private EditText edLok, edTgl;
    private RelativeLayout detailLayout;
    private TextView nama,detail,lokasi,tanggal,jam,namaPelapor;
    private static Animation shakeAnimation;
    Calendar c = Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_report);

        nama = (TextView) findViewById(R.id.tvNamabrg);
        detail = (TextView) findViewById(R.id.tvDeskripsi);
        lokasi = (TextView) findViewById(R.id.tvLokasiHilang);
        tanggal = (TextView) findViewById(R.id.tvTanggalHilang);
        jam = (TextView) findViewById(R.id.tvJamHilang);
        namaPelapor = (TextView) findViewById(R.id.tvNamaPelapor);

        nama.setText(barangModel.getNamaBarang());
        detail.setText(pelaporanModel.getKeterangan());
        lokasi.setText(pelaporanModel.getLokHilang());
        tanggal.setText(pelaporanModel.getTglHilang());
        jam.setText(pelaporanModel.getTglHilang());
        namaPelapor.setText(MainMenuActivity.userModel.getUsername());

        detailLayout = (RelativeLayout) findViewById(R.id.detail_layout);
        btnFound = (Button) findViewById(R.id.btnTemukan);
        btnBack = (TextView) findViewById(R.id.tvBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailReport.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
        edLok = (EditText) findViewById(R.id.edLokasiDitemukan);
        edTgl = (EditText) findViewById(R.id.dtTanggalDitemukan);
        edTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(DetailReport.this, mDateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(this,
                R.anim.shake);


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


    private Dialog createSimpleDialog1(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Kami akan memberitahukan kepada pemilik barang bahwa Anda menemukan barang mereka.");
        builder.setPositiveButton("Lanjutkan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                progDialog= ProgressDialog.show(DetailReport.this,"","Mohon tunggu...", true);
                new Thread(){
                    public void run()
                    {
                        try{
                            this.sleep(1500);
                        }catch(Exception e){}
                        progDialog.dismiss();
                    }
                }.start();

                Toast.makeText(DetailReport.this, "Pemberitahuan terkirim!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
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
                if (edLok.equals("") || edLok.length() ==0 || edTgl.equals("") || edTgl.length() == 0) {
                    Toast.makeText(DetailReport.this, "Informasi penemuan barang harus diisi!", Toast.LENGTH_SHORT).show();
                }
                return createSimpleDialog1(DetailReport.this);

        }
        return null;
    }

    public void eventBtnFood1(View v)
    {
        showDialog(DIALOG1);
    }



}
