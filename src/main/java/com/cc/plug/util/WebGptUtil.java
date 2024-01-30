package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDataEntity;
import com.cc.plug.factory.ChatFactory;
import com.cc.plug.util.convert.GptStreamUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.cc.plug.data.F.BOT_ROLE;

public class WebGptUtil {
    private WebGptUtil(){
    }

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

    public static void sendGptAndUpdate(GlobalDataEntity globalDataEntity){
        Flux<String> send = WebGptUtil.send(globalDataEntity);
        List<DialogEntity> messages = D.globalDataEntity.getGlobalDialogEntityObject().getMessages();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole(BOT_ROLE);
        messages.add(dialogEntity);
        send.subscribe(data -> {
            if ("[DONE]".equals(data) || data == null){
                ChatFactory.chatWindow.initJList();
                return;
            }
            String content;
            try{
                content = GptStreamUtil.toObj(data).getChoices().get(0).getDelta().getContent();
            }catch (Exception e){
                content = data;
            }
            if (content == null)return;
            dialogEntity.setContent(dialogEntity.getContent()+content);
        });
    }
}
