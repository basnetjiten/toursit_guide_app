package com.eversoft.tourist_facilitator.hotelList.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.HotelOffer;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.HotelData;



public interface HotelListPresenterOps {
    void onStartup();

    void listItemSelect(HotelData item);

    void getSearchRequestResult(HotelOffer hotelOffer);

    void getSearchRequestFailure(String s);

    void onResume();

    void showReservationRequested();
}
