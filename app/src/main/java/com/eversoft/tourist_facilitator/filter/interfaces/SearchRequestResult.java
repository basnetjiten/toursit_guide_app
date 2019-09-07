package com.eversoft.tourist_facilitator.filter.interfaces;


import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.HotelOffer;



public interface SearchRequestResult {
    void getSearchRequestResult(HotelOffer hotelOffer);

    void searchRequestFailure(String s);
}
