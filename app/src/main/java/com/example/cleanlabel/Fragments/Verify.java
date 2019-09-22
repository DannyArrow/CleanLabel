package com.example.cleanlabel.Fragments;


import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cleanlabel.R;
import com.example.cleanlabel.ToolbarManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;


/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 */
public class Verify extends androidx.fragment.app.Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int RESOLVE_HINT = 2;
    private TextView enter_number;
    ConstraintLayout center_lay;
    ConstraintLayout btm_lay;

    GoogleApiClient mCredentialsApiClient;
    private View next;

    public Verify() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        next = view.findViewById(R.id.txtview);
        enter_number = view.findViewById(R.id.textInputLayout7);

        focus_on_textview();

     center_lay = view.findViewById(R.id.center_lay);
      //  btm_lay = view.findViewById(R.id.btm_lay);
      next.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_verify_to_verifyCode));

        return view;
    }

    private void focus_on_textview() {
        enter_number.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
