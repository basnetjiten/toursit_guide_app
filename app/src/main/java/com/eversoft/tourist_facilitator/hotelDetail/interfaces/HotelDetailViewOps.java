package com.eversoft.tourist_facilitator.hotelDetail.interfaces;

import android.graphics.Bitmap;

import android.os.Bundle;
import com.eversoft.tourist_facilitator.data.serverEntity.hotel.HotelDetail;
import com.eversoft.tourist_facilitator.data.serverEntity.room.Room;



public interface HotelDetailViewOps {
    void onCreate(Bundle savedInstanceState);

    void prepareHotelData(HotelDetail hotelDetail);

    void prepareRoomData(Room room);

    void showSnackBar();

    void showConfirmDialog();

    void showAlertDialog(String s, boolean b);

    void showProgressDialog();

    void dismissProgressDialog();

    void showReservationSuccessDialog();

    void endActivity();

    void showReservationActivity();

    void stopPictureProgressBar();

    void showPicture(Bitmap bitmap);

    void showPicture();

    void makeToast(String s);
}
