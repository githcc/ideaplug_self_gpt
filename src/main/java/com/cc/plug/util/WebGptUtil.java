package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDataEntity;
import com.cc.plug.factory.ChatFactory;
import com.cc.plug.util.convert.GptNoStreamUtil;
import com.cc.plug.util.convert.GptStreamUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.MessageDialogBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.cc.plug.data.F.*;

public class WebGptUtil {
    private WebGptUtil(){
    }

    public static Flux<String> sendStream(GlobalDataEntity globalDataEntity){
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
                .bodyToFlux(String.class)
                .onErrorResume(Exception.class, ex -> {
                    ApplicationManager.getApplication().invokeLater(() -> {
                        MessageDialogBuilder.yesNo("Network Error", "Please check proxy or key.").show();
                    });
                    return Flux.just("Network Error");
                });
    }


    public static String sendNoStream(GlobalDataEntity globalDataEntity){
        String proxy = globalDataEntity.getProxy();
        String key = globalDataEntity.getKey();
        String requestBody = globalDataEntity.getGlobalDialogText();
        WebClient webClient = WebClient.create(proxy+"/v1/chat");
        try{
            return webClient.post()
                    .uri("/completions")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .header(HttpHeaders.AUTHORIZATION, key)
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .bodyToMono(String.class).block();
        }catch (Exception e){
            ApplicationManager.getApplication().invokeLater(() -> {
                MessageDialogBuilder.yesNo("Network Error", "Please check proxy or key.").show();
            });
        }
        return "Network Error";
    }

    public static void sendStreamGptAndUpdate(GlobalDataEntity globalDataEntity){
        Flux<String> send = WebGptUtil.sendStream(globalDataEntity);
        List<DialogEntity> messages = D.globalDataEntity.getGlobalDialogEntityObject().getMessages();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole(ROLE_BOT);
        messages.add(dialogEntity);
        send.subscribe(data -> {
            if ("[DONE]".equals(data) || data == null){
                return;
            }
            String content;
            try{
                content = GptStreamUtil.toObj(data).getChoices().get(0).getDelta().getContent();
                if (content != null && content.isEmpty()){
                    ChatFactory.chatWindow.addChatMessage(dialogEntity.getContent(), COLOR_BOT, COLOR_BOT_DARK);
                }
                ChatFactory.chatWindow.updateChatMessage(dialogEntity.getContent(), COLOR_BOT, COLOR_BOT_DARK);
            }catch (Exception e){
                content = data;
            }
            if (content == null || content.isEmpty())return;
            dialogEntity.setContent(dialogEntity.getContent()+content);
        });
    }
    public static void sendNoStreamGptAndUpdate(GlobalDataEntity globalDataEntity){
        String data = WebGptUtil.sendNoStream(globalDataEntity);
        String content = GptNoStreamUtil.toObj(data).getChoices().get(0).getMessage().getContent();
        List<DialogEntity> messages = D.globalDataEntity.getGlobalDialogEntityObject().getMessages();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole(ROLE_BOT);
        dialogEntity.setContent(content);
        messages.add(dialogEntity);
        ChatFactory.chatWindow.addChatMessage(content, COLOR_BOT, COLOR_BOT_DARK);
    }
}
