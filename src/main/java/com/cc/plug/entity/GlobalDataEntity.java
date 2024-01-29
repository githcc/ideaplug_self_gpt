package com.cc.plug.entity;


public class GlobalDataEntity {
    private String proxy = "https://api.openai-proxy.com";
    private String key = "Bearer sk-xxx";
    private Integer maxNum = 0;
    private String checkText;
    private String dialog;
    private String globalDialogText;
    private GlobalDialogEntity globalDialogEntityObject;

    public GlobalDataEntity() {
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

    public String getCheckText() {
        return checkText;
    }

    public void setCheckText(String checkText) {
        this.checkText = checkText;
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

    public GlobalDialogEntity getGlobalDialogObject() {
        return globalDialogEntityObject;
    }

    public void setGlobalDialogObject(GlobalDialogEntity globalDialogEntityObject) {
        this.globalDialogEntityObject = globalDialogEntityObject;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
}
