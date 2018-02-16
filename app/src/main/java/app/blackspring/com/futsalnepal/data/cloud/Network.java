package app.blackspring.com.futsalnepal.data.cloud;

import com.google.android.gms.maps.model.LatLng;

import rx.Observable;

/**
 * Created by utsavstha on 2/15/18.
 */

public interface Network {
    Observable<String> getNavigationPath(LatLng origin, LatLng dest);
    Observable<String> LoginUser(String name, String email, int phoneNumber, String deviceToken);
    Observable<String> getFutsalList(String latitute, String longitude);

}
