package com.cc.plug.window;

import com.cc.plug.action.SelectAction;
import com.cc.plug.component.ChatComponent;
import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.cc.plug.factory.ChatFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.panels.VerticalLayout;
import com.intellij.util.ui.JBUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;

import static com.cc.plug.data.F.*;
import static com.cc.plug.util.WebGptUtil.sendNoStreamGptAndUpdate;
import static com.cc.plug.util.WebGptUtil.sendStreamGptAndUpdate;
import static com.cc.plug.util.convert.GlobalDialogUtil.toStr;
import static com.cc.plug.util.markdownUtil.convertMarkdownToHtml;

import javax.swing.*;

public class ChatWindow {
    private JPanel chatJPanel;
    private JTextField contentText;
    private JButton sendButton;
    private JComboBox<String> promptsBox;
    private JPanel subChatJPanel = new JPanel(new VerticalLayout(JBUI.scale(8)));
    private JScrollPane chatJScrollPane;
    private int chatComponentNum = 0;
    private static int num = 0;

    {
        if (num++ == 0){
            chatJScrollPane.setDoubleBuffered(true);
            chatJScrollPane.setViewportView(subChatJPanel);
        }
    }
    public JPanel getChatJPanel() {
        return chatJPanel;
    }

    public void sendGpt(String text){
        GlobalDialogEntity globalDialogObject = D.globalDataEntity.getGlobalDialogEntityObject();
        Vector<DialogEntity> messages = globalDialogObject.getMessages();
        DialogEntity dialogEntityUser = new DialogEntity();
        dialogEntityUser.setRole(ROLE_USER);
        String promptsCheck = D.globalDataEntity.getPromptsCheck();
        String prompt = D.globalDataEntity.getPromptsList().get(promptsCheck);
        String content = prompt + " \n " + text;
        dialogEntityUser.setContent(content);
        addChatMessage(content, COLOR_USER, COLOR_USER_DARK);
        messages.add(dialogEntityUser);

        D.globalDataEntity.setGlobalDialogText(toStr(D.globalDataEntity.getGlobalDialogEntityObject()));
        boolean stream = D.globalDataEntity.getGlobalDialogEntityObject().isStream();

        CompletableFuture.runAsync(() -> {
            try{
                if (stream){
                    sendStreamGptAndUpdate(D.globalDataEntity);
                }else{
                    sendNoStreamGptAndUpdate(D.globalDataEntity);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        contentText.setText("");
        chatJScrollPane.updateUI();
    }

    private void sendText(){
        String text = contentText.getText();
        sendGpt(text);
    }

    public void cleanJList() {
        chatComponentNum = 0;
        subChatJPanel.removeAll();
        chatJScrollPane.updateUI();
    }

    public void addChatMessage(String str, int color, int color_dark){
        chatComponentNum++;
        subChatJPanel.add(new ChatComponent(convertMarkdownToHtml(str), color, color_dark));
        chatJScrollPane.updateUI();
    }

    public void updateChatMessage(String str, int color, int color_dark){
        subChatJPanel.remove(chatComponentNum -1);
        subChatJPanel.add(new ChatComponent(convertMarkdownToHtml(str), color, color_dark));
        chatJScrollPane.updateUI();
    }

    public ChatWindow(Project project, ToolWindow toolWindow) {
        sendButton.addActionListener(e -> ChatFactory.chatWindow.sendText());
        contentText.addKeyListener(new KeyAdapter() {
                @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ChatFactory.chatWindow.sendText();
                }
            }
        });
        promptsBox.addActionListener(e -> {
            String check = (String) promptsBox.getSelectedItem();
            D.globalDataEntity.setPromptsCheck(check);
            SelectAction.selectAction.setText(check);
        });
    }

    public void initPromptsBox(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        Map<String, String> promptsMap = D.globalDataEntity.getPromptsList();
        Set<String> promptsSet = promptsMap.keySet();
        for (String s : promptsSet) {
            model.addElement(s);
        }
        promptsBox.setModel(model);
    }
}
