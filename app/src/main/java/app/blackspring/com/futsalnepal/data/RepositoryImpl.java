package app.blackspring.com.futsalnepal.data;

import com.google.android.gms.maps.model.LatLng;

import app.blackspring.com.futsalnepal.data.cloud.ApiConfig;
import app.blackspring.com.futsalnepal.data.cloud.Network;
import app.blackspring.com.futsalnepal.data.cloud.NetworkImpl;
import app.blackspring.com.futsalnepal.data.local.LocalStorage;
import app.blackspring.com.futsalnepal.data.local.LocalStorageImpl;
import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public class RepositoryImpl implements Repository {

    private Network network;
    private LocalStorage localStorage;

    public RepositoryImpl() {
        this.network = new NetworkImpl();
        this.localStorage = new LocalStorageImpl();

    }


    @Override
    public Observable<String> getNavigationPath(LatLng origin, LatLng dest) {
        return network.getNavigationPath(origin, dest);
    }


    @Override
    public Observable<String> loginUser(String name, String email, int phoneNumber, String deviceToken) {
        if (localStorage.getData(name) != null) {
            return Observable.just(localStorage.getData(name));
        } else {
            return network.LoginUser(name, email, phoneNumber, deviceToken);
        }
    }


    @Override
    public void saveUserId(String userName, String userId) {
        localStorage.saveData(userName, userId);
    }

    @Override
    public String getUserId() {
        if (localStorage.getData("user_id") != null)
            return localStorage.getData("user_id");
        else return null;
    }

    @Override
    public Observable<String> getFutsalList(String latitude, String longitude) {
        if (localStorage.getData(ApiConfig.GET_FUTSAL_LIST) != null) {
            return Observable.just(localStorage.getData(ApiConfig.GET_FUTSAL_LIST));
        } else {
            return network.getFutsalList(latitude, longitude);
        }
    }

    @Override
    public void saveFutsalList(String futsalUrl, String data) {
        localStorage.saveData(futsalUrl, data);
    }
}
