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

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import baranghilang.myaplication.Login_Fragment;
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



        setListeners();

        return v;
    }

    private void setListeners() {
        btnEdit.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    public void Edit(){
        if(btnEdit.getText()=="EDIT") {
            btnEdit.setText("SAVE");
            btnLogOut.setText("CANCEL");
            edName.setFocusable(true);
            edMail.setFocusable(true);
            edPass.setFocusable(true);
            edHp.setFocusable(true);
            edAddresses.setFocusable(true);
        }

    }

    public void Save(){
        if(btnEdit.getText()=="SAVE") {
            btnEdit.setText("EDIT");
            btnLogOut.setText("LOG OUT");
            edName.setFocusable(false);
            edMail.setFocusable(false);
            edPass.setFocusable(false);
            edHp.setFocusable(false);
            edAddresses.setFocusable(false);
        }

    }

    public void LogOut(){
        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
        startActivity(intent);
    }

    public void Cancel(){}

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                if(btnEdit.getText()=="EDIT") {
                    Edit();
                }
                else if(btnEdit.getText()=="SAVE")
                {
                    Save();
                }
                break;
            case R.id.tvLogOut:
                if(btnLogOut.getText()=="CANCEL") {
                    Cancel();
                }
                else if(btnLogOut.getText()=="LOG OUT")
                {
                    LogOut();
                }
                break;
        }
    }


}
