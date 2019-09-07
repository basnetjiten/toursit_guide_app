package com.eversoft.tourist_facilitator.reservation;

import com.eversoft.tourist_facilitator.data.NetworkServiceImpl;
import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.interfaces.NetworkService;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RateRequest;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.UserReservationResponse;
import com.eversoft.tourist_facilitator.reservation.interfaces.RateRequestResult;
import com.eversoft.tourist_facilitator.reservation.interfaces.ReservationModelOps;
import com.eversoft.tourist_facilitator.reservation.interfaces.ReservationPresenterOps;
import com.eversoft.tourist_facilitator.reservation.interfaces.UserReservationResult;



class ReservationModel implements ReservationModelOps {
    private final ReservationPresenterOps reservationPresenter;

    //todo remove:, replace with callback
    private final NetworkService networkService;

    public ReservationModel(ReservationPresenterOps ReservationPresenterOps, NetworkServiceImpl networkService) {
        this.reservationPresenter = ReservationPresenterOps;
        this.networkService = networkService;
    }

    @Override
    public void downloadUserReservation(LoginData loginData) {
        networkService.downloadUserReservation(loginData, new UserReservationResult() {
            @Override
            public void userReservationNotAvailable() {
                reservationPresenter.userReservationNotAvailable();
            }

            @Override
            public void userReservationResult(UserReservationResponse userReservationResponse) {
                reservationPresenter.userReservationResult(userReservationResponse);
            }

            @Override
            public void reservationRequestFailure(String s) {
                reservationPresenter.reservationRequestFailure(s);
            }
        });
    }

    @Override
    public void rateRequest(LoginData loginData, RateRequest rateRequest) {
        networkService.rateRequest(loginData,rateRequest, new RateRequestResult(){
            @Override
            public void rateResultOk() {
                reservationPresenter.rateResultOk();
            }

            @Override
            public void rateResultNok(String s) {
                reservationPresenter.rateResultNok(s);
            }
        } );
    }
}
