package com.cc.plug.entity;


import java.io.Serializable;

public class DialogEntity implements Serializable {
    private String role;
    private String content = "";

    public DialogEntity() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getRole()+":" + getContent();
    }
}
