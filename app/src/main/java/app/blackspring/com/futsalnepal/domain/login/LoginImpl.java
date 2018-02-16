package app.blackspring.com.futsalnepal.domain.login;

import android.content.Context;

import com.google.gson.Gson;

import app.blackspring.com.futsalnepal.data.Repository;
import app.blackspring.com.futsalnepal.data.RepositoryImpl;
import app.blackspring.com.futsalnepal.model.login.LoginData;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by utsavstha on 2/15/18.
 */

public class LoginImpl implements LoginUseCase {
    private Repository repository;
    private Gson gson;
    public LoginImpl(Context context) {
        repository = new RepositoryImpl(context);
        gson = new Gson();
    }

    @Override
    public void loginUser(String name, String email, int phoneNumber, String deviceToken, LoginUseCase.CallBack callBack) {
        repository.loginUser(name, email, phoneNumber, deviceToken).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBack.onFailure(null);
            }

            @Override
            public void onNext(String s) {
                LoginData data = gson.fromJson(s, LoginData.class);
                if(data.isSuccess()){
                    repository.saveUserId(data.getUserDetails().get(0).getName(),
                            String.valueOf(data.getUserDetails().get(0).getId()));
                    callBack.onLoggedIn(data);
                }
            }
        });
    }

    @Override
    public String checkSession() {
        return repository.getUserId();
    }
}
