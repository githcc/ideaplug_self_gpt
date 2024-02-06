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
    public JPanel getPromptJPanel() {
        return promptJPanel;
    }

    private static boolean firstLoad = true;
    public PromptWindow() {
        promptsTable.setModel(D.tableModel);
        promptsTable.setEnabled(true);
        if (firstLoad){
            firstLoad = false;
            loadPrompts(D.globalDataEntity.getPromptsList());
        }
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
            D.tableModel.setDataVector(new Object[][]{{PROMPTS_HEAD[0], PROMPTS_HEAD[1]}}, PROMPTS_HEAD);
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
