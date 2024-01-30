package com.cc.plug.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import static com.cc.plug.factory.ChatFactory.downloadConversations;

public class DownloadAction extends AnAction {
    public DownloadAction(){
        super(() -> "Download", AllIcons.Actions.Download);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        downloadConversations();
    }
}
