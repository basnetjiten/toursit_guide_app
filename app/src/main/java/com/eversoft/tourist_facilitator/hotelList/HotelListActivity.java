package com.eversoft.tourist_facilitator.hotelList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eversoft.tourist_facilitator.R;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.HotelData;
import com.eversoft.tourist_facilitator.hotelDetail.HotelDetailActivity;
import com.eversoft.tourist_facilitator.hotelList.interfaces.HotelListPresenterOps;
import com.eversoft.tourist_facilitator.hotelList.interfaces.HotelListViewOps;
import com.eversoft.tourist_facilitator.hotelList.list.HotelArrayAdapter;
import com.eversoft.tourist_facilitator.reservation.ReservationActivity;
import com.eversoft.tourist_facilitator.storage.Storage;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class HotelListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HotelListViewOps {

    private HotelListPresenterOps mPresenter;

    private ListView hotelListView;
    private MaterialDialog progressDialog;
    HotelArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        hotelListView = (ListView) findViewById(R.id.hotelListView);


        setupMVP();
        mPresenter.onStartup();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void setupMVP() {
        mPresenter = new HotelListPresenter(this);
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

        if (id == R.id.nav_your_reservation) {
            mPresenter.showReservationRequested();
        } else if (id == R.id.nav_filter) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initHotelListView(ArrayList<HotelData> hotelData) {
        adapter = new HotelArrayAdapter(this, hotelData);
        hotelListView.setAdapter(adapter);

        hotelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                mPresenter.listItemSelect((HotelData) parent.getAdapter().getItem(pos));
            }
        });
    }

    @Override
    public void showHotelDetailView() {
        Intent myIntent = new Intent(this, HotelDetailActivity.class);
        this.startActivity(myIntent);
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
    public void stopProgressBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showAlertDialogAndFinish(String s) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
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
    public void updateListView(List<HotelData> items) {
        Log.d("MyApp_list", "updateListView invoked");
        adapter.swapItems(items);
    }

    @Override
    public void showReservationActivity() {
        Intent myIntent = new Intent(this, ReservationActivity.class);
        this.startActivity(myIntent);
    }
}
