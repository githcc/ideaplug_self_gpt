package com.cc.plug.entity.gpt.nostream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Choices {

    private int index;
    private Message message;
    private String logprobs;
    @JsonProperty("finish_reason")
    private String finishReason;

    public void setIndex(int index) {
        this.index = index;
    }
    public int getIndex() {
        return this.index;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public Message getMessage() {
        return this.message;
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
