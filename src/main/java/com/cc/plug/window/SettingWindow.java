package com.cc.plug.window;

import com.cc.plug.data.D;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;

import static com.cc.plug.util.io.PersistenceUtil.globalToFile;

public class SettingWindow {
    private JTextField proxyText;
    private JTextField keyText;
    private JTextField maxNumText;
    private JCheckBox sharePromptsCheckBox;
    private JCheckBox shareConversationsCheckBox;
    private JPanel settingJPanel;
    private static int num = 0 ;
    {
        if (num++ == 0){
            String proxy = D.globalDataEntity.getProxy();
            String key = D.globalDataEntity.getKey();
            int maxNum = D.globalDataEntity.getMaxNum();
            boolean sharePrompts = D.globalDataEntity.isSharePrompts();
            boolean shareConversations = D.globalDataEntity.isShareConversations();
            proxyText.setText(proxy);
            keyText.setText(key);
            maxNumText.setText(String.valueOf(maxNum));
            sharePromptsCheckBox.setSelected(sharePrompts);
            shareConversationsCheckBox.setSelected(shareConversations);
        }
    }

    public SettingWindow() {
    }
    public void apply(){
        String proxy = proxyText.getText();
        String key = keyText.getText();
        int maxNum = Integer.parseInt(maxNumText.getText());
        boolean sharePrompts = sharePromptsCheckBox.isSelected();
        boolean shareConversations = shareConversationsCheckBox.isSelected();
        D.globalDataEntity.setProxy(proxy);
        D.globalDataEntity.setKey(key);
        D.globalDataEntity.setMaxNum(maxNum);
        D.globalDataEntity.setSharePrompts(sharePrompts);
        D.globalDataEntity.setShareConversations(shareConversations);
        globalToFile();
    }

    public JPanel getSettingJPanel() {
        return settingJPanel;
    }

    public boolean isModified() {
        maxNumText.setText(maxNumText.getText().replaceAll("\\D", ""));
        return !StringUtil.equals(proxyText.getText(), D.globalDataEntity.getProxy()) ||
                !StringUtil.equals(keyText.getText(), D.globalDataEntity.getKey()) ||
                Integer.parseInt(maxNumText.getText()) != D.globalDataEntity.getMaxNum() ||
                sharePromptsCheckBox.isSelected()!=D.globalDataEntity.isSharePrompts() ||
                shareConversationsCheckBox.isSelected()!= D.globalDataEntity.isShareConversations();
    }

    public void reset() {
        proxyText.setText(D.globalDataEntity.getProxy());
        keyText.setText(D.globalDataEntity.getKey());
        maxNumText.setText(String.valueOf(D.globalDataEntity.getMaxNum()));
        sharePromptsCheckBox.setSelected(D.globalDataEntity.isSharePrompts());
        shareConversationsCheckBox.setSelected(D.globalDataEntity.isShareConversations());
    }
}
