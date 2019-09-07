package com.eversoft.tourist_facilitator.login;

import com.eversoft.tourist_facilitator.data.entity.LoginData;
import com.eversoft.tourist_facilitator.data.interfaces.NetworkService;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;
import com.eversoft.tourist_facilitator.login.entity.Result;
import com.eversoft.tourist_facilitator.login.interfaces.LoginModelOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginModelPresenterOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginServiceLoginResult;
import com.eversoft.tourist_facilitator.login.interfaces.LoginServiceRegisterResult;



public class LoginModel implements LoginModelOps {

    private final NetworkService loginService;

    //todo remove:, replace with callback
    private final LoginModelPresenterOps loginPresenter;


    public LoginModel(LoginModelPresenterOps loginPresenter, NetworkService loginService) {
        this.loginPresenter = loginPresenter;
        this.loginService = loginService;
    }


    @Override
    public void login(LoginReqParam loginReqParam) {
        loginService.login(loginReqParam, new LoginServiceLoginResult() {
            @Override
            public void loginSuccess(LoginData s) {
                loginPresenter.loginSuccess(s);
            }

            @Override
            public void loginFailed(LoginData loginData) {
                loginPresenter.loginFailed(loginData);
            }
        });
    }

    @Override
    public void validateLoginParameters(LoginReqParam loginReqParam) {
        boolean error = false;
        if (!isEmailValid(loginReqParam.getLogin())) {
            error = true;
            loginPresenter.validateLoginParamFailed(Result.LoginInvalid);
        }
        if (!isPasswordValid(loginReqParam.getPassword())) {
            error = true;
            loginPresenter.validateLoginParamFailed(Result.PasswordInvalid);
        }
        if (!error) {
            loginPresenter.validateLoginParamSuccess(loginReqParam);
        }

    }

    @Override
    public void register(RegisterRequest registerRequest) {
        if (!registerRequest.getPassword().equals("") && !registerRequest.getUser().equals("")) {

            loginService.register(registerRequest, new LoginServiceRegisterResult() {
                @Override
                public void registerResultOk() {
                    loginPresenter.registerResultOk();
                }

                @Override
                public void registerResultFailed(String s) {
                    loginPresenter.registerResultFailed(s);
                }
            });
        } else {
            loginPresenter.registerResultFailed("Login i hasło nie mogą byc puste");
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
