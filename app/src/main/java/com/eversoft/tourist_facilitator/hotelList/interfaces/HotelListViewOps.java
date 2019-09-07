package com.eversoft.tourist_facilitator.hotelList.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.hotel.HotelData;

import java.util.ArrayList;
import java.util.List;



public interface HotelListViewOps {
    abstract void initHotelListView(ArrayList<HotelData> hotelData);

    void showHotelDetailView();

    void showProgressBar();

    void stopProgressBar();

    void showAlertDialogAndFinish(String s);

    void updateListView(List<HotelData> hotelOffer);

    void showReservationActivity();
}
