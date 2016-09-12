package namlxuit.socialnetworkgamers.UI.Login.Google.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import namlxuit.socialnetworkgamers.R;
import namlxuit.socialnetworkgamers.UI.Login.Google.Presenter.FirebaseLoginPresenter;
import namlxuit.socialnetworkgamers.Utils.SignInWithGoogle;

import static namlxuit.socialnetworkgamers.Utils.SignInWithGoogle.RC_SIGN_IN;

/**
 * Created by namlxuit on 22/08/2016.
 */

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,LoginViewOps{

    TextView txtStatus;
    Button mSignInWithGoogleButton;
    private ProgressBar mProgressBar;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    FirebaseLoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addControls();
        mSignInWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getLoginInteractor().getmAuth().addAuthStateListener(presenter.getLoginInteractor().getmAuthListener());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter.getLoginInteractor().getmAuthListener()!= null) {
            presenter.getLoginInteractor().getmAuth().removeAuthStateListener(presenter.getLoginInteractor().getmAuthListener());
        }
    }

    private void addControls() {
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        mSignInWithGoogleButton = (Button) findViewById(R.id.btnSignInWithGoogle);
        mLoginButton = (Button) findViewById(R.id.btnSignIn);
        mEmailEditText = (EditText) findViewById(R.id.editTextLogin);
        mPasswordEditText = (EditText) findViewById(R.id.editTextPassword);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        presenter = new FirebaseLoginPresenter(this);

        //SignIn with Google
        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        presenter.getLoginInteractor().setmGoogleApiClient(new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build());
        presenter.getLoginInteractor().setmAuth(FirebaseAuth.getInstance());
        presenter.getLoginInteractor().setmAuthListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(SignInWithGoogle.TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(SignInWithGoogle.TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                updateUI(user);
                // [END_EXCLUDE]
            }
        });
    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if(i == R.id.btnSignInWithGoogle){
            signInWithGoogle();
        }

    }

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(presenter.getLoginInteractor().getmGoogleApiClient());
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(SignInWithGoogle.TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SignInWithGoogle.RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                signInWithGoogleSuccess();
                //GoogleSignInAccount account = result.getSignInAccount();
                //presenter.getLoginInteractor().firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    private void updateUI(Object o) {
        signInWithGoogleSuccess();
    }


    @Override
    public void logTheUserIn(String username, String uid, String emoji) {
    }

    @Override
    public void onFailure() {
        Toast.makeText(LoginActivity.this, R.string.on_failure_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void spinProgressBar() {
            mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void stopProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }
    /* /
     *signIn is success go Main
     * */
    public void signInWithGoogleSuccess() {
        Toast.makeText(this, "Login success",Toast.LENGTH_LONG).show();
    }
}
