package app.blackspring.com.futsalnepal.presentation.login;

import android.content.Context;
import android.os.Bundle;

import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;

import app.blackspring.com.futsalnepal.domain.login.LoginImpl;
import app.blackspring.com.futsalnepal.domain.login.LoginUseCase;
import app.blackspring.com.futsalnepal.model.login.LoginData;

/**
 * Created by utsavstha on 2/15/18.
 */

public class LoginModel implements LoginContract.Model {

    private LoginContract.Presenter presenter;
    private LoginUseCase loginUseCase;

    public LoginModel(LoginContract.Presenter loginPresenter, Context context) {
        presenter = loginPresenter;
        loginUseCase = new LoginImpl(context);
    }

    @Override
    public LoginData loginUser(String name, String email, int phoneNumber, String deviceToken) {
        return null;
    }

    @Override
    public void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            presenter.onLoginSuccess(account.getEmail(), account.getDisplayName());
        } catch (ApiException e) {
            presenter.onLoginFailed(e);
        }
    }

    @Override
    public void handleFacebookSignIn(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                (object, response) -> {

                    try {
                        String name = object.getString("name");

                        String email = object.getString("email");

                        presenter.onLoginSuccess(email, name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        presenter.onLoginFailed(e);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public String checkSession() {
        return loginUseCase.checkSession();
    }
}
