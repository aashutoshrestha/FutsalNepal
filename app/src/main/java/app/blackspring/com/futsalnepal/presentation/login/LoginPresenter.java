package app.blackspring.com.futsalnepal.presentation.login;

import android.content.Context;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import app.blackspring.com.futsalnepal.model.login.LoginData;

/**
 * Created by utsavstha on 2/15/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginModel model;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view, Context context) {
        model = new LoginModel(this, context);
        this.view = view;
    }

    @Override
    public void loginUser(String name, String email, int phoneNumber, String deviceToken) {
        model.loginUser(name, email, phoneNumber, deviceToken);
    }

    @Override
    public void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask) {
        model.handleGoogleSignIn(completedTask);
    }

    @Override
    public void handleFacebookSignIn(LoginResult loginResult) {
        model.handleFacebookSignIn(loginResult);
    }

    @Override
    public void onEmailFetched(String email, String name) {
        view.onEmailFetched(email, name);
    }

    @Override
    public void onEmailError(Exception e) {
        view.onEmailError(e);
    }

    @Override
    public void onLoggedIn(LoginData data) {
        if(data.isSuccess()){
            view.onLoggedIn(data);
        }
    }

    @Override
    public void onLoginError() {
            view.onLoginError();
    }

    @Override
    public void checkSession() {
        String user_id = model.checkSession();
        if(user_id != null){
            view.sessionExists(user_id);
        }
    }
}
