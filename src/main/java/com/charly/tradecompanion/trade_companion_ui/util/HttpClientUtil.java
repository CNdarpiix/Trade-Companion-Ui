package com.charly.tradecompanion.trade_companion_ui.util;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;
import java.time.Duration;

public final class HttpClientUtil {
    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();



    private HttpClientUtil(){
    }

    public static HttpClient getClient(){
        return CLIENT;
    }



}
