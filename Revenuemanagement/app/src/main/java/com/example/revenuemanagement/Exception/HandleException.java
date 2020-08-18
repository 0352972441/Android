package com.example.revenuemanagement.Exception;

import com.google.android.material.textfield.TextInputLayout;

public class HandleException {
    public static String validationTitle(TextInputLayout mTitle){
        String title = mTitle.getEditText().getText().toString();
        if(title.isEmpty()){
            mTitle.setError("Title can't empty");
            return "Title can't empty";
        }
        return null;
    }

    public static String validationDescription(TextInputLayout mdescription){
        String description = mdescription.getEditText().getText().toString();
        if(description.isEmpty()){
            mdescription.setError("Description can't empty");
            return "Description can't empty";
        }else if(description.length() < 10){
            mdescription.setError("Description the least 10 character");
            return "Description the least 10 character";
        }
        return null;
    }
}
