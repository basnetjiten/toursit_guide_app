package com.eversoft.tourist_facilitator.login.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;
import com.eversoft.tourist_facilitator.login.entity.Result;


public interface LoginModelPresenterOps {
    void loginSuccess(LoginData s);

    void loginFailed(LoginData loginInvalid);

    void validateLoginParamFailed(Result loginInvalid);

    void validateLoginParamSuccess(LoginReqParam loginReqParam);

    void registerResultOk();

    void registerResultFailed(String s);
}
