package com.example.cleanlabel.Utility;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.cleanlabel.Fragments.Register;
import com.example.cleanlabel.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Validation implements TextWatcher {

    private static Register context;
    private View view;
    private TextInputLayout textInputLayout;
    private String password;

    public Validation(View view, Register context, TextInputLayout textInputLayout) {
        this.view = view;
        this.context = context;
        this.textInputLayout = textInputLayout;
    }

    public Validation(View view, Register context, TextInputLayout textInputLayout, String password) {
        this.view = view;
        this.context = context;
        this.textInputLayout = textInputLayout;
        this.password = password;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    public void afterTextChanged(Editable editable) {
        switch (view.getId()) {
            case R.id.editname:
                validateName((EditText) view.findViewById(R.id.editname),textInputLayout);
                break;
            case R.id.editemail:
                TextInputEditText inputEditText = view.findViewById(R.id.editemail);
                validateEmail(inputEditText,textInputLayout);
                break;
            case R.id.editpasseord:

                TextInputEditText inputEditText1 = view.findViewById(R.id.editpasseord);
                validatePassword(inputEditText1,textInputLayout);
                break;
            case R.id.editconfirmpass:
                validateconfirm_password((EditText) view.findViewById(R.id.editconfirmpass),textInputLayout);
        }
    }



    public boolean validateconfirm_password(EditText editText, TextInputLayout inputLayoutName){
        if(!editText.getText().toString().trim().equals(password)){
            inputLayoutName.setError("doesn't match password");
            requestFocus(editText);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
            return true;
        }

    }


    public static String validateName(EditText editText, TextInputLayout inputLayoutName) {
        String name = editText.getText().toString().trim();
        if (name.isEmpty()) {
            inputLayoutName.setError("please enter name");
            requestFocus(editText);
            return null;
        } else {
            inputLayoutName.setErrorEnabled(false);
            return name;
        }


    }

    public String validateEmail(TextInputEditText editText, TextInputLayout textInputEditText) {
        String email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            textInputEditText.setError("invalid email");
            requestFocus(editText);
            return null;
        } else {
            textInputEditText.setErrorEnabled(false);
            return email;
        }


    }

    public String validatePassword(TextInputEditText editText,TextInputLayout inputLayoutPassword) {
        String password = editText.getText().toString().trim();
        if (password.isEmpty() || editText.getText().toString().length() > 6) {
            inputLayoutPassword.setError("Password must be at least 6 character");
            requestFocus(inputLayoutPassword);
            return null;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
            return password;
        }
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static void requestFocus(View view) {
        if (view.requestFocus()) {
            context.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
