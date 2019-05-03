package com.example.cleanlabel.Utility;

import com.google.android.material.textfield.TextInputLayout;

public interface Validatepassword {

    public void requestfocus();

    public void unfocus();

    public void show_toggle_error(TextInputLayout textInputLayout,String error_message);

    public void unshow_toggle_error(TextInputLayout textInputLayout);

    public void show_errorforconfirm_password(String error_message,TextInputLayout textInputLayout);

    public void disable_error_confirmpassword();

    public boolean validate_password(String pass, String confirmPass);

    public boolean validate_password();

    public void getpassword();

    public void validate_confirmpass();

    public void show_error();

}
