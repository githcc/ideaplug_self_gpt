package com.cc.plug.factory;

import com.cc.plug.action.CleanAction;
import com.cc.plug.action.DownloadAction;
import com.cc.plug.action.SettingAction;
import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.window.ChatWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ChatFactory implements ToolWindowFactory {
    public static ChatWindow chatWindow;
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        chatWindow = new ChatWindow(project, toolWindow);
        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(chatWindow.getContentPanel(), "", false);

        List<AnAction> actionList = new ArrayList<>();
        actionList.add(new CleanAction());
        actionList.add(new DownloadAction());
        actionList.add(new SettingAction());
        toolWindow.setTitleActions(actionList);
        toolWindow.getContentManager().addContent(content);
        chatWindow.initPromptsBox();
        chatWindow.initJList();
    }

    public static void downloadConversations(){
        String path =  System.getProperty("user.home");
        String fileName = path+File.separator+"Desktop"+File.separator+UUID.randomUUID()+".md";
        Vector<DialogEntity> messages = D.globalDataEntity.getGlobalDialogEntityObject().getMessages();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("# "+new Date()+ "\n\n\n");
            for (DialogEntity entity : messages) {
                writer.write("### " + new String(entity.getRole().getBytes(), "GBK") + ":\n");
                writer.write(new String(entity.getContent().getBytes(), "GBK")  + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
