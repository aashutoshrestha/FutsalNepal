package app.blackspring.com.futsalnepal.presentation.login;

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

    public LoginPresenter(LoginContract.View view) {
        model = new LoginModel(this);
        this.view = view;
    }

    @Override
    public LoginData loginUser(String name, String email, int phoneNumber, String deviceToken) {
        return null;
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
    public void onLoginSuccess(String email, String name) {
        view.onLoggedIn(email, name);
    }

    @Override
    public void onLoginFailed(Exception e) {
        view.onFailure(e);
    }

    @Override
    public void checkSession() {
        String user_id = model.checkSession();
        if(user_id != null){
            view.sessionExists(user_id);
        }
    }
}
