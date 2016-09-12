package namlxuit.socialnetworkgamers.UI.Login.Google.Presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import namlxuit.socialnetworkgamers.UI.Login.Google.Interactor.LoginInteractor;
import namlxuit.socialnetworkgamers.UI.Login.Google.View.LoginActivity;

/**
 * Created by namlxuit on 22/08/2016.
 */

public class FirebaseLoginPresenter implements
        GoogleApiClient.OnConnectionFailedListener, FirebaseLoginPresenterOps{
    private LoginInteractor loginInteractor;
    private LoginActivity loginActivity;

    public FirebaseLoginPresenter(LoginActivity view) {
        this.loginActivity = view;
        loginInteractor = new LoginInteractor(this);
    }


    public FirebaseLoginPresenter(LoginActivity loginActivity, LoginInteractor loginInteractor) {
        this.loginActivity = loginActivity;
        this.loginInteractor = loginInteractor;
    }

    private void showProgressDialog() {
    }

    public LoginActivity getLoginView() {
        return loginActivity;
    }

    public LoginInteractor getLoginInteractor() {
        return loginInteractor;
    }

    public void setLoginInteractor(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
       loginActivity.stopProgressBar();
        loginActivity.onFailure();
    }

    @Override
    public void receiveUserLogin(String email, String password) {
        loginActivity.spinProgressBar();
    }

    @Override
    public void onFailure() {
        loginActivity.stopProgressBar();
        loginActivity.onFailure();
    }

    @Override
    public void onSuccess(String user, String uid, String emoji) {
        loginActivity.stopProgressBar();
        loginActivity.signInWithGoogleSuccess();
    }
}
