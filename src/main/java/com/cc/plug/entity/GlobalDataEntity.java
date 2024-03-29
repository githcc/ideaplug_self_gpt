package com.cc.plug.entity;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.cc.plug.data.F.*;

public class GlobalDataEntity implements Serializable {
    private String proxy = GlobalDataEntity_PROXY;
    private String key = GlobalDataEntity_KEY;
    private String dialog;
    private String globalDialogText;
    private boolean sharePrompts = GlobalDialogEntity_SHARE_PROMPTS;
    private boolean shareConversations = GlobalDialogEntity_SHARE_CONVERSATIONS;
    private GlobalDialogEntity globalDialogEntityObject = new GlobalDialogEntity();

    private Map<String, String> promptsList = new LinkedHashMap<>();
    private Map<String, String> promptsListBak = new LinkedHashMap<>();
    private String promptsCheck = GlobalDataEntity_CHAT;

    public GlobalDataEntity() {
        promptsList.put(GlobalDataEntity_CHAT,"");
        promptsList.put("Example","Write a similar example");
        promptsList.put("Explain","Explain this code");
        promptsListBak.putAll(promptsList);
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
