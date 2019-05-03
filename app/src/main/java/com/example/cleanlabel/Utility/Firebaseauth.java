package com.example.cleanlabel.Utility;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface Firebaseauth {

   public boolean currently_signed_user();

   public void createUserWithEmailAndPassword(String email, String password);

   public void signInWithEmailAndPassword(String email, String password);

   public void update_password(String password);

   public void log_out();

   public User accessuserinfo();

   public void configured_google_signin();

   public void sign_in_google();

   public void firebaseAuthWithGoogle(GoogleSignInAccount account);

   public void login_facebook();

   public void handleFacebookAccessToken(AccessToken token);

   public String getUserId();

   public String getUserName();


}
