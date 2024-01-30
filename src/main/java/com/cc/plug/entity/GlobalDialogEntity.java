package com.cc.plug.entity;

import java.util.Vector;

import static com.cc.plug.data.F.GlobalDialogEntity_MODEL;
import static com.cc.plug.data.F.GlobalDialogEntity_STREAM;

public class GlobalDialogEntity {
    private String model = GlobalDialogEntity_MODEL;
    private boolean stream = GlobalDialogEntity_STREAM;
    private Vector<DialogEntity> messages = new Vector<>();

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

    public Vector<DialogEntity> getMessages() {
        return messages;
    }

    public void setMessages(Vector<DialogEntity> messages) {
        this.messages = messages;
    }
}
