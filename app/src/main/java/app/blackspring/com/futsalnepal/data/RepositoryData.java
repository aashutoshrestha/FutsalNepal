package app.blackspring.com.futsalnepal.data;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

import app.blackspring.com.futsalnepal.data.cloud.Network;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public class RepositoryData implements Repository{

    @Override
    public Observable<String> getNavigationPath(LatLng origin, LatLng dest){
        return Network.getNavigationPath(origin, dest);
    }
}
