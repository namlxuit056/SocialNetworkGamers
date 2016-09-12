package namlxuit.socialnetworkgamers.UI.Login.Google.View;

/**
 * Created by namlxuit on 24/08/2016.
 */

public interface LoginViewOps {
    void logTheUserIn(String username, String uid, String emoji);
    void onFailure();
    void spinProgressBar();
    void stopProgressBar();
}
