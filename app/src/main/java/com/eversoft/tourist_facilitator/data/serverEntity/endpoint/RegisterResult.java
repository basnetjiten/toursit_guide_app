package com.eversoft.tourist_facilitator.data.serverEntity.endpoint;

public enum RegisterResult {
    USEREXIST(0), OK(1);

    private final double id;

    RegisterResult(int id) {
        this.id = id;
    }
}
