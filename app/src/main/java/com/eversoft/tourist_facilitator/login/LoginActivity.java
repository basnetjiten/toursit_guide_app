package com.eversoft.tourist_facilitator.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eversoft.tourist_facilitator.R;
import com.eversoft.tourist_facilitator.data.serverEntity.endpoint.RegisterRequest;
import com.eversoft.tourist_facilitator.filter.FilterActivity;
import com.eversoft.tourist_facilitator.login.entity.LoginReqParam;
import com.eversoft.tourist_facilitator.login.entity.Result;
import com.eversoft.tourist_facilitator.login.interfaces.LoginPresenterOps;
import com.eversoft.tourist_facilitator.login.interfaces.LoginViewOps;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginViewOps {

    //Presenter interface
    LoginPresenterOps mPresenter;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private AutoCompleteTextView mLoginView;
    private EditText mPasswordView;
    private View mProgressView;
    private MaterialDialog progressDialog;
    EditText userText;
    EditText psswdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupMVP();

        // Set up the login form.
        mLoginView = (AutoCompleteTextView) findViewById(R.id.user_login);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_in_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        addEmailsToAutoComplete();

        mPresenter.onStartup();

    }

    private void attemptRegister() {
        Log.d("MyApp_login", "view attemptRegister invoked");

        mPresenter.attemptRegister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void setupMVP() {
        LoginPresenter presenter = new LoginPresenter(this);
        mPresenter = presenter;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        Log.d("MyApp_login", "view attemptLogin invoked");
        // Store values at the time of the login attempt.
        String login = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();

        LoginReqParam loginReqParam = new LoginReqParam(login, password);

        mPresenter.attemptLogin(loginReqParam);
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @Override
    public void showProgress(final boolean show) {

        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void addEmailsToAutoComplete() {
        List<String> emailAddressCollection = new ArrayList<>();
        emailAddressCollection.add("john.doe@gmail.com");

        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mLoginView.setAdapter(adapter);
    }

    @Override
    public void showLoginError(Result msg, String message) {

        //todo improve
        switch (msg) {
            case LoginInvalid:
                mLoginView.setError(getString(R.string.error_invalid_email) + message);
                break;
            case PasswordInvalid:
                mPasswordView.setError(getString(R.string.error_invalid_password) + message);
                break;
            case AuthFailed:
                mLoginView.setError(getString(R.string.error_authentication_failed) + ": " + message);
                break;
            default:
                mLoginView.setError(getString(R.string.error_login_other) + message);
                Log.e("MyApp_login", "showLoginError, unexpected result code: " + msg);
                break;

        }

    }

    @Override
    public void resetLoginErrors() {
        // Reset errors.
        mLoginView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void showFilterActivity() {
        Intent myIntent = new Intent(this, FilterActivity.class);
        this.startActivity(myIntent);
    }

    @Override
    public void showRegisterDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Zarejestruj")
                .customView(R.layout.dialog_register_custom, true)
                .positiveText("Stwórz konto")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        mPresenter.registerConfirm(new RegisterRequest(userText.getText().toString(), psswdText.getText().toString()));

                    }
                })
                .show();
        userText = (EditText) dialog.getCustomView().findViewById(R.id.dialog_user_name);
        psswdText = (EditText) dialog.getCustomView().findViewById(R.id.dialog_user_pswd);

        Log.d("myapp", userText + "");
        Log.d("myapp", psswdText + "");

    }

    @Override
    public void showProgressDialog() {
        progressDialog = new MaterialDialog.Builder(this)
                .title(R.string.please_wait)
                .content(R.string.process_request)
                .progress(true, 0)
                .show();
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showAlertDialog(String s) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.error)
                .content(s)
                .positiveText(R.string.ok)
                .show();
    }

    @Override
    public void showRegistrationSuccessDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.New_acc)
                .content(R.string.registartion_succ)
                .positiveText(R.string.ok)

                .show();
    }

}


