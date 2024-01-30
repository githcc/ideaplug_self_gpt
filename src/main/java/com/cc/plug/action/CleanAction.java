package com.cc.plug.action;

import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.factory.ChatFactory;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class CleanAction extends AnAction {

    public CleanAction(){
        super(() -> "Clean", AllIcons.Actions.GC);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Vector<DialogEntity> dialogEntities = new Vector<>();
        D.globalDataEntity.getGlobalDialogEntityObject().setMessages(dialogEntities);
        ChatFactory.chatWindow.cleanJList();
    }
}
