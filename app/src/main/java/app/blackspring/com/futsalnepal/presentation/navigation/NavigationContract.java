package app.blackspring.com.futsalnepal.presentation.navigation;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public interface NavigationContract {
    interface View {

    }

    interface Model {
        Observable<PolylineOptions> getNavigationPath(LatLng origin, LatLng dest);
    }

    interface Presenter {
        Observable<PolylineOptions> getNavigationPath(LatLng origin, LatLng dest);
    }
}
