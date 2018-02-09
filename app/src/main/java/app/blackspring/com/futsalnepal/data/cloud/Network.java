package app.blackspring.com.futsalnepal.data.cloud;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;


import java.io.IOException;

import app.blackspring.com.futsalnepal.data.Repository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public class Network {

    @Nullable
    public static Observable<String> getNavigationPath(LatLng origin, LatLng dest) {
        Observable<String> observable;

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        observable = Observable.create(subscriber -> {
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();

                subscriber.onNext(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        });


        return observable;
    }
}
