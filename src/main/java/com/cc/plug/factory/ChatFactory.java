package com.cc.plug.factory;

import com.cc.plug.action.CleanAction;
import com.cc.plug.action.DownloadAction;
import com.cc.plug.action.SettingAction;
import com.cc.plug.window.ChatWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChatFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ChatWindow chatWindow = new ChatWindow(project, toolWindow);
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(chatWindow.getContentPanel(), "", false);

        List<AnAction> actionList = new ArrayList<>();
        actionList.add(new CleanAction());
        actionList.add(new DownloadAction());
        actionList.add(new SettingAction());
        toolWindow.setTitleActions(actionList);
        toolWindow.getContentManager().addContent(content);
    }
}
