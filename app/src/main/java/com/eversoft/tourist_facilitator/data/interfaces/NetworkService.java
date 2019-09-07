package com.eversoft.tourist_facilitator.data.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RateRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.ReservationRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.SearchRequest;
import com.eversoft.tourist_facilitator.filter.interfaces.LoginServiceCityListResult;
import com.eversoft.tourist_facilitator.filter.interfaces.SearchRequestResult;
import com.eversoft.tourist_facilitator.hotelList.interfaces.ReservationRequestResult;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;
import com.eversoft.tourist_facilitator.login.interfaces.LoginServiceLoginResult;
import com.eversoft.tourist_facilitator.login.interfaces.LoginServiceRegisterResult;
import com.eversoft.tourist_facilitator.login.interfaces.getPictureResult;
import com.eversoft.tourist_facilitator.reservation.interfaces.RateRequestResult;
import com.eversoft.tourist_facilitator.reservation.interfaces.UserReservationResult;


public interface NetworkService {
    void login(LoginReqParam loginReqParam, LoginServiceLoginResult loginServiceResult);

    void getCityList(LoginData loginData, LoginServiceCityListResult loginServiceCityListResult);

    void searchRequest(SearchRequest searchRequest, LoginData loginData, SearchRequestResult searchRequestResult);

    void reservationRequest(ReservationRequest reservationRequest, LoginData loginData, ReservationRequestResult reservationRequestResult);

    void downloadUserReservation(LoginData loginData, UserReservationResult userReservationResult);


    void register(RegisterRequest registerRequest, LoginServiceRegisterResult loginServiceRegisterResult);

    void rateRequest(LoginData loginData, RateRequest rateRequest, RateRequestResult rateRequestResult);

    void getPicture(String picturePath, getPictureResult getPictureResult);
}
