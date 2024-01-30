package com.cc.plug.window;

import com.cc.plug.action.SelectAction;
import com.cc.plug.data.D;
import com.cc.plug.entity.DialogEntity;
import com.cc.plug.entity.GlobalDialogEntity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import static com.cc.plug.data.F.USER_ROLE;
import static com.cc.plug.util.WebGptUtil.sendGptAndUpdate;
import static com.cc.plug.util.convert.GlobalDialogUtil.toStr;

public class ChatWindow {
    private JPanel chatJPanel;
    private JTextField contentText;
    private JButton sendButton;
    private JList<DialogEntity> contentList;
    private JComboBox<String> promptsBox;

    public JComponent getContentPanel() {
        return chatJPanel;
    }

    public void sendGpt(String text){
        GlobalDialogEntity globalDialogObject = D.globalDataEntity.getGlobalDialogEntityObject();
        Vector<DialogEntity> messages = globalDialogObject.getMessages();
        DialogEntity dialogEntityUser = new DialogEntity();
        dialogEntityUser.setRole(USER_ROLE);
        String promptsCheck = D.globalDataEntity.getPromptsCheck();
        String prompt = D.globalDataEntity.getPromptsList().get(promptsCheck);
        dialogEntityUser.setContent(prompt+" \n "+text);
        messages.add(dialogEntityUser);

        D.globalDataEntity.setGlobalDialogText(toStr(D.globalDataEntity.getGlobalDialogEntityObject()));
        sendGptAndUpdate(D.globalDataEntity);
        contentText.setText("");
    }

    private void sendText(){
        String text = contentText.getText();
        sendGpt(text);
    }

    public void cleanJList() {
        Vector<DialogEntity> dialogEntities = new Vector<>();
        contentList.setListData(dialogEntities);
    }

    public ChatWindow(Project project, ToolWindow toolWindow) {
        ChatWindow that = this;
        sendButton.addActionListener(e -> that.sendText());
        contentText.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                that.sendText();
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

    public void initJList(){
        contentList.setListData(D.globalDataEntity.getGlobalDialogEntityObject().getMessages());
    }
}
