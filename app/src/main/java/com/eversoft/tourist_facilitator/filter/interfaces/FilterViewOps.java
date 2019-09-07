package com.eversoft.tourist_facilitator.filter.interfaces;


import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public interface FilterViewOps {
    void initSpinner(List<City> list);

    void showCityLoadProgressBar(boolean b);

    void makeToast(String s);

    Date getCurrentDate();

    void displayArrivalDate(String s);

    void displayDepartureDate(String s);

    void showCalendarDialog(Calendar current, Calendar minDate, String title);

    void showHotelListView();
}
