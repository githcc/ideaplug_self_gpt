package com.cc.plug.window;

import com.cc.plug.data.D;
import com.cc.plug.factory.ChatFactory;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

import static com.cc.plug.data.F.PROMPTS_HEAD;
import static com.cc.plug.util.io.PersistenceUtil.globalToFile;

public class PromptWindow {
    private JPanel promptJPanel;
    private JTable promptsTable;
    private JTextField nameText;
    private JButton addButton;
    private JTextArea contentText;
    private JButton resetButton;

    private static int num = 0;

    {
        promptsTable.setModel(D.tableModel);
        promptsTable.setEnabled(true);
        if (num++ == 0){
            loadPrompts(D.globalDataEntity.getPromptsList());
        }
    }

    public JPanel getPromptJPanel() {
        return promptJPanel;
    }

    public PromptWindow() {
        addButton.addActionListener(e -> {
            String name = nameText.getText();
            String content = contentText.getText();
            if (name == null || content == null || name.trim().isEmpty() || content.trim().isEmpty()) return;
            D.globalDataEntity.getPromptsList().put(name, content);
            D.tableModel.addRow(new Object[]{name, content});
            ChatFactory.chatWindow.initPromptsBox();

            nameText.setText("");
            contentText.setText("");
            globalToFile();
        });

        resetButton.addActionListener(e -> {
            D.globalDataEntity.getPromptsList().clear();
            D.tableModel.setDataVector(null, PROMPTS_HEAD);
            loadPrompts(D.globalDataEntity.getPromptsListBak());
            ChatFactory.chatWindow.initPromptsBox();
            globalToFile();
        });
    }

    private void loadPrompts(Map<String, String> promptsMap){
        Set<String> setPrompts = promptsMap.keySet();
        for (String key : setPrompts) {
            D.tableModel.addRow(new Object[]{key, promptsMap.get(key)});
        }
    }
}
