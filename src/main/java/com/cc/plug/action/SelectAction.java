package com.cc.plug.action;

import com.cc.plug.factory.ChatFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

import static com.cc.plug.data.F.*;
import static com.intellij.openapi.actionSystem.ex.ActionUtil.createEmptyEvent;

public class SelectAction extends AnAction {
    public static SelectAction selectAction = new SelectAction();
    public static String dynamicText;
    private SelectAction() {
        selectAction = this;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        SelectionModel model = editor.getSelectionModel();
        String text = model.getSelectedText();
        if (text == null || text.trim().isEmpty()){
            return;
        }
        ChatFactory.chatWindow.sendGpt(text);
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
        });
    }
}
