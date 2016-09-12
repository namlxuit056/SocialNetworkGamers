package namlxuit.socialnetworkgamers.UI.Login.Google.Interactor;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by namlxuit on 22/08/2016.
 */

public interface LoginOps {
    void firebaseAuthWithGoogle(GoogleSignInAccount acct);
}
