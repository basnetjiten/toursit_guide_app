package com.eversoft.tourist_facilitator.data.serverEntity.endpoint;

public class ReservationRequest {
    private long arrivalTime;
    private long departureTim;
    private long reservationRoomId;

    public ReservationRequest() {

    }

    public ReservationRequest(long arrivalTime, long departureTim, long reservationRoomId) {
        this.arrivalTime = arrivalTime;
        this.departureTim = departureTim;
        this.reservationRoomId = reservationRoomId;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getDepartureTim() {
        return departureTim;
    }

    public void setDepartureTim(long departureTim) {
        this.departureTim = departureTim;
    }

    public long getReservationRoomId() {
        return reservationRoomId;
    }

    public void setReservationRoomId(long reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
    }

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "arrivalTime=" + arrivalTime +
                ", departureTim=" + departureTim +
                ", reservationRoomId=" + reservationRoomId +
                '}';
    }
}
