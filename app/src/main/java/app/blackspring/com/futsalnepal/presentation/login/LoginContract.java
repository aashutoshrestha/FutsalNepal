package app.blackspring.com.futsalnepal.presentation.login;

import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import app.blackspring.com.futsalnepal.model.login.LoginData;

/**
 * Created by utsavstha on 2/15/18.
 */

public interface LoginContract {
    interface View {
        void onLoggedIn(String email, String name);
        void onFailure(Exception e);
        void sessionExists(String user_id);
    }

    interface Model {
        LoginData loginUser(String name, String email, int phoneNumber, String deviceToken);
        void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask);
        void handleFacebookSignIn(LoginResult loginResult);
        String checkSession();
    }

    interface Presenter {
        LoginData loginUser(String name, String email, int phoneNumber, String deviceToken);
        void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask);
        void handleFacebookSignIn(LoginResult loginResult);
        void onLoginSuccess(String email, String name);
        void onLoginFailed(Exception e);
        void checkSession();
    }
}
