package app.blackspring.com.futsalnepal.presentation.dashboard;

import app.blackspring.com.futsalnepal.domain.futsallist.FutsalListImpl;
import app.blackspring.com.futsalnepal.domain.futsallist.FutsalListUseCase;
import app.blackspring.com.futsalnepal.model.futsal.FutsalData;

/**
 * Created by utsavstha on 2/16/18.
 */

public class DashboardModel implements DashboardContract.Model {
    private DashboardContract.Presenter presenter;
    private FutsalListUseCase futsalListUseCase;

    public DashboardModel(DashboardContract.Presenter presenter) {
        this.presenter = presenter;
        futsalListUseCase = new FutsalListImpl();
    }

    @Override
    public void getFutsalList(String latitude, String longitude) {
        futsalListUseCase.getFutsalList(latitude, longitude, new FutsalListUseCase.OnFutsalListFetched() {
            @Override
            public void onSuccess(FutsalData data) {
                presenter.onDataFetched(data);
            }

            @Override
            public void onError(Exception e) {
                presenter.onDataFetched(null);
            }
        });
    }
}
