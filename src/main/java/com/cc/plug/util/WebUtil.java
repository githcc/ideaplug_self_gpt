package com.cc.plug.util;

import com.cc.plug.entity.GlobalDataEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class WebUtil {
    public static Flux<String> send(GlobalDataEntity globalDataEntity){
        String proxy = globalDataEntity.getProxy();
        String key = globalDataEntity.getKey();
        String requestBody = globalDataEntity.getGlobalDialogText();
        WebClient webClient = WebClient.create(proxy+"/v1/chat");

        // Initiate an asynchronous POST request and obtain a Flux representation of the response body
        return webClient.post()
                .uri("/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, key)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToFlux(String.class);
    }
}
