package com.cc.plug.entity.gpt.stream;

public class Delta {

    private String role;
    private String content;

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }
}
