package app.blackspring.com.futsalnepal.presentation.navigation;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public class NavigationPresenter implements NavigationContract.Presenter {
    private NavigationContract.Model model;

    public NavigationPresenter() {
        model = new NavigationModel();
    }

    @Override
    public Observable<PolylineOptions> getNavigationPath(LatLng origin, LatLng dest) {
        return model.getNavigationPath(origin, dest);
    }
}
