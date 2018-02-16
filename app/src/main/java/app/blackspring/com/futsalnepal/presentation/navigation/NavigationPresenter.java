package app.blackspring.com.futsalnepal.presentation.navigation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public class NavigationPresenter implements NavigationContract.Presenter {
    private NavigationContract.Model model;

    public NavigationPresenter(Context context) {
        model = new NavigationModel(context);
    }

    @Override
    public Observable<PolylineOptions> getNavigationPath(LatLng origin, LatLng dest) {
        return model.getNavigationPath(origin, dest);
    }
}
