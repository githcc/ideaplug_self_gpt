package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.cc.plug.util.convert.GlobalDialogUtil;
import com.cc.plug.util.convert.GptStreamUtil;
import org.junit.Test;
import reactor.core.publisher.Flux;
import java.util.Vector;

public class TestGptStreamUtil {
    @Test
    public void t() throws InterruptedException {
        GlobalDialogEntity globalDialogEntity = new GlobalDialogEntity();
        Vector<DialogEntity> dialogEntities = new Vector<>();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole("user");
        dialogEntity.setContent("who are you");
        dialogEntities.add(dialogEntity);
        globalDialogEntity.setMessages(dialogEntities);

        String str = GlobalDialogUtil.toStr(globalDialogEntity);
        D.globalDataEntity.setGlobalDialogEntityObject(globalDialogEntity);
        D.globalDataEntity.setGlobalDialogText(str);

        // Test Web Flux Stream
        Flux<String> send = WebGptUtil.sendStream(D.globalDataEntity);
        send.subscribe(data -> {
            if ("[DONE]".equals(data) || data == null){
                return;
            }
            String content;
            try{
                content = GptStreamUtil.toObj(data).getChoices().get(0).getDelta().getContent();
            }catch (Exception e){
                content = data;
            }
            if (content == null)return;
            System.out.printf(content);
        });

        Thread.sleep(20000);
    }
}
