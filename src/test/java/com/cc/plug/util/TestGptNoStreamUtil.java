package com.cc.plug.util;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.cc.plug.util.convert.GlobalDialogUtil;
import com.cc.plug.util.convert.GptNoStreamUtil;
import org.junit.Test;

import java.util.Vector;

public class TestGptNoStreamUtil {
    @Test
    public void t() {
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

        // Test Web Flux No Stream
        String data = WebGptUtil.sendNoStream(D.globalDataEntity);
        System.out.println(GptNoStreamUtil.toObj(data).getChoices().get(0).getMessage().getContent());
    }
}
