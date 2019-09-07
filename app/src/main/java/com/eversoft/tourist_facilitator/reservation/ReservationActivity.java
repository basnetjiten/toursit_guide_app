package com.eversoft.tourist_facilitator.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eversoft.tourist_facilitator.R;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RateRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationData;
import com.eversoft.tourist_facilitator.filter.FilterActivity;
import com.eversoft.tourist_facilitator.reservation.interfaces.ReservationPresenterOps;
import com.eversoft.tourist_facilitator.reservation.interfaces.ReservationViewOps;
import com.eversoft.tourist_facilitator.storage.Storage;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class ReservationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ReservationViewOps {

    private ReservationPresenterOps mPresenter;
    private MaterialDialog progressDialog;
    private ListView reservationListView;
    private TextView reservationTextView;
    com.eversoft.tourist_facilitator.reservation.ReservationArrayAdapter adapter;
    private RatingBar ratingBar;
    private long selectedReservationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        reservationTextView = (TextView) findViewById(R.id.reservation_msg);
        reservationListView = (ListView) findViewById(R.id.reservation_list_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupMVP();
        mPresenter.onStartup();

    }

    private void setupMVP() {
        mPresenter = new ReservationPresenter(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel_list, menu);
        TextView viewDepartureDate = (TextView) findViewById(R.id.nav_header_user_name);
        viewDepartureDate.setText(Storage.getInstance().getLoginData().getLogin());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_filter) {
            mPresenter.showFilterRequested();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgressBar() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.please_wait)
                .content(R.string.process_request)
                .progress(true, 0)
                .show();
    }

    @Override
    public void hideListView() {
        reservationListView.setVisibility(View.GONE);
    }

    @Override
    public void showDataNotAvailable() {
        reservationTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorDialogAndQuit(String s) {
        new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(s)
                .positiveText(R.string.ok)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void makeToast(String dzialam) {
        Toast.makeText(this, dzialam, Toast.LENGTH_LONG).show();
    }

    @Override
    public void stopProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showFilterActivity() {
      //  Intent myIntent = new Intent(this, FilterActivity.class);
        //this.startActivity(myIntent);
    }

    @Override
    public void showRateDialog(ReservationData item) {
        selectedReservationId = item.getId();
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Rate Hotel")
                .customView(R.layout.dialog_rate_custom, true)
                .positiveText("Submit")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.rateConfirm(new RateRequest(selectedReservationId,(int) ratingBar.getRating()*2));
                        Log.d("myapp","rated: "+ ratingBar.getRating()*2 + " : id: " +  selectedReservationId);
                    }
                })
                .show();
        ratingBar = (RatingBar) dialog.getCustomView().findViewById(R.id.dialog_hotelrate_rate);
    }


    @Override
    public void initListView(ArrayList<ReservationData> reservationDataArrayList) {
        adapter = new ReservationArrayAdapter(this, reservationDataArrayList);
        reservationListView.setAdapter(adapter);

        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                mPresenter.listItemSelect((ReservationData) parent.getAdapter().getItem(pos));
            }
        });
    }

}
