package baranghilang.myaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import baranghilang.myaplication.BottomBarNavigationMenu.MainMenuActivity;

public class MainActivity extends AppCompatActivity {

        private static FragmentManager fragmentManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            fragmentManager = getSupportFragmentManager();

            // If savedinstnacestate is null then replace login fragment
            if (savedInstanceState == null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new Login_Fragment(),
                                Utils.Login_Fragment).commit();
            }

            /* On close icon click finish activity
            findViewById(R.id.close_activity).setOnClickListener(
                    new OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            finish();

                        }
                    });*/

        }

        // Replace Login Fragment with animation
        protected void replaceLoginFragment() {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    .replace(R.id.frameContainer, new Login_Fragment(),
                            Utils.Login_Fragment).commit();
        }

        @Override
        public void onBackPressed() {

            // Find the tag of signup and forgot password fragment
            Fragment SignUp_Fragment = fragmentManager
                    .findFragmentByTag(Utils.SignUp_Fragment);
            Fragment ForgotPassword_Fragment = fragmentManager
                    .findFragmentByTag(Utils.ForgotPassword_Fragment);

            // Check if both are null or not
            // If both are not null then replace login fragment else do backpressed
            // task

            if (SignUp_Fragment != null)
                replaceLoginFragment();
            else if (ForgotPassword_Fragment != null)
                replaceLoginFragment();
            else
                super.onBackPressed();
        }

    public void moveActivity(){
        Intent intent=new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
    }

