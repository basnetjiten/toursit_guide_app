package com.eversoft.tourist_facilitator.login.interfaces;

import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;


public interface LoginPresenterOps {
    void attemptLogin(LoginReqParam loginReqParam);

    void onResume();

    void onStartup();

    void attemptRegister();

    void registerConfirm(RegisterRequest registerRequest);
}
