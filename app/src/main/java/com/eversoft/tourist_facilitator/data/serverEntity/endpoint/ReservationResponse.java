package com.eversoft.tourist_facilitator.data.serverEntity.endpoint;

import com.eversoft.tourist_facilitator.data.serverEntity.hotel.data.ResultStatus;



public class ReservationResponse {

    ResultStatus status;

    public ReservationResponse() {
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationResponse{" +
                "status=" + status +
                '}';
    }
}
