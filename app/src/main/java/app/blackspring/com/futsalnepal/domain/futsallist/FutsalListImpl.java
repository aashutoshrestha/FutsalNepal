package app.blackspring.com.futsalnepal.domain.futsallist;

import com.google.gson.Gson;

import app.blackspring.com.futsalnepal.data.Repository;
import app.blackspring.com.futsalnepal.data.RepositoryImpl;
import app.blackspring.com.futsalnepal.data.cloud.ApiConfig;
import app.blackspring.com.futsalnepal.model.futsal.FutsalData;
import app.blackspring.com.futsalnepal.model.login.LoginData;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by utsavstha on 2/16/18.
 */

public class FutsalListImpl implements FutsalListUseCase {
    private Repository repository;
    private Gson gson;
    public FutsalListImpl() {
        repository = new RepositoryImpl();
        gson = new Gson();
    }

    @Override
    public void getFutsalList(String latitude, String longitude, OnFutsalListFetched callBack) {
        repository.getFutsalList(latitude, longitude).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                callBack.onError(null);
            }

            @Override
            public void onNext(String s) {
                FutsalData data = gson.fromJson(s, FutsalData.class);
                if (data.isSuccess()) {
                    repository.saveFutsalList(ApiConfig.GET_FUTSAL_LIST,
                            s);
                    callBack.onSuccess(data);
                }
            }
        });
    }

}
