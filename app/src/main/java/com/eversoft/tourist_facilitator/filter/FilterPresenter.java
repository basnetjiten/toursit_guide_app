package com.eversoft.tourist_facilitator.filter;

import android.os.Handler;
import android.text.format.DateFormat;
import android.util.Log;

import com.eversoft.tourist_facilitator.data.NetworkServiceImpl;
import com.eversoft.tourist_facilitator.data.entity.DialogChoice;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.SearchRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterModelOps;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterPresenterOps;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterViewOps;
import com.eversoft.tourist_facilitator.storage.Storage;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



class FilterPresenter implements FilterPresenterOps {
    private final WeakReference<FilterViewOps> filterViewOps;
    private final FilterModelOps filterModelOps;
    private final Handler handler = new Handler();
    private final String DATA_FORMAT = "yyyy-MM-dd";
    private Calendar arrivalCalendar = null;
    private Calendar departureCalendar = null;

    public FilterPresenter(FilterViewOps filterViewOps) {
        this.filterViewOps = new WeakReference<>(filterViewOps);
        this.filterModelOps = new FilterModel(this, new NetworkServiceImpl());
    }

    private FilterViewOps getView() throws NullPointerException {
        if (filterViewOps != null)
            return filterViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void onStartup() {
        getView().showCityLoadProgressBar(true);


        Date arrivalDate = new Date();
        Date departureDate = new Date();

        arrivalCalendar = Calendar.getInstance();
        arrivalCalendar.setTime(arrivalDate);

        departureCalendar = Calendar.getInstance();
        departureCalendar.setTime(departureDate);
        departureCalendar.add(Calendar.DATE, 1);
        departureDate = departureCalendar.getTime();

        getView().displayArrivalDate(DateFormat.format(DATA_FORMAT, arrivalDate).toString());
        getView().displayDepartureDate(DateFormat.format(DATA_FORMAT, departureDate).toString());

        filterModelOps.getCityList(Storage.getInstance().getLoginData());

        //getView().initSpinner();
    }

    @Override
    public void getCityListResult(final List<City> list) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showCityLoadProgressBar(false);
                getView().initSpinner(list);
            }
        });

    }

    @Override
    public void getCityListResultFailed(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showCityLoadProgressBar(false);
                getView().makeToast(s);
            }
        });
    }

    @Override
    public void arrivalDateClick() {
        getView().showCalendarDialog(arrivalCalendar, Calendar.getInstance(), DialogChoice.ARRIVAL.name());
    }

    @Override
    public void departureDateClick() {
        Calendar minCalendarTime = Calendar.getInstance();
        minCalendarTime.setTime(arrivalCalendar.getTime());
        minCalendarTime.add(Calendar.DATE, 1);

        getView().showCalendarDialog(departureCalendar, minCalendarTime, DialogChoice.DEPARTURE.name());

    }

    @Override
    public void arrivalDateChange(Date date) {
        arrivalCalendar.setTime(date);
        getView().displayArrivalDate(DateFormat.format(DATA_FORMAT, date).toString());
        if (arrivalCalendar.getTime().compareTo(departureCalendar.getTime()) >= 0) {
            Log.d("MyApp_filter", "arrivalCalendar > departureCalendar, update departureCalendar ");
            departureCalendar.setTime(arrivalCalendar.getTime());
            departureCalendar.add(Calendar.DATE, 1);

            getView().displayDepartureDate(DateFormat.format(DATA_FORMAT, departureCalendar.getTime()).toString());
        }

    }

    @Override
    public void departureDateChange(Date date) {
        departureCalendar.setTime(date);
        getView().displayDepartureDate(DateFormat.format(DATA_FORMAT, date).toString());
    }

    @Override
    public void search(City city, int numberOfPeople) {
        SearchRequest searchRequest = new SearchRequest(city, arrivalCalendar.getTime().getTime(), departureCalendar.getTime().getTime(), numberOfPeople);
        Storage.getInstance().setSelectedSearchRequest(searchRequest);
        getView().showHotelListView();
    }

}
