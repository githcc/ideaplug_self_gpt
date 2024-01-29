package com.cc.plug.window;

import com.cc.plug.data.D;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow {
    private JPanel chatJPanel;
    private JTextField conentText;
    private JButton sendButton;
    private JList list;
    private JComboBox comboBox1;

    public ChatWindow(Project project, ToolWindow toolWindow) {

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = conentText.getText();
                D.globalDataEntity.setCheckText(text);

                System.out.println(text);
                conentText.setText("");
            }
        });
    }

    public JComponent getContentPanel() {
        return chatJPanel;
    }
}
