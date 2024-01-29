package com.cc.plug.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class CleanAction extends AnAction {

    public CleanAction(){
        super(() -> "Clean", AllIcons.Actions.GC);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

    }
}
