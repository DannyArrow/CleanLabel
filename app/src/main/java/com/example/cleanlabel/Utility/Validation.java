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
    private static String name = "";
    private static String email = "";

    public Validation(View view, Register context, TextInputLayout textInputLayout) {
        this.view = view;
        this.context = context;
        this.textInputLayout = textInputLayout;
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

        }
    }


    private static String validateName(EditText editText, TextInputLayout inputLayoutName) {
           name = editText.getText().toString().trim();

        if (name.isEmpty()) {
            inputLayoutName.setError("please enter name");
            requestFocus(editText);
            return null;
        } else {
            inputLayoutName.setErrorEnabled(false);
            return name;
        }
    }

    public static boolean validateName(){
        return name.isEmpty();
    }



    private String validateEmail(TextInputEditText editText, TextInputLayout textInputEditText) {
         email = editText.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            textInputEditText.setError("invalid email");
            requestFocus(editText);
            return null;
        } else {
            textInputEditText.setErrorEnabled(false);
            return email;
        }


    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidEmail() {
        return email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private static void requestFocus(View view) {
        if (view.requestFocus()) {
            context.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
