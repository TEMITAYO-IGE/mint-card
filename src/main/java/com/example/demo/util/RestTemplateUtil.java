package com.example.demo.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Osagie Erhabor on 25/02/2020.
 */
public class RestTemplateUtil {
    private String url;
    private HttpMethod httpMethod;
    private Object body;
    private HttpHeaders httpHeaders;

    public RestTemplateUtil(String url, HttpMethod httpMethod, Object body, HttpHeaders httpHeaders) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.body = body;
        this.httpHeaders = httpHeaders;
    }

    public ResponseEntity<String> exchange() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);

        return restTemplate.exchange(url, httpMethod, httpEntity, String.class);
    }
}
