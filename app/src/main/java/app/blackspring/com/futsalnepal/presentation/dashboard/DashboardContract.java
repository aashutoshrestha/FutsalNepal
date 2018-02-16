package app.blackspring.com.futsalnepal.presentation.dashboard;

import app.blackspring.com.futsalnepal.model.futsal.FutsalData;

/**
 * Created by utsavstha on 2/16/18.
 */

public interface DashboardContract {
    interface View {
        void getFutsalList();
        void showProgress();
        void onSuccess(FutsalData futsalData);
        void hideProgress();
        void onError();

        void getDeviceCoordinates();
        void getLocationPermission();
    }

    interface Presenter {
        void getFutsalList(String latitude, String longitude);
        void onPermissionGrandted();
        void onDataFetched(FutsalData futsalData);
    }

    interface Model {
        void getFutsalList(String latitude, String longitude);
    }
}
