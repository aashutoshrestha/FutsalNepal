package app.blackspring.com.futsalnepal.domain.futsallist;

import app.blackspring.com.futsalnepal.model.futsal.FutsalData;

/**
 * Created by utsavstha on 2/16/18.
 */

public interface FutsalListUseCase {
    void getFutsalList(String latitude, String longitude, FutsalListUseCase.OnFutsalListFetched callBack);

    interface OnFutsalListFetched {
        void onSuccess(FutsalData data);
        void onError(Exception e);
    }
}
