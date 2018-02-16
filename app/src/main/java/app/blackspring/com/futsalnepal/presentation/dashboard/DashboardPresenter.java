package app.blackspring.com.futsalnepal.presentation.dashboard;

import android.content.Context;

import app.blackspring.com.futsalnepal.model.futsal.FutsalData;

/**
 * Created by utsavstha on 2/16/18.
 */

public class DashboardPresenter implements DashboardContract.Presenter {
    private DashboardContract.View view;
    private DashboardContract.Model model;

    public DashboardPresenter(DashboardContract.View view, Context context) {
        this.view = view;
        model = new DashboardModel(this, context);
    }

    @Override
    public void getFutsalList(String latitude, String longitude) {
        view.showProgress();
        model.getFutsalList(latitude, longitude);
    }

    @Override
    public void onPermissionGrandted() {
        view.getDeviceCoordinates();
    }

    @Override
    public void onDataFetched(FutsalData futsalData) {
        view.hideProgress();
        if (futsalData != null) {
            view.onSuccess(futsalData);
        } else {
            view.onError();
        }
    }

}
