package app.blackspring.com.futsalnepal.presentation.login;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.model.login.LoginData;
import app.blackspring.com.futsalnepal.presentation.dashboard.DashboardView;
import app.blackspring.com.futsalnepal.presentation.utils.Utils;

public class LoginView extends AppCompatActivity implements
        View.OnClickListener, LoginContract.View {

    private static final int RC_SIGN_IN = 9001;

    private GoogleSignInClient mGoogleSignInClient;
    private LoginPresenter presenter;
    private CallbackManager callbackManager;
    private View rootView;
    String deviceToken;
    String email, name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        deviceToken = FirebaseInstanceId.getInstance().getToken();


        rootView = getWindow().getDecorView().getRootView();


        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        presenter = new LoginPresenter(this, this);

        presenter.checkSession();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        facebookLogin();

    }

    private void facebookLogin() {
        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                presenter.handleFacebookSignIn(loginResult);
            }

            @Override
            public void onCancel() {
                Utils.showSnackBar(rootView, "Please Complete the registration");
            }

            @Override
            public void onError(FacebookException exception) {
                Utils.showSnackBar(rootView, exception.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            presenter.handleGoogleSignIn(task);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;

        }
    }

    //Todo: Ask and verify phone number
    @Override
    public void onEmailFetched(String email, String name) {
        this.email = email;
        this.name = name;
        showPhoneNumberDialog();
    }

    @Override
    public void onEmailError(Exception e) {
        Utils.showSnackBar(rootView, e.getMessage());
    }

    @Override
    public void sessionExists(String user_id) {
        startActivity(new Intent(this, DashboardView.class));
    }

    @Override
    public void onLoggedIn(LoginData data) {
    }

    @Override
    public void onLoginError() {

    }

    private void showPhoneNumberDialog() {
        getSmsPermission();
        Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_DayNight_NoActionBar);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_phone_number);
        TextInputLayout phoneNumberLayout = dialog.findViewById(R.id.phoneNumberLayout);
        EditText phoneNumber = dialog.findViewById(R.id.phoneNumber);

        Button verfiy = dialog.findViewById(R.id.btn_verify);

        verfiy.setOnClickListener(view -> {
           if(phoneNumber.getText().toString().length() < 10) {
               phoneNumberLayout.setError("Please Enter a valid phone number");
           } else {
               presenter.loginUser(name, email, Integer.parseInt(phoneNumber.getText().toString()), deviceToken);
           }
        });
        dialog.show();
    }

    public void getSmsPermission() {
        RxPermissions.getInstance(this)
                .request(Manifest.permission.SEND_SMS)
                .subscribe(granted -> {
                    if (granted) {

                    } else {
                        Utils.showSnackBar(rootView, "We need this permission to verify your phone number",
                                "Retry", this::getSmsPermission);
                    }
                });


    }
}