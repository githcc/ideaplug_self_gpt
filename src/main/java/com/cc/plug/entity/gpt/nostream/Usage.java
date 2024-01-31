package com.cc.plug.entity.gpt.nostream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usage {

    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }
    public int getPromptTokens() {
        return this.promptTokens;
    }
    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }
    public int getCompletionTokens() {
        return this.completionTokens;
    }
    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }
    public int getTotalTokens() {
        return this.totalTokens;
    }
}