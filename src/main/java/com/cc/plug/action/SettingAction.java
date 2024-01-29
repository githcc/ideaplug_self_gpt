package com.cc.plug.action;

import com.cc.plug.factory.SettingFactory;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import org.jetbrains.annotations.NotNull;

public class SettingAction extends AnAction {
    public SettingAction(){
        super(() -> "Setting", AllIcons.Actions.Properties);
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ShowSettingsUtil.getInstance().showSettingsDialog(anActionEvent.getProject(), SettingFactory.class);
    }
}
