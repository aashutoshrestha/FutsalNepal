package app.blackspring.com.futsalnepal.data.cloud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import app.blackspring.com.futsalnepal.data.Repository;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 2/6/18.
 */

public class NetworkImpl implements Network {
    @Override
    public Observable<String> getNavigationPath(LatLng origin, LatLng dest) {
        Observable<String> observable;

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        observable = getStringObservableGet(url);


        return observable;
    }


    @Override
    public Observable<String> LoginUser(String name, String email, int phoneNumber, String deviceToken) {
        Observable<String> observable;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("phone_number", phoneNumber);
            jsonObject.put("device_token", deviceToken);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        observable = getStringObservablePost(body, ApiConfig.LOGIN);


        return observable;
    }

    @Override
    public Observable<String> getFutsalList(String latitute, String longitude) {
        return getStringObservableGet(ApiConfig.GET_FUTSAL_LIST + "?origins=" + latitute + "," + longitude);
    }

    @NonNull
    private Observable<String> getStringObservablePost(RequestBody body, String url) {
        Observable<String> observable;
        observable = Observable.create(subscriber -> {
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
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

    @NonNull
    private Observable<String> getStringObservableGet(String url) {
        Observable<String> observable;
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
