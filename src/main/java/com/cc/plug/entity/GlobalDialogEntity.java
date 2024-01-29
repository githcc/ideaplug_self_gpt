package com.cc.plug.entity;

import java.util.List;

public class GlobalDialogEntity {
    private String model = "gpt-3.5-turbo-1106";
    private boolean stream = true;
    private List<DialogEntity> messages;

    public GlobalDialogEntity() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public List<DialogEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<DialogEntity> messages) {
        this.messages = messages;
    }
}
