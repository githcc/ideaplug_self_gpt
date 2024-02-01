package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.cc.plug.util.convert.GlobalDialogUtil;
import org.junit.Test;
import reactor.core.publisher.Flux;
import java.util.Vector;

public class TestWebGptUtil {
    @Test
    public void tStream() throws InterruptedException {
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

        // Test Web Flux
        Flux<String> send = WebGptUtil.sendStream(D.globalDataEntity);
        send.subscribe(System.out::println);
        Thread.sleep(20000);
    }

    @Test
    public void tNoStream() {
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

        // Test Web Flux
        String content = WebGptUtil.sendNoStream(D.globalDataEntity);
        System.out.println(content);
    }
}
