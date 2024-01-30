package com.cc.plug.util;

import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.cc.plug.util.convert.GlobalDialogUtil;
import org.junit.Test;

import java.util.Vector;

public class TestGlobalDialogUtil {
    @Test
    public void t() {
        GlobalDialogEntity globalDialogEntity = new GlobalDialogEntity();
        Vector<DialogEntity> dialogEntities = new Vector<>();
        DialogEntity dialogEntity = new DialogEntity();
        dialogEntity.setRole("user");
        dialogEntity.setContent("who are you");
        dialogEntities.add(dialogEntity);
        globalDialogEntity.setMessages(dialogEntities);

        // Test toStr
        String str = GlobalDialogUtil.toStr(globalDialogEntity);
        System.out.println(str);

        // Test toObj
        globalDialogEntity = GlobalDialogUtil.toObj(str);
        str = GlobalDialogUtil.toStr(globalDialogEntity);
        System.out.println(str);
    }
}
