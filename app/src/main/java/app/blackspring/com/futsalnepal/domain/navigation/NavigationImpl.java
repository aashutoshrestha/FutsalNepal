package app.blackspring.com.futsalnepal.domain.navigation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import app.blackspring.com.futsalnepal.data.Repository;
import app.blackspring.com.futsalnepal.data.RepositoryImpl;
import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public class NavigationImpl implements NavigationUseCase{

    Repository repository;

    public NavigationImpl(Context context) {
        repository = new RepositoryImpl(context);
    }

    @Override
    public Observable<String> getNavigationPath(LatLng origin, LatLng dest){
        return repository.getNavigationPath(origin, dest);
    }



}
