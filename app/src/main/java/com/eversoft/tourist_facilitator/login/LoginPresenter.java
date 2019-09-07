package com.eversoft.tourist_facilitator.login;

import android.os.Handler;
import android.util.Log;

import com.eversoft.tourist_facilitator.data.NetworkServiceImpl;
import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;
import com.eversoft.tourist_facilitator.login.entity.Result;
import com.eversoft.tourist_facilitator.login.interfaces.LoginModelOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginModelPresenterOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginPresenterOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginViewOps;
import com.eversoft.tourist_facilitator.storage.Storage;

import java.lang.ref.WeakReference;

import static com.eversoft.tourist_facilitator.login.entity.Result.AuthFailed;


public class LoginPresenter implements LoginPresenterOps, LoginModelPresenterOps {

    private final LoginModelOps loginModelOps;
    private final Handler handler = new Handler();
    private WeakReference<LoginViewOps> loginViewOps;

    public LoginPresenter(LoginViewOps loginViewOps) {
        this.loginViewOps = new WeakReference<>(loginViewOps);
        this.loginModelOps = new LoginModel(this, new NetworkServiceImpl());
    }

    private LoginViewOps getView() throws NullPointerException {
        if (loginViewOps != null)
            return loginViewOps.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    @Override
    public void attemptLogin(LoginReqParam loginReqParam) {
        Log.d("MyApp_Login", "presenter attemptLogin invoked");

        getView().resetLoginErrors();

        getView().showProgress(true);
        loginModelOps.login(loginReqParam);

        //first check if given parameters are correct (do not check with server yet)
        //loginModelOps.validateLoginParameters(loginReqParam);
    }

    @Override
    public void onResume() {
        getView().showProgress(false);
    }

    @Override
    public void onStartup() {

    }

    @Override
    public void attemptRegister() {
        getView().showRegisterDialog();
    }

    @Override
    public void registerConfirm(RegisterRequest registerRequest) {
        getView().showProgressDialog();
        loginModelOps.register(registerRequest);
    }

    @Override
    public void loginSuccess(final LoginData s) {
        Log.d("MyApp_Login", "Login OK!!!!");
        Storage.getInstance().setLoginData(s);

        //NetworkServiceImpl temp = new NetworkServiceImpl();
        //temp.testMsg(s);

        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showFilterActivity();
                getView().showProgress(false);

            }
        });

    }

    @Override
    public void loginFailed(final LoginData loginInvalid) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().showLoginError(AuthFailed, loginInvalid.getMessage());
                getView().showProgress(false);

            }
        });
    }

    @Override
    public void validateLoginParamFailed(Result loginInvalid) {
        getView().showLoginError(loginInvalid, "");
    }

    @Override
    public void validateLoginParamSuccess(LoginReqParam loginReqParam) {
        getView().showProgress(true);
        loginModelOps.login(loginReqParam);
    }

    @Override
    public void registerResultOk() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().dismissProgressDialog();
                getView().showRegistrationSuccessDialog();

            }
        });
    }

    @Override
    public void registerResultFailed(final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                getView().dismissProgressDialog();
                getView().showAlertDialog(s);


            }
        });
    }
}
