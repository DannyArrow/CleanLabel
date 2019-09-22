package com.example.cleanlabel.Utility;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.cleanlabel.Fragments.Register;
import com.example.cleanlabel.MainActivity;
import com.example.cleanlabel.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.List;

public class Firebaseauthimpl extends AppCompatActivity implements Firebaseauth{
    private static final String ERROR_EMAIL_ALREADY_IN_USE = "ERROR_EMAIL_ALREADY_IN_USE";
    private OnProgressEventListener onProgressEventListener;
    private static Firebaseauthimpl firebaseauthimpl = null;
    private static final int RC_SIGN_IN = 7;
    private static FirebaseAuth mAuth;
    public CallbackManager callbackManager;
    private String tag = "credentialinfo";
    Activity context;
    GoogleSignInClient mGoogleSignInClient;
    private final String ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL = "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL";

    public Firebaseauthimpl() {
        mAuth = FirebaseAuth.getInstance();

    }

    private Firebaseauthimpl(Activity context) {
        mAuth = FirebaseAuth.getInstance();
        this.context = context;

    }

    public static Firebaseauthimpl getInstance(Activity context)
    {
        if(firebaseauthimpl == null){
            firebaseauthimpl = new Firebaseauthimpl(context);
        }
       return firebaseauthimpl;
    }

    public static Firebaseauthimpl getInstance()
    {
        if(firebaseauthimpl == null){
            firebaseauthimpl = new Firebaseauthimpl();
        }
        return firebaseauthimpl;
    }

    public void register_listener(OnProgressEventListener listener){
        this.onProgressEventListener = listener;
    }



