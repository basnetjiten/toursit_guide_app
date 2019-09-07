package com.eversoft.tourist_facilitator.hotelList.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.SearchRequest;



public interface HotelListModelOps {
    void searchRequest(SearchRequest selectedSearchRequest, LoginData loginData);
}
