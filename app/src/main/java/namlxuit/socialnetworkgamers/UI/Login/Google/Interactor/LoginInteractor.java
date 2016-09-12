package namlxuit.socialnetworkgamers.UI.Login.Google.Interactor;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

import namlxuit.socialnetworkgamers.UI.Login.Google.Presenter.FirebaseLoginPresenter;

import static namlxuit.socialnetworkgamers.Utils.SignInWithGoogle.TAG;

/**
 * Created by namlxuit on 22/08/2016.
 */

public class LoginInteractor implements LoginOps{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseLoginPresenter presenter;
    public LoginInteractor(FirebaseLoginPresenter pre) {
        this.presenter = pre;
    }
    public LoginInteractor(FirebaseAuth mAuth, FirebaseAuth.AuthStateListener mAuthListener, GoogleApiClient mGoogleApiClient, FirebaseLoginPresenter presenter) {
        this.mAuth = mAuth;
        this.mAuthListener = mAuthListener;
        this.mGoogleApiClient = mGoogleApiClient;
        this.presenter = presenter;
    }



    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseAuth.AuthStateListener getmAuthListener() {
        return mAuthListener;
    }

    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuthListener = mAuthListener;
    }

    public GoogleApiClient getmGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setmGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithCredential", task.getException());
                    Toast.makeText(presenter.getLoginView(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
