package com.cc.plug.window;

import com.cc.plug.data.D;
import com.cc.plug.factory.ChatFactory;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

import static com.cc.plug.data.F.PROMPTS_HEAD;

public class PromptWindow {
    private JPanel promptJPanel;
    private JTable promptsTable;
    private JTextField nameText;
    private JButton addButton;
    private JTextArea contentText;
    private JButton resetButton;

    public PromptWindow() {
        promptsTable.setModel(D.globalDataEntity.getTableModel());
        promptsTable.setEnabled(true);
        loadPrompts(D.globalDataEntity.getPromptsList());
        addButton.addActionListener(e -> {
            String name = nameText.getText();
            String content = contentText.getText();
            if (name == null || content == null || name.trim().isEmpty() || content.trim().isEmpty()) return;
            D.globalDataEntity.getPromptsList().put(name, content);
            D.globalDataEntity.getTableModel().addRow(new Object[]{name, content});
            ChatFactory.chatWindow.initPromptsBox();

            nameText.setText("");
            contentText.setText("");
        });
        resetButton.addActionListener(e -> {
            D.globalDataEntity.getPromptsList().clear();
            D.globalDataEntity.getTableModel().setDataVector(null, PROMPTS_HEAD);
            loadPrompts(D.globalDataEntity.getPromptsListBak());
            ChatFactory.chatWindow.initPromptsBox();
        });
    }

    public JPanel getPromptJPanel() {
        return promptJPanel;
    }

    private void loadPrompts(Map<String, String> promptsMap){
        Set<String> setPrompts = promptsMap.keySet();
        for (String key : setPrompts) {
            D.globalDataEntity.getTableModel().addRow(new Object[]{key, promptsMap.get(key)});
        }
    }
}
