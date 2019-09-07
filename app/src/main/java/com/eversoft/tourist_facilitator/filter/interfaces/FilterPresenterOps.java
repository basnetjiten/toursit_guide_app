package com.eversoft.tourist_facilitator.filter.interfaces;


import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;

import java.util.Date;
import java.util.List;


public interface FilterPresenterOps {
    void onStartup();

    void getCityListResult(List<City> list);

    void getCityListResultFailed(String s);

    void arrivalDateClick();

    void departureDateClick();

    void arrivalDateChange(Date date);

    void departureDateChange(Date date);

    void search(City city, int value);
}
