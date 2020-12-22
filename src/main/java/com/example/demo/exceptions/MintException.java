package com.example.demo.exceptions;

/**
 * Created by Osagie Erhabor on 25/02/2020.
 */
public class MintException extends RuntimeException {

    private String code;
    private String message;

    public MintException(String message, String code) {
        super(message);
        this.code = code;
    }


}
