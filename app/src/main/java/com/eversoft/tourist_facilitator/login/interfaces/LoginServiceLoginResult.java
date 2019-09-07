package com.eversoft.tourist_facilitator.login.interfaces;

import com.eversoft.tourist_facilitator.data.entity.LoginData;



public interface LoginServiceLoginResult {

    void loginSuccess(LoginData s);

    void loginFailed(LoginData loginData);
}
