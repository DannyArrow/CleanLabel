package com.example.cleanlabel.Utility;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Validatepasswordimpl implements Validatepassword, TextWatcher {

    TextInputEditText editpassword;
    TextInputEditText edit_passwordconfirm;
    TextInputLayout passwordLayout_text;
    TextInputLayout confirmpasswordLayout_text;

    public Validatepasswordimpl(TextInputEditText editpassword, TextInputEditText edit_passwordconfirm,
                                TextInputLayout passwordLayout_text, TextInputLayout confirmpasswordLayout_text) {

        this.edit_passwordconfirm = edit_passwordconfirm;
        this.editpassword = editpassword;
        this.passwordLayout_text = passwordLayout_text;
        this.confirmpasswordLayout_text = confirmpasswordLayout_text;

        edit_passwordconfirm.addTextChangedListener(this);
        editpassword.addTextChangedListener(this);
    }

    @Override
    public void requestfocus() {

    }

    @Override
    public void unfocus() {

    }

    @Override
    public void show_toggle_error(TextInputLayout textInputLayout, String error_message) {
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setPasswordVisibilityToggleEnabled(true);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setCounterMaxLength(6);
        textInputLayout.setError(error_message);
    }

    @Override
    public void unshow_toggle_error(TextInputLayout textInputLayout) {
        textInputLayout.setCounterEnabled(false);
        textInputLayout.setPasswordVisibilityToggleEnabled(true);
        textInputLayout.setErrorEnabled(false);

    }



    @Override
    public void show_errorforconfirm_password(String error_message,TextInputLayout textInputLayout) {
        textInputLayout.setCounterEnabled(false);
        textInputLayout.setPasswordVisibilityToggleEnabled(false);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(error_message);
    }

    @Override
    public void disable_error_confirmpassword() {

    }

    @Override
    public boolean validate_password(String pass, String confirmPass) {
        if(pass.equals(confirmPass)){
            return true;
        }
        return false;
    }

    @Override
    public boolean validate_password() {
        return false;
    }

    @Override
    public void getpassword() {

    }

    @Override
    public void validate_confirmpass() {

    }

    @Override
    public void show_error() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        if(edit_passwordconfirm.getText().toString().trim().length() >= 6){
            if(!validate_password(editpassword.getText().toString().trim(),edit_passwordconfirm.getText().toString().trim()))
            {
                show_errorforconfirm_password("Password don't match", confirmpasswordLayout_text);
            }else{
                unshow_toggle_error(confirmpasswordLayout_text);
            }
        }



        if (edit_passwordconfirm.hasFocus()) {
            Log.d("has focus", " confirm password has focus");
            if (edit_passwordconfirm.getText().toString().trim().length() <= 6 && !edit_passwordconfirm.getText().toString().trim().isEmpty()) {
                show_toggle_error(confirmpasswordLayout_text,"invalid password");
            }
        }


        if (editpassword.hasFocus()) {
            Log.d("has focus", " password has focus");
            if (editpassword.getText().toString().trim().length() <= 6 && !editpassword.getText().toString().trim().isEmpty()) {
                show_toggle_error(passwordLayout_text,"invalid password");
            } else{
                unshow_toggle_error(passwordLayout_text);
            }

        }


    }
}
