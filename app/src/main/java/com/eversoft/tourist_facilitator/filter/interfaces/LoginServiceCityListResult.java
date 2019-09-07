package com.eversoft.tourist_facilitator.filter.interfaces;


import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;

import java.util.List;


public interface LoginServiceCityListResult {
    void getCityListResult(List<City> list);

    void failure(String s);
}