    @Override
    public boolean currently_signed_user() {
        if(mAuth.getCurrentUser() != null){
            FirebaseAuth.getInstance().signOut();
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("user", "createUserWithEmail:success");
                          notifyUser("Succesfully created account");
                           // Navigation.createNavigateOnClickListener(R.id.action_registerbtn_to_verify);
                            onProgressEventListener.dismiss_progress();
                           onProgressEventListener.sucessfully_login();
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String errorcode = e.getMessage();
                if(e instanceof FirebaseAuthUserCollisionException) {
                     errorcode = ((FirebaseAuthUserCollisionException) e).getErrorCode();
                } else if(e instanceof FirebaseAuthInvalidUserException){
                    errorcode = ((FirebaseAuthInvalidUserException) e).getErrorCode();
                }
                else if(e instanceof FirebaseAuthWeakPasswordException) {
                     errorcode = ((FirebaseAuthWeakPasswordException) e).getErrorCode();
                }
                Log.i(tag,".... " + errorcode);
                if(errorcode.equals(ERROR_EMAIL_ALREADY_IN_USE)) {
                    onProgressEventListener.Toast_Message_Error("Error Email Already In Use");
                }
                onProgressEventListener.dismiss_progress();
            }

        });
    }

   public void Get_Error_message(String errorcode){
        if(errorcode.equals("ERROR_WEAK_PASSWORD")){
            notifyUser("Password has to be atleast 6 character");
        } else if (errorcode.equals("The email address is badly formatted")) {
            notifyUser("The email address is badly formatted");
        } else if(errorcode.equals("ERROR_EMAIL_ALREADY_IN_USE")){
              notifyUser("Email is already in use");
            }else{
            notifyUser(errorcode);
        }

        Log.i("autherror", errorcode);
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password) {
        if (email.isEmpty() || !isValidEmail(email)){
            onProgressEventListener.Toast_Message_Error("Email is invalid");
            onProgressEventListener.dismiss_progress();
            return;
        }

        if (password.isEmpty() || password.length() < 6){
            onProgressEventListener.Toast_Message_Error("Please enter password");
            onProgressEventListener.dismiss_progress();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("g", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            onProgressEventListener.dismiss_progress();
                            onProgressEventListener.sucessfully_login();
                        } else {
                            // If sign in fails, display a message to the user.
                            onProgressEventListener.dismiss_progress();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                String errorCode =
                                        ((FirebaseAuthInvalidCredentialsException) task.getException()).getErrorCode();
                                Log.i("errorcode = ", errorCode);
                                //ERROR_WRONG_PASSWORD
                                if(errorCode.equals("ERROR_WRONG_PASSWORD")){
                                     onProgressEventListener.Toast_Message_Error("Wrong Password");
                                     return;
                                }
                                onProgressEventListener.Toast_Message_Error(task.getException().getLocalizedMessage());
                                return;

                            }
                            if(task.getException() instanceof  FirebaseAuthInvalidUserException) {
                                // ERROR_USER_NOT_FOUND
                                Log.w("errorcode", "signInWithEmail:failure", task.getException());
                                String errorCode =
                                        ((FirebaseAuthInvalidUserException) task.getException()).getErrorCode();
                                if(errorCode.equals("ERROR_USER_NOT_FOUND")){
                                    onProgressEventListener.Toast_Message_Error("User with this email doesn't exist");
                                    return;
                                }
                                onProgressEventListener.Toast_Message_Error(task.getException().getLocalizedMessage());

                                Log.i("errorcode", errorCode);
                                return;
                            }

                            if(task.getException() instanceof  FirebaseAuthUserCollisionException){
                                Log.w("errorcode", "signInWithEmail:failure", task.getException());
                                String errorCode =
                                        ((FirebaseAuthUserCollisionException) task.getException()).getErrorCode();
                                onProgressEventListener.Toast_Message_Error(task.getException().getLocalizedMessage());
                                Log.i("errorcode", errorCode);
                                return;
                            }

                            Log.i("errorcode",task.getException().getMessage());
                            if(task.getException().getMessage().equals("A network error (such as timeout, interrupted connection or unreachable host) has occurred.")) {
                                onProgressEventListener.Toast_Message_Error("A network error has ocuured, please check your internet connection");
                                return;
                            }
                              onProgressEventListener.Toast_Message_Error(task.getException().getMessage());
                        }

                        // ...
                    }

                });
    }

    @Override
    public void update_password(String password) {

    }

    @Override
    public void log_out() {

    }

    @Override
    public User accessuserinfo() {
        return null;
    }

    @Override
    public void configured_google_signin() {
        Log.w("exception google", "start Activity");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


         mGoogleSignInClient = GoogleSignIn.getClient(context,gso);



    }

    @Override
    public void sign_in_google() {
        configured_google_signin();
        Log.w("exception google", "start Activity");

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        context.startActivityForResult(signInIntent, RC_SIGN_IN);

    }





    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d("firebase", "firebaseAuthWithGoogle:" + account.getId());

        //mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(tag, "signInWithCredential:success");
                            Log.d(tag,  user.getUid() + "   name: " + user.getDisplayName() + " phone number "+ user.getPhoneNumber() + " email "+ user.getEmail());
                            notifyUser("credentialinfo" + user.getUid() + "   name: " + user.getDisplayName() + " phone number "+ user.getPhoneNumber() + " email "+ user.getEmail());
                            link_usercredential(mAuth,credential);
                            onProgressEventListener.dismiss_progress();
                            onProgressEventListener.sucessfully_login();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("credentialinfo", "signInWithCredential:failure", task.getException());
                            notifyUser("Sign in failed with google ");
                            onProgressEventListener.dismiss_progress();
                        }

                    }
                });
    }

    @Override
    public void login_facebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager loginManager = LoginManager.getInstance();
        List<String> permissions = new ArrayList<>();
        permissions.add("email");
        permissions.add("public_profile");

        loginManager.logInWithReadPermissions(context,permissions);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i(tag, String.valueOf(loginResult.getAccessToken()));
                        handleFacebookAccessToken(loginResult.getAccessToken());

                    }

                    @Override
                    public void onCancel() {
                        // App code
                        onProgressEventListener.dismiss_progress();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        if (exception instanceof FacebookAuthorizationException) {
                            Log.i(tag,exception.getMessage());
                                if (AccessToken.getCurrentAccessToken() != null) {
                                    handleFacebookAccessToken(AccessToken.getCurrentAccessToken());
                                   }
                                }
                            }



                });
    }

    @Override
    public void handleFacebookAccessToken(AccessToken token) {
        Log.d(tag, "handleFacebookAccessToken:" + token);

        final AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("facebooktoken", "signInWithCredential:success");
                            onProgressEventListener.dismiss_progress();
                            FirebaseUser user = mAuth.getCurrentUser();
                          //  link_usercredential(mAuth,credential);
                            Log.i("userinfo", " name: " + user.getDisplayName() + " email: " + user.getEmail() +  "phone number: " + user.getPhoneNumber());
                            onProgressEventListener.dismiss_progress();
                            onProgressEventListener.sucessfully_login();

                        } else {
                            // If sign in fails, display a message to the user.
                           if(task.getException() instanceof FirebaseAuthUserCollisionException){
                              if(((FirebaseAuthUserCollisionException) task.getException()).getErrorCode().equals(ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL)){
                                  onProgressEventListener.dismiss_progress();
                                  onProgressEventListener.Toast_Message_Error("Cant Login to facebook, An account already exist with the same email but different sign-in.");
                              }

                           }



                            Log.w(tag, "signInWithCredential:failure", task.getException());

                            Log.d(tag, "signInWithCredential:fail");

                        }

                        // ...
                    }
                });
    }

    private void link_usercredential(FirebaseAuth auth, AuthCredential credential){
        auth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(tag, "linkWithCredential:success");
                }else{
                    Log.d(tag, "linkWithCredential:failure");
                }
            }
        });
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    public void notifyUser(String text){
        Toast.makeText(context, text,
                Toast.LENGTH_LONG).show();
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
