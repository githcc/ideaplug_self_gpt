package com.cc.plug.entity.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choices {
    private int index;
    private Delta delta;
    private String logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return this.index;
    }
    public void setDelta(Delta delta) {
        this.delta = delta;
    }
    public Delta getDelta() {
        return this.delta;
    }
    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }
    public String getLogprobs() {
        return this.logprobs;
    }
    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }
    public String getFinishReason() {
        return this.finishReason;
    }
}
