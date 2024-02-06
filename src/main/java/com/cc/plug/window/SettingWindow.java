package com.cc.plug.window;

import com.cc.plug.data.D;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;

import static com.cc.plug.util.io.PersistenceUtil.globalToFile;

public class SettingWindow {
    private JPanel settingJPanel;
    private JTextField proxyText;
    private JTextField keyText;
    private JCheckBox sharePromptsCheckBox;
    private JCheckBox shareConversationsCheckBox;

    public SettingWindow() {
        proxyText.setText(D.globalDataEntity.getProxy());
        keyText.setText(D.globalDataEntity.getKey());
        sharePromptsCheckBox.setSelected(D.globalDataEntity.isSharePrompts());
        shareConversationsCheckBox.setSelected(D.globalDataEntity.isShareConversations());
    }

    public JPanel getSettingJPanel() {
        return settingJPanel;
    }

    public void apply(){
        String proxy = proxyText.getText();
        String key = keyText.getText();
        boolean sharePrompts = sharePromptsCheckBox.isSelected();
        boolean shareConversations = shareConversationsCheckBox.isSelected();
        D.globalDataEntity.setProxy(proxy);
        D.globalDataEntity.setKey(key);
        D.globalDataEntity.setSharePrompts(sharePrompts);
        D.globalDataEntity.setShareConversations(shareConversations);
        globalToFile();
    }

    public boolean isModified() {
        return !StringUtil.equals(proxyText.getText(), D.globalDataEntity.getProxy()) ||
                !StringUtil.equals(keyText.getText(), D.globalDataEntity.getKey()) ||
                sharePromptsCheckBox.isSelected()!=D.globalDataEntity.isSharePrompts() ||
                shareConversationsCheckBox.isSelected()!= D.globalDataEntity.isShareConversations();
    }

    public void reset() {
        proxyText.setText(D.globalDataEntity.getProxy());
        keyText.setText(D.globalDataEntity.getKey());
        sharePromptsCheckBox.setSelected(D.globalDataEntity.isSharePrompts());
        shareConversationsCheckBox.setSelected(D.globalDataEntity.isShareConversations());
    }
}
