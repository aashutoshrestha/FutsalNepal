package app.blackspring.com.futsalnepal.domain.navigation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public interface NavigationUseCase {
    Observable<String> getNavigationPath(LatLng origin, LatLng dest);
}
