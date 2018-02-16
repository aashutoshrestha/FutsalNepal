package app.blackspring.com.futsalnepal.data;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;


import rx.Observable;

/**
 * Created by utsavstha on 2/6/18.
 */

public interface Repository {
    /**
     *
     * @param origin User current location
     * @param dest Futsal location
     * @return path data for navigation
     */
    Observable<String> getNavigationPath(LatLng origin, LatLng dest);
    /**
     *
     * @param name
     * @param email
     * @param phoneNumber
     * @param deviceToken
     * @return if user_id exists locally returns local id, else hits the cloud to register user and get user id
     */
    Observable<String> loginUser(String name, String email, int phoneNumber, String deviceToken);
    /**
     *
     * @param userName
     * @param userId
     *
     * Saves user_id locally, always use this method after calling login user
     */
    void saveUserId(String userName, String userId);

    /**
     * returns user_id if exists
     */

    String getUserId();

    /**
     *
     * @param latitute users current latitude
     * @param longitude users current longitude
     * @returns futsal list from local if there is any, else fetches it from cloud
     */
    Observable<String> getFutsalList(String latitute, String longitude);

    /**
     *
     * @param futsalUrl
     * @param data
     * Saves futsal list locally, always use this method after calling getFutsalList
     */
    void saveFutsalList(String futsalUrl, String data);
}
