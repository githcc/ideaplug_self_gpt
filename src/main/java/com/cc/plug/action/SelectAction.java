package com.cc.plug.action;

import com.cc.plug.factory.ChatFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;

import static com.cc.plug.data.F.*;
import static com.cc.plug.util.io.PersistenceUtil.globalToFile;
import static com.intellij.openapi.actionSystem.ex.ActionUtil.createEmptyEvent;

public class SelectAction extends AnAction {
    public static SelectAction selectAction = new SelectAction();
    public static String dynamicText;
    static {
        selectAction.setText(GlobalDataEntity_CHAT);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
        String fileName = file != null ? file.getName() : "\\.";
        String fileType = fileName.split("\\.")[1];

        SelectionModel model = editor.getSelectionModel();
        String text = model.getSelectedText();
        if (text == null || text.trim().isEmpty()){
            return;
        }
        String content = String.format("\n```%s\n%s\n```", fileType, text);
        ChatFactory.chatWindow.sendGpt(content);
    }

    public void setText(String text){
        if (GlobalDataEntity_CHAT.equals(text)){
            dynamicText = ASK_GPT;
        } else {
            dynamicText = ASK_GPT_FOR + text;
        }
        AnActionEvent event = createEmptyEvent();
        update(event);
    }

    @Override
    public void update(AnActionEvent e) {
        ApplicationManager.getApplication().invokeLater(() -> {
            Presentation presentation = e.getPresentation();
            presentation.setText(dynamicText);
            globalToFile();
        });
    }
}
