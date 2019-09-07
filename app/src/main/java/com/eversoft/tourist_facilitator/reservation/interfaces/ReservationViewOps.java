package com.eversoft.tourist_facilitator.reservation.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationData;

import java.util.ArrayList;



public interface ReservationViewOps {
    void showProgressBar();

    void hideListView();

    void showDataNotAvailable();

    void initListView(ArrayList<ReservationData> reservationDataArrayList);

    void showErrorDialogAndQuit(String s);

    void makeToast(String dzialam);

    void stopProgressBar();

    void showFilterActivity();

    void showRateDialog(ReservationData item);
}
