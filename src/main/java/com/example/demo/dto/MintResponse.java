package com.example.demo.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Osagie Erhabor on 25/02/2020.
 */
@ToString
@Getter
public class MintResponse {
    private Map<String, Object> response = new HashMap<>() ;

    public MintResponse(Boolean status, Object T) {
        response.put("success",status);
        response.put("payload", T);
    }
}
