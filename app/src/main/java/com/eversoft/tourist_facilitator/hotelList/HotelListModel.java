package com.eversoft.tourist_facilitator.hotelList;

import com.eversoft.tourist_facilitator.data.NetworkServiceImpl;
import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.HotelOffer;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.SearchRequest;
import com.eversoft.tourist_facilitator.filter.interfaces.SearchRequestResult;
import com.eversoft.tourist_facilitator.hotelList.interfaces.HotelListModelOps;
import com.eversoft.tourist_facilitator.hotelList.interfaces.HotelListPresenterOps;



class HotelListModel implements HotelListModelOps {
    private final HotelListPresenterOps hotelListPresenter;

    //todo remove:, replace with callback
    private final NetworkServiceImpl networkService;

    public HotelListModel(HotelListPresenter hotelListPresenter, NetworkServiceImpl networkService) {
        this.hotelListPresenter = hotelListPresenter;
        this.networkService = networkService;
    }

    @Override
    public void searchRequest(SearchRequest searchRequest, LoginData loginData) {
        networkService.searchRequest(searchRequest, loginData, new SearchRequestResult() {

            @Override
            public void getSearchRequestResult(HotelOffer hotelOffer) {
                hotelListPresenter.getSearchRequestResult(hotelOffer);
            }

            @Override
            public void searchRequestFailure(String s) {
                hotelListPresenter.getSearchRequestFailure(s);
            }
        });
    }
}
