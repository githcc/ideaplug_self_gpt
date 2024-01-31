package com.cc.plug.action;

import com.cc.plug.factory.ChatFactory;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

import static com.cc.plug.data.F.*;

public class SelectAction extends AnAction {
    public static SelectAction selectAction;
    public SelectAction() {
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
            super.getTemplatePresentation().setText(ASK_GPT);
        }else{
            super.getTemplatePresentation().setText(ASK_GPT_FOR + text);
        }
    }
}
