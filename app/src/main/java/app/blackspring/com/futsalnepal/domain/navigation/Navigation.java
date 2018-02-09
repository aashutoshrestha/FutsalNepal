package app.blackspring.com.futsalnepal.domain.navigation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import app.blackspring.com.futsalnepal.data.Repository;
import app.blackspring.com.futsalnepal.data.RepositoryData;
import app.blackspring.com.futsalnepal.data.cloud.Network;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public class Navigation implements NavigationUseCase{

    Repository repository;

    public Navigation() {
        repository = new RepositoryData();
    }

    @Override
    public Observable<String> getNavigationPath(LatLng origin, LatLng dest){
        return repository.getNavigationPath(origin, dest);
    }



}
