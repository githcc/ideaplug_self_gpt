package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class TestWebUtil {

    @Test
    public void t() throws InterruptedException {
        GlobalDialogEntity globalDialogEntity = new GlobalDialogEntity();
        List<DialogEntity> dialogEntities = new ArrayList<>();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole("user");
        dialogEntity.setContent("who are you");
        dialogEntities.add(dialogEntity);
        globalDialogEntity.setMessages(dialogEntities);

        String str = GlobalDialogUtil.toStr(globalDialogEntity);
        D.globalDataEntity.setGlobalDialogObject(globalDialogEntity);
        D.globalDataEntity.setGlobalDialogText(str);

        // Test Web Flux
        Flux<String> send = WebUtil.send(D.globalDataEntity);
        send.subscribe(System.out::println);
        Thread.sleep(20000);
    }
}
