package baranghilang.myaplication;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import baranghilang.myaplication.BottomBarNavigationMenu.MainMenuActivity;
import baranghilang.myaplication.helperVolley.CustomRequest;
import baranghilang.myaplication.model.UserModel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by CMDDJ on 5/4/2017.
 */



public class Login_Fragment extends Fragment implements
        OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
    private UserModel userModel ;
    private ProgressDialog progressDialog;

    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        userModel= new UserModel();
        progressDialog = null;
        return view;
    }

    // Initiate Views
    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
       // forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
        signUp = (TextView) view.findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) view
                .findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);



        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
//        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            /*case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;*/
            case R.id.createAccount:

                // Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new SignUp_Fragment(),
                                Utils.SignUp_Fragment).commit();
                break;
        }

    }

    // Check Validation before login
    private void checkValidation() {
        // Get emails id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check patter for emails id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if emails is valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
            // Else do login and do your stuff
        else
        {
//            loginToBackEnd(getEmailId,getPassword);
            loginAuthPostBodyJson(getEmailId,getPassword);
        }

    }

    private void loginToBackEnd(final String email, final String password){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://barang-hilang.azurewebsites.net/api/v1/users/auth";

        StringRequest requestResponse = new StringRequest(Request.Method.POST, url
        , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("result");
                    userModel.setEmail(result.getJSONObject(0).getString("email"));
                    userModel.setPassword(result.getJSONObject(0).getString("pasword"));
                    userModel.setUsername(result.getJSONObject(0).getString("username"));
                    userModel.setAlamat(result.getJSONObject(0).getString("alamat"));
                    userModel.setIdDev(result.getJSONObject(0).getString("idDev"));
                    userModel.setIdUser(result.getJSONObject(0).getString("idUser"));
                    userModel.setNoHp(result.getJSONObject(0).getString("noHp"));
                    Log.e("Email User Auth : ",userModel.getEmail());
                    Toast.makeText(getContext(),"Success Login",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Login success!", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                    startActivity(intent);

                } catch (JSONException e) {
                    Log.e("Error",e.toString());
                    e.printStackTrace();
                    progressDialog.dismiss();
                    new CustomToast().Show_Toast(getActivity(), view,
                        "Sorry, email/password seems wrong");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error",error.toString());
                progressDialog.dismiss();
                new CustomToast().Show_Toast(getActivity(), view,
                       error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",email);
                params.put("password",password);
                return params;
           }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("apiKey","wakowakowakowa");
                header.put("Content-Type", "application/json");
                return header ;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        requestResponse.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        progressDialog = new ProgressDialog(this.getContext(),R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Getting Data...");
        progressDialog.show();
        queue.add(requestResponse);

    }

    private void loginAuthPostBodyJson(String email,String password){
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://barang-hilang.azurewebsites.net/api/v1/users/auth";

        Map<String,String> params = new HashMap<String, String>();
        params.put("email",email);
        params.put("password",password);

        JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            JSONArray result = response.getJSONArray("result");
                            userModel.setEmail(result.getJSONObject(0).getString("email"));
                            userModel.setPassword(result.getJSONObject(0).getString("password"));
                            userModel.setUsername(result.getJSONObject(0).getString("username"));
                            userModel.setAlamat(result.getJSONObject(0).getString("alamat"));
//                            userModel.setIdDev(result.getJSONObject(0).getString("idDeveloper"));
                            userModel.setIdUser(result.getJSONObject(0).getString("idUser"));
                            userModel.setNoHp(result.getJSONObject(0).getString("noHp"));
                            Log.e("Email User Auth : ",userModel.getEmail());
                            Toast.makeText(getContext(),"Success Login",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Login success!", Toast.LENGTH_SHORT)
                                    .show();
                            MainMenuActivity.userModel=userModel;
                            Intent intent = new Intent(getActivity(), MainMenuActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Sorry, email/password seems wrong, "+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                progressDialog.dismiss();
                new CustomToast().Show_Toast(getActivity(), view,
                        error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String, String>();
                header.put("apiKey","wakowakowakowa1");
                header.put("Content-Type", "application/json");
                return header ;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        request_json.setRetryPolicy(new DefaultRetryPolicy( 50000, 5, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        progressDialog = new ProgressDialog(this.getContext(),R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Getting Data...");
        progressDialog.show();
        queue.add(request_json);
    }

}
