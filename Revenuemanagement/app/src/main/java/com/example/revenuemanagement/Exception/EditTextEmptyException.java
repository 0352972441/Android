package com.example.revenuemanagement.Exception;

import androidx.annotation.Nullable;

public class EditTextEmptyException extends Exception{
    private String message;
    EditTextEmptyException(String message){
        super(message);
        this.message = message;
    }

    @Nullable
    @Override
    public String getMessage() {
        return  message;
    }
}
