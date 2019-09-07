package com.eversoft.tourist_facilitator.login.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;


public interface LoginModelOps {
    void login(LoginReqParam loginReqParam);

    void validateLoginParameters(LoginReqParam loginReqParam);

    void register(RegisterRequest registerRequest);
}
