package com.cc.plug.entity;


import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.cc.plug.data.F.*;

public class GlobalDataEntity {
    private String proxy = GlobalDataEntity_PROXY;
    private String key = GlobalDataEntity_KEY;
    private int maxNum = GlobalDataEntity_MAXNUM;
    private String dialog;
    private String globalDialogText;
    private boolean sharePrompts = GlobalDialogEntity_SHARE_PROMPTS;
    private boolean shareConversations = GlobalDialogEntity_SHARE_CONVERSATIONS;
    private GlobalDialogEntity globalDialogEntityObject = new GlobalDialogEntity();

    private Map<String, String> promptsList;
    private Map<String, String> promptsListBak;
    private String promptsCheck = GlobalDataEntity_CHAR;
    private DefaultTableModel tableModel = new DefaultTableModel(null, PROMPTS_HEAD) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public GlobalDataEntity() {
        promptsList = new LinkedHashMap<>();
        promptsList.put(GlobalDataEntity_CHAR,"");
        promptsList.put("Example","Write a similar example");
        promptsList.put("Explain","Explain this code");

        promptsListBak = new LinkedHashMap<>();
        promptsListBak.put(GlobalDataEntity_CHAR,"");
        promptsListBak.put("Example","Write a similar example");
        promptsListBak.put("Explain","Explain this code");
    }

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDialog() {
        return dialog;
    }

    public void setDialog(String dialog) {
        this.dialog = dialog;
    }

    public String getGlobalDialogText() {
        return globalDialogText;
    }

    public void setGlobalDialogText(String globalDialogText) {
        this.globalDialogText = globalDialogText;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public boolean isSharePrompts() {
        return sharePrompts;
    }

    public void setSharePrompts(boolean sharePrompts) {
        this.sharePrompts = sharePrompts;
    }

    public boolean isShareConversations() {
        return shareConversations;
    }

    public void setShareConversations(boolean shareConversations) {
        this.shareConversations = shareConversations;
    }

    public GlobalDialogEntity getGlobalDialogEntityObject() {
        return globalDialogEntityObject;
    }

    public void setGlobalDialogEntityObject(GlobalDialogEntity globalDialogEntityObject) {
        this.globalDialogEntityObject = globalDialogEntityObject;
    }

    public Map<String, String> getPromptsList() {
        return promptsList;
    }

    public void setPromptsList(Map<String, String> promptsList) {
        this.promptsList = promptsList;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public String getPromptsCheck() {
        return promptsCheck;
    }

    public void setPromptsCheck(String promptsCheck) {
        this.promptsCheck = promptsCheck;
    }

    public Map<String, String> getPromptsListBak() {
        return promptsListBak;
    }

    public void setPromptsListBak(Map<String, String> promptsListBak) {
        this.promptsListBak = promptsListBak;
    }
}
