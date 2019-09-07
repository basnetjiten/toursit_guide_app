package com.eversoft.tourist_facilitator.data.serverEntity.hotel.data;

public enum ResultStatus {
    OK(0), NO_DATA(1), RESERVATION_NOT_POSSIBLE(2),RATE_ERROR(3), NOT_INITIALED(99);

    private int value;

    ResultStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};
