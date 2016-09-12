package namlxuit.socialnetworkgamers.UI.RegisterAccount.Interactor;

import com.google.firebase.database.DatabaseReference;

import java.util.Map;
import java.util.Objects;

import namlxuit.socialnetworkgamers.UI.RegisterAccount.Presenter.FirebaseUserRegisterPresenter;

/**
 * Created by namlxuit on 25/08/2016.
 */

public class RegisterInteractor implements RegisterOps {
    private  DatabaseReference userRef;
    private FirebaseUserRegisterPresenter presenter;

    public RegisterInteractor(DatabaseReference userRef, FirebaseUserRegisterPresenter presenter) {
        this.userRef = userRef;
        this.presenter = presenter;
    }

    public DatabaseReference getUserRef() {
        return userRef;
    }

    public void setUserRef(DatabaseReference userRef) {
        this.userRef = userRef;
    }

    public FirebaseUserRegisterPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(FirebaseUserRegisterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void receiveRegisterRequest(String email, String password) {

    }

    @Override
    public Map<String, Objects> createUser(String email, String password) {
        return null;
    }
}
