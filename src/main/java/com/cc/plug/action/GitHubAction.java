package com.cc.plug.action;

import com.intellij.icons.AllIcons;
import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class GitHubAction extends AnAction {
    public GitHubAction(){
        super(() -> "Github", AllIcons.Vcs.Vendors.Github);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        BrowserUtil.browse("https://github.com/githcc?tab=repositories");
    }
}
