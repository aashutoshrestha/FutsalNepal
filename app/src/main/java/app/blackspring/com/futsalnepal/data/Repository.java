package app.blackspring.com.futsalnepal.data;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;


import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public interface Repository {
    Observable<String> getNavigationPath(LatLng origin, LatLng dest);
}
