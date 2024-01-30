package com.cc.plug.entity.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GptStreamEntity {
    private String id;
    private String object;
    private int created;
    private String model;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;
    private List<Choices> choices;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getObject() {
        return this.object;
    }
    public void setCreated(int created) {
        this.created = created;
    }
    public int getCreated() {
        return this.created;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return this.model;
    }
    public void setSystemFingerprint(String systemFingerprint) {
        this.systemFingerprint = systemFingerprint;
    }
    public String getSystemFingerprint() {
        return this.systemFingerprint;
    }
    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }
    public List<Choices> getChoices() {
        return this.choices;
    }
}
