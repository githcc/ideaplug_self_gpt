package com.cc.plug.action;

import com.cc.plug.data.D;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;

public class SelectAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        SelectionModel model = editor.getSelectionModel();
        String text = model.getSelectedText();
        if ( text == null || text.trim().isEmpty()){
            return;
        }

        // Test Check
        System.out.println(text);
        D.globalDataEntity.setCheckText(text);
    }
}
