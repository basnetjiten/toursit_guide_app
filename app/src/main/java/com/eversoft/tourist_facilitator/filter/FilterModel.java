package com.eversoft.tourist_facilitator.filter;


import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.interfaces.NetworkService;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterModelOps;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterPresenterOps;
import com.eversoft.tourist_facilitator.filter.interfaces.LoginServiceCityListResult;

import java.util.List;



class FilterModel implements FilterModelOps {

    private final NetworkService networkService;

    //todo remove:, replace with callback
    FilterPresenterOps filterPresenterOps;

    public FilterModel(FilterPresenterOps filterPresenterOps, NetworkService networkService) {
        this.networkService = networkService;
        this.filterPresenterOps = filterPresenterOps;
    }

    @Override
    public void getCityList(LoginData loginData) {
        networkService.getCityList(loginData, new LoginServiceCityListResult() {
            @Override
            public void getCityListResult(List<City> list) {
                filterPresenterOps.getCityListResult(list);
            }

            @Override
            public void failure(String s) {
                filterPresenterOps.getCityListResultFailed(s);
            }
        });
    }
}
