package namlxuit.socialnetworkgamers.UI.Login.Google.Presenter;

/**
 * Created by namlxuit on 24/08/2016.
 */

public interface FirebaseLoginPresenterOps {
    void receiveUserLogin(String email, String password);
    void onFailure();
    void onSuccess(String user, String uid, String emoji);
}
