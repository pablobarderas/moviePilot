package com.app.moviePilot.controller.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.JsonElement;
/**
 * 
 * @author Marino Burillo
 *
 */
public class UrlBuilder {
    private String baseURL;
    private String key;
    private Map<String, String> queryParameters;

    private UrlBuilder(String baseURL, String key) {
        this.baseURL = baseURL;
        this.key = key;
        this.queryParameters = new LinkedHashMap<>();
    }

    public static UrlBuilder create(String baseURL, String key) {
        return new UrlBuilder(baseURL, key);
    }

    public UrlBuilder addQueryParameter(String key, String value) {
        queryParameters.put(key, value);
        return this;
    }

    public JsonElement build() {
        StringBuilder urlBuilder = new StringBuilder(baseURL);
        if (!queryParameters.isEmpty()) {
            for (Map.Entry<String, String> entry : queryParameters.entrySet()) {
            	if(entry.getKey().equals("type")) urlBuilder.append(entry.getValue()).append('?');
            	else urlBuilder.append(entry.getValue()).append("&");
            }
        }
        urlBuilder.append(key);
        return JsonFactory.getResponse(urlBuilder.toString());
    }
}
