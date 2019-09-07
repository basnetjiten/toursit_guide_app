package com.eversoft.tourist_facilitator.login.interfaces;

import com.eversoft.tourist_facilitator.login.entity.Result;


public interface LoginViewOps {

    void showProgress(boolean b);

    void showLoginError(Result msg, String message);

    void resetLoginErrors();

    void showFilterActivity();

    void showRegisterDialog();

    void showProgressDialog();

    void dismissProgressDialog();

    void showAlertDialog(String s);

    void showRegistrationSuccessDialog();
}

