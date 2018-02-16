package app.blackspring.com.futsalnepal.presentation.navigation;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.blackspring.com.futsalnepal.R;
import app.blackspring.com.futsalnepal.domain.navigation.NavigationImpl;
import app.blackspring.com.futsalnepal.domain.navigation.NavigationUseCase;
import app.blackspring.com.futsalnepal.presentation.utils.DirectionsJsonParser;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public class NavigationModel implements NavigationContract.Model {
    private NavigationUseCase navigationUseCase;
    private PolylineOptions lineOptions = null;
    private PublishSubject<PolylineOptions> optionsPublishSubject;

    public NavigationModel(Context context) {
        navigationUseCase = new NavigationImpl(context);
        optionsPublishSubject = PublishSubject.create();
    }

    @Nullable
    public PublishSubject<PolylineOptions> getNavigationPath(LatLng origin, LatLng dest) {
        DirectionsJsonParser parser = new DirectionsJsonParser();
        navigationUseCase.getNavigationPath(origin, dest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        lineOptions = null;
                    }

                    @Override
                    public void onNext(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            List<List<HashMap<String, String>>> result = parser.parse(jsonObject);

                            ArrayList<LatLng> points;


                            // Traversing through all the routes
                            for (int i = 0; i < result.size(); i++) {
                                points = new ArrayList<>();
                                lineOptions = new PolylineOptions();

                                // Fetching i-th route
                                List<HashMap<String, String>> path = result.get(i);

                                // Fetching all the points in i-th route
                                for (int j = 0; j < path.size(); j++) {
                                    HashMap<String, String> point = path.get(j);

                                    double lat = Double.parseDouble(point.get("lat"));
                                    double lng = Double.parseDouble(point.get("lng"));
                                    LatLng position = new LatLng(lat, lng);

                                    points.add(position);
                                }

                                // Adding all the points in the route to LineOptions
                                lineOptions.addAll(points);
                                lineOptions.width(10);
                                lineOptions.color(R.color.colorPrimary);
                            }

                            optionsPublishSubject.onNext(lineOptions);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

        return optionsPublishSubject;

    }
}
