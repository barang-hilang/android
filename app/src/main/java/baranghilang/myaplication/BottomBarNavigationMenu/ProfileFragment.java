package baranghilang.myaplication.BottomBarNavigationMenu;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import baranghilang.myaplication.Login_Fragment;
import baranghilang.myaplication.MainActivity;
import baranghilang.myaplication.R;


/**
 * Created by CMDDJ on 5/8/2017.
 */
public class ProfileFragment extends Fragment  implements
        View.OnClickListener {
    private static Button btnEdit;
    private static TextView btnLogOut;
    private static EditText edName, edMail, edPass, edHp, edAddresses;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_profil, container, false);

        btnEdit = (Button) v.findViewById(R.id.edit);
        btnLogOut = (TextView) v.findViewById(R.id.tvLogOut);
        edName = (EditText) v.findViewById(R.id.edFullName);
        edMail = (EditText) v.findViewById(R.id.edEmail);
        edPass = (EditText) v.findViewById(R.id.edPassword);
        edHp = (EditText) v.findViewById(R.id.edPhone);
        edAddresses = (EditText) v.findViewById(R.id.edAddress);

        btnEdit.setText("UBAH");
        btnLogOut.setText("KELUAR");
        edName.setEnabled(false);
        edMail.setEnabled(false);
        edPass.setEnabled(false);
        edHp.setEnabled(false);
        edAddresses.setEnabled(false);

        setListeners();

        return v;
    }

    private void setListeners() {
        btnEdit.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    public void Edit(){

        btnEdit.setText("SIMPAN");
        btnLogOut.setText("BATAL");
        edName.setEnabled(true);
        edMail.setEnabled(true);
        edPass.setEnabled(true);
        edHp.setEnabled(true);
        edAddresses.setEnabled(true);


    }

    public void Save(){

        btnEdit.setText("UBAH");
        btnLogOut.setText("KELUAR");
        edName.setEnabled(false);
        edMail.setEnabled(false);
        edPass.setEnabled(false);
        edHp.setEnabled(false);
        edAddresses.setEnabled(false);


    }

    public void LogOut(){
        new LovelyStandardDialog(getActivity())
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

    public void Cancel(){
        btnEdit.setText("UBAH");
        btnLogOut.setText("KELUAR");
        edName.setEnabled(false);
        edMail.setEnabled(false);
        edPass.setEnabled(false);
        edHp.setEnabled(false);
        edAddresses.setEnabled(false);

        //balik ke tampil profil biasa
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                if(btnEdit.getText()=="UBAH") {
                    Edit();
                }
                else if(btnEdit.getText()=="SIMPAN")
                {
                    Save();
                }
                break;
            case R.id.tvLogOut:
                if(btnLogOut.getText()=="BATAL") {
                    Cancel();
                }
                else if(btnLogOut.getText()=="KELUAR")
                {
                    LogOut();
                }
                break;
        }
    }


}
