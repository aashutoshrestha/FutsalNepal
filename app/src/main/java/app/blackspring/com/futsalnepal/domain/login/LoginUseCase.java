package app.blackspring.com.futsalnepal.domain.login;

import app.blackspring.com.futsalnepal.model.login.LoginData;

/**
 * Created by utsavstha on 2/15/18.
 */

public interface LoginUseCase {
    void loginUser(String name, String email, int phoneNumber, String deviceToken, LoginUseCase.CallBack callBack);
    String checkSession();
    interface CallBack{
        void onLoggedIn(LoginData data);

        void onFailure(String data);
    }

}
