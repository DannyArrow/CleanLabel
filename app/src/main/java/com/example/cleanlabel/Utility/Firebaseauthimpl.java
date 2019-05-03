package com.example.cleanlabel.Utility;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.cleanlabel.Fragments.Register;
import com.example.cleanlabel.MainActivity;
import com.example.cleanlabel.R;
import com.facebook.AccessToken;
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

public class Firebaseauthimpl extends AppCompatActivity implements Firebaseauth{
    private OnProgressEventListener onProgressEventListener;
    private static Firebaseauthimpl firebaseauthimpl = null;
    private static final int RC_SIGN_IN = 7;
    private static FirebaseAuth mAuth;
    Register context;
    GoogleSignInClient mGoogleSignInClient;

    public Firebaseauthimpl() {
        mAuth = FirebaseAuth.getInstance();

    }

    private Firebaseauthimpl(Register context) {
        mAuth = FirebaseAuth.getInstance();
         this.context = context;

    }

    public static Firebaseauthimpl getInstance(Register context)
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
                            //Navigation.createNavigateOnClickListener(R.id.action_register_to_verify);
                            NavHostFragment.findNavController(context).navigate(R.id.prefrence);

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
                Get_Error_message(errorcode);

            }

        });
        onProgressEventListener.dismiss_progress();

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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("g", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("g", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
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


         mGoogleSignInClient = GoogleSignIn.getClient(context.getActivity(),gso);

         sign_in_google();

    }

    @Override
    public void sign_in_google() {
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
                            Log.d("exception google", "signInWithCredential:success");
                            Log.d("exception google", "new user id" + user.getUid());
                            notifyUser("new user id" + user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("exception google", "signInWithCredential:failure", task.getException());
                            notifyUser("Sign in failed with google ");
                        }

                        onProgressEventListener.dismiss_progress();
                    }
                });

    }

    @Override
    public void login_facebook() {

    }

    @Override
    public void handleFacebookAccessToken(AccessToken token) {

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
        Toast.makeText(context.getContext(), text,
                Toast.LENGTH_LONG).show();
    }
}
