package com.eversoft.tourist_facilitator.filter;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.eversoft.tourist_facilitator.R;
import com.eversoft.tourist_facilitator.data.entity.DialogChoice;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.City;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterPresenterOps;
import com.eversoft.tourist_facilitator.filter.interfaces.FilterViewOps;
import com.eversoft.tourist_facilitator.hotelList.HotelListActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FilterActivity extends FragmentActivity implements FilterViewOps, DatePickerDialog.OnDateSetListener {


    FilterPresenterOps mPresenter;
    Spinner citySpinner;
    LinearLayout arrivalLayout;
    LinearLayout departureLayout;
    TextView viewDepartureDate;
    TextView viewArrivalDate;
    NumberPicker numberPicker;
    Button mSearchButton;
    private View mCityLoadProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MyApp_Filter", " FilterActivity onCreate invoked");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        citySpinner = (Spinner) this.findViewById(R.id.citySpinner);
        viewDepartureDate = (TextView) findViewById(R.id.filter_departure_date);
        viewArrivalDate = (TextView) findViewById(R.id.filter_arrival_date);

        mCityLoadProgressView = findViewById(R.id.city_load_progress);

        arrivalLayout = (LinearLayout) this.findViewById(R.id.arrival_linear);
        arrivalLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.arrivalDateClick();
            }
        });

        departureLayout = (LinearLayout) this.findViewById(R.id.departure_linear);
        departureLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.departureDateClick();
            }
        });

        numberPicker = (NumberPicker) findViewById(R.id.filter_number_picker);

        mSearchButton = (Button) findViewById(R.id.filter_select_button);
        mSearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSearch();
            }
        });


        setupMVP();

        mPresenter.onStartup();
    }

    private void setupMVP() {
        mPresenter = new FilterPresenter(this);
    }

    @Override
    public void initSpinner(List<City> list) {
        Log.d("MyApp_Filter", "initSpinner invoked");
        citySpinner = (Spinner) this.findViewById(R.id.citySpinner);
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, list);

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        citySpinner.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public void showCityLoadProgressBar(boolean show) {
        mCityLoadProgressView.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Date getCurrentDate() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    @Override
    public void displayArrivalDate(String s) {
        viewArrivalDate.setText(s);

    }

    @Override
    public void displayDepartureDate(String s) {
        viewDepartureDate.setText(s);
    }

    @Override
    public void showCalendarDialog(Calendar current, Calendar minDate, String title) {

        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(FilterActivity.this, this, year, month, day);

        dpd.setTitle(title);


        dpd.show();
    }


//    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)


    /*@Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Log.d("MyApp_filter", "onDateSet return the following date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year + " tag " + view.getTag());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(0);
        try {
            d = sdf.parse(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (view.getTag().equals(DialogChoice.ARRIVAL.name())) {
            Log.d("MyApp_filter", "arrival change sent");
            mPresenter.arrivalDateChange(d);
        } else if (view.getTag().equals(DialogChoice.DEPARTURE.name())) {
            mPresenter.departureDateChange(d);
        } else {
            Log.e("MyApp_filter", "onDateSet received incorrect tag: " + view.getTag());
        }
    }*/

    private void attemptSearch() {
        Log.d("MyApp_search", "view attemptSearch invoked");
        // Store values at the time of the login attempt.
        City city = (City) citySpinner.getSelectedItem();
        int value = numberPicker.getValue();

        mPresenter.search(city, numberPicker.getValue());
        Log.d("MyApp_search", city.toString() + "  " + value);
    }

    @Override
    public void showHotelListView() {
        Intent myIntent = new Intent(this, HotelListActivity.class);
        this.startActivity(myIntent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Log.d("MyApp_filter", "onDateSet return the following date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year + " tag " + view.getTag());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(0);
        try {
            d = sdf.parse(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (view.getTag().equals(DialogChoice.ARRIVAL.name())) {
            Log.d("MyApp_filter", "arrival change sent");
            mPresenter.arrivalDateChange(d);
        } else if (view.getTag().equals(DialogChoice.DEPARTURE.name())) {
            mPresenter.departureDateChange(d);
        } else {
            Log.e("MyApp_filter", "onDateSet received incorrect tag: " + view.getTag());
        }
    }
}
