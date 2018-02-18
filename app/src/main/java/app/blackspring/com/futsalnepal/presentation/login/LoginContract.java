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
        void onEmailFetched(String email, String name);
        void onEmailError(Exception e);
        void sessionExists(String user_id);
        void onLoggedIn(LoginData data);
        void onLoginError();
    }

    interface Model {
        void loginUser(String name, String email, int phoneNumber, String deviceToken);
        void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask);
        void handleFacebookSignIn(LoginResult loginResult);
        String checkSession();
    }

    interface Presenter {
        void loginUser(String name, String email, int phoneNumber, String deviceToken);
        void handleGoogleSignIn(Task<GoogleSignInAccount> completedTask);
        void handleFacebookSignIn(LoginResult loginResult);
        void onEmailFetched(String email, String name);
        void onEmailError(Exception e);
        void onLoggedIn(LoginData data);
        void onLoginError();
        void checkSession();

    }
}
